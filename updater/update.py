import glob
import os
import shutil
import sys
import getopt
import subprocess

# Process:
# *download
# *stop service/delete old
# *set env vars



# Start here
def main(argv):
    # Validate arguments
    version = ''

    opts, args = getopt.getopt(argv, "hv:", ["version="])
    for opt, arg in opts:
        if opt == "-h":
            print('Arguments: -v/--version= <version>')
            sys.exit()
        elif opt in ("-v", "--version"):
            version = arg

    if not version:
        print('Version not provided; use -v <version or --version <version>')
        sys.exit(22)

    print('Starting MSM Updater with version ' + version)

    download_new_version(version)
    uninstall_old(version)
    install_new()

def download_new_version(version):
    download_location = './'+'meanstreammachine-'+version+'/bin'
    print('Downloading version ' + version + ' to ' + download_location + '...')
    curl_command_args_list = make_download_curl_command_args_list(version,download_location)
    result = run_subprocess_command(curl_command_args_list)

    print('Download complete')

def uninstall_old(new_version):
    remove_old_versions_except_version(os.getcwd(),new_version)
    return

def install_new():
    return
def remove_old_versions_except_version(base_path, specified_version):
    pattern = f"{base_path}/meanstreammachine-*"
    directories = glob.glob(pattern)

    for directory in directories:
        if directory.endswith(specified_version) or directory == specified_version:
            continue
        try:
            shutil.rmtree(directory)
            print(f"Removed: {directory}")
        except Exception as e:
            print(f"Failed to remove {directory}: {e}")
def make_header_str(header_name, header_value):
    return '"' + header_name + ': ' + header_value + '"'


def make_download_curl_command_args_list(version,download_location):
    # curl --create-dirs -O --output-dir ./bin -L -H "Authorization: Bearer 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" -H "X-GitHub-Api-Version: 2022-11-28" https://maven.pkg.github.com/tsallustro/MeanStreamMachine/com.meanmachines.meanstreammachine/alpha-12.4.23/meanstreammachine-alpha-12.4.23.jar
    github_auth_token = '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'
    github_api_version = '2022-11-28'
    download_url_prefix = 'https://maven.pkg.github.com/tsallustro/MeanStreamMachine/com.meanmachines.meanstreammachine/'


    curl_command_prefix = 'curl --create-dirs -O --output-dir '+download_location+' -L'
    auth_header_str = make_header_str('Authorization', 'Bearer ' + github_auth_token)
    github_api_version_header_str = make_header_str('X-GitHub-Api-Version', github_api_version)
    download_url_str = download_url_prefix + version + '/meanstreammachine-' + version + '.jar'

    curl_command_args_list = []
    curl_command_args_list.extend(curl_command_prefix.split(' '))
    curl_command_args_list.extend(['-H', auth_header_str])
    curl_command_args_list.extend(['-H', github_api_version_header_str])
    curl_command_args_list.append(download_url_str)
    return curl_command_args_list


def run_subprocess_command(command_args_list):
    subprocess_timeout_seconds = 60
    return subprocess.run(
        args=command_args_list,
        timeout=subprocess_timeout_seconds)


if __name__ == "__main__":
    main(sys.argv[1:])
