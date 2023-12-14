import getopt
import glob
import os
import re
import shutil
import subprocess
import sys
import requests

arguments = {
    "-v": "(required) version number to install",
    "-r": "do not delete files for previous versions",
    "-h": "show this menu"
}
APP_NAME = "MeanStreamMachine"
MAVEN_ARTIFACT_NAME = "meanstreammachine"
github_auth_token = os.environ["MSM_GITHUB_TOKEN"]
github_api_version = '2022-11-28'

download_url_prefix = 'https://maven.pkg.github.com/tsallustro/MeanStreamMachine/com.meanmachines.meanstreammachine/'


# Process:
# *download
# *stop service/delete old
# *set env vars


# Start here
def main(argv):
    # Validate arguments
    version = ''
    remove_old = True
    curl_verbose = False
    opts, args = getopt.getopt(argv, "rhv:", [])
    for opt, arg in opts:
        if opt == "-h":
            print('Arguments: ' + str(arguments))
            sys.exit()
        elif opt == '-v':
            version = arg
        elif opt == '-r':
            remove_old = False


    if not version:
        print('Version not provided; use -v <version or --version <version>')
        sys.exit(22)

    print('Starting MSM Updater with version ' + version)

    download_new_version(version)
    uninstall_old(version, remove_old)
    install_new(version)


def download_new_version(version):
    download_location = './' + MAVEN_ARTIFACT_NAME + '-' + version + '/bin'
    obj_name = MAVEN_ARTIFACT_NAME + '-' + version + '.jar'
    print('Downloading version ' + version + ' to ' + download_location + '...')
    headers = {
        'User-Agent': 'msmUpdater',
        'Authorization': 'Bearer '+github_auth_token,
        'X-GitHub-Api-Version': github_api_version
    }
    download_url_str = download_url_prefix + version + '/' + obj_name
    if not os.path.exists(download_location):
        os.makedirs(download_location)
        print("Created folder "+download_location)
    response = requests.get(download_url_str, headers=headers)

    if response.status_code == 200:
        with open(download_location+'/'+obj_name, 'wb') as file:
            file.write(response.content)
        print(f"File downloaded successfully as {download_location}")
    else:
        print("Failed to download the file")


def uninstall_old(new_version, remove_old):
    if remove_old:
        print("Removing old versions... ")
        remove_old_versions_except_version(os.getcwd(), new_version)

    print("Stopping " + APP_NAME + ".service...")
    stop_service_command = ['sudo', 'systemctl', 'stop', APP_NAME + '.service']
    run_subprocess_command(stop_service_command)


def install_new(new_version):
    app_version_str = MAVEN_ARTIFACT_NAME + "-" + new_version

    print("Writing new environment variables...")
    print("Bin path...")
    bin_path = os.getcwd() + "/" + app_version_str + "/bin"
    print("Bin path is " + bin_path)
    update_config(APP_NAME + "_BIN", bin_path)

    print("Jar path...")
    jar_path = bin_path + "/" + app_version_str + ".jar"
    print("Jar path is " + jar_path)
    update_config(APP_NAME + "_JAR", jar_path)

    print("Setting permissions...")
    permissions_command = ['chmod', '+x', jar_path]
    run_subprocess_command(permissions_command)

    print("Restarting " + APP_NAME + ".service...")
    start_service_command = ['sudo', 'systemctl', 'start', APP_NAME + '.service']
    run_subprocess_command(start_service_command)


def update_config(var_name, new_value):
    config_file = "/etc/MeanConfig.conf"

    # Check if the environment variable exists in the config file
    with open(config_file, 'r') as file:
        lines = file.readlines()

    found = False
    for i, line in enumerate(lines):
        if re.match(f"^{var_name}=", line):
            lines[i] = f"{var_name}={new_value}\n"
            found = True
            break

    if not found:
        lines.append(f"{var_name}={new_value}\n")

    with open(config_file, 'w') as file:
        file.writelines(lines)

    # Apply changes by executing shell commands
    subprocess.run(f"export {var_name}={new_value}", shell=True)


def remove_old_versions_except_version(base_path, specified_version):
    pattern = f"{base_path}/" + MAVEN_ARTIFACT_NAME + "-*"
    directories = glob.glob(pattern)

    for directory in directories:
        if directory.endswith(specified_version) or directory == specified_version:
            continue
        try:
            shutil.rmtree(directory)
            print(f"Removed: {directory}")
        except Exception as e:
            print(f"Failed to remove {directory}: {e}")

def run_subprocess_command(command_args_list):
    subprocess_timeout_seconds = 60
    return subprocess.run(
        args=command_args_list,
        timeout=subprocess_timeout_seconds)


if __name__ == "__main__":
    main(sys.argv[1:])