package com.meanmachines.MeanStreamMachine.service;

import com.meanmachines.MeanStreamMachine.model.dto.request.RegisterRequest;
import com.meanmachines.MeanStreamMachine.model.dto.response.RegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRegistrationManager {
    @Autowired
    @Qualifier("userManager")
    UserDetailsManager userDetailsManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    String FAIL_PASSWORDS_DONT_MATCH = "Passwords do not match";
    String FAIL_INVALID_PASSWORD = "Passwords must only be mixed case letters, numbers, and be 8-32 characters";
    String FAIL_INVALID_USERNAME = "Username must be only letters and be no more than 12 characters";

    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,32}$";
    String USERNAME_REGEX = "^[a-zA-Z0-9]{1,12}$";


    public RegisterResponse createNewUser(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        response.setSuccess(false);
        if(!validateUsername(request)){
            log.warn(FAIL_INVALID_USERNAME);
            response.setMessage(FAIL_INVALID_USERNAME);
        }
        else if(!validatePasswordsMatch(request)){
            log.warn(FAIL_PASSWORDS_DONT_MATCH);
            response.setMessage(FAIL_PASSWORDS_DONT_MATCH);
        }
        else if(!validatePasswordValid(request)){
            log.warn(FAIL_INVALID_PASSWORD);
            response.setMessage(FAIL_INVALID_PASSWORD);
        }
        else{
            log.info("Creating new user "+request.getUser());
            registerUser(request);
            response.setSuccess(true);
            response.setMessage("Created user");
        }

        return response;
    }
    private boolean validateUsername(RegisterRequest request) {
        return request.getUser().matches(USERNAME_REGEX);
    }

    private boolean validatePasswordsMatch(RegisterRequest request) {
        return request.getPass1().equals(request.getPass2());
    }
    private boolean validatePasswordValid(RegisterRequest request) {
        return request.getPass1().matches(PASSWORD_REGEX);
    }


    private void registerUser(RegisterRequest request){
        UserDetails details = User.builder().username(request.getUser()).password(passwordEncoder.encode(request.getPass1())).roles("USER").build();
        userDetailsManager.createUser(details);
    }
}
