package com.meanmachines.MeanStreamMachine.service;

import com.meanmachines.MeanStreamMachine.model.dto.request.RegisterRequest;
import com.meanmachines.MeanStreamMachine.model.dto.response.RegisterResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRegistrationManagerTest {

    static UserRegistrationManager subject = new UserRegistrationManager();
    static UserDetailsManager mockUserDetailsManager = mock(UserDetailsManager.class);

    @BeforeAll
    static void setUp() {
        subject.userDetailsManager = mockUserDetailsManager;
        subject.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void createNewUserSuccess() {
        doNothing().when(mockUserDetailsManager).createUser(any());
        RegisterRequest request = new RegisterRequest("validuser", "ValidPass1", "ValidPass1");
        RegisterResponse response = subject.createNewUser(request);
        assertTrue(response.isSuccess());
    }

    @Test
    void createNewUserBadUser() {
        doNothing().when(mockUserDetailsManager).createUser(any());
        RegisterRequest[] requests = {
                new RegisterRequest("", "ValidPass1", "ValidPass1"),
                new RegisterRequest("thisusernameisprobablytoolong", "ValidPass1", "ValidPass1"),
                new RegisterRequest(null, "ValidPass1", "ValidPass1")
        };
        for(RegisterRequest request : requests){
            RegisterResponse response = subject.createNewUser(request);
            assertAll(()->{
                assertFalse(response.isSuccess());
                assertEquals(UserRegistrationManager.FAIL_INVALID_USERNAME, response.getMessage());
            });

        }

    }

    @Test
    void createNewUserBadPass() {
        doNothing().when(mockUserDetailsManager).createUser(any());
        RegisterRequest[] requests = {
                new RegisterRequest("user", "", "ValidPass1"),
                new RegisterRequest("user", "thispasswordisprobablytoolongthelimitis32charssoiwouldimaginethisdoesntpass", "ValidPass1"),
                new RegisterRequest("user", "singlecase", "ValidPass1"),
                new RegisterRequest("user", "MixedCaseNoNums", "ValidPass1"),
                new RegisterRequest("user", "!!!aaaa!!!!!aa!!!!", "ValidPass1"),
                new RegisterRequest("user", "!!!!!!!!!!!!!", "ValidPass1"),
                new RegisterRequest("user", "ThisIsInvalid!", "ValidPass1"),
                new RegisterRequest("user", "ThisIsInvalid2!", "ValidPass1"),
                new RegisterRequest("user", null, "ValidPass1")
        };
        for(RegisterRequest request : requests){
            RegisterResponse response = subject.createNewUser(request);
            assertAll(()->{
                assertFalse(response.isSuccess());
                assertEquals(UserRegistrationManager.FAIL_INVALID_PASSWORD, response.getMessage());
            });

        }

    }

    @Test
    void createNewUserPassDontMatch() {
        doNothing().when(mockUserDetailsManager).createUser(any());
        RegisterRequest[] requests = {
                new RegisterRequest("user", "ValidPass1", "ValidPass2"),
                new RegisterRequest("user", "ValidPass1", "notevenvalid")
        };
        for(RegisterRequest request : requests){
            RegisterResponse response = subject.createNewUser(request);
            assertAll(()->{
                assertFalse(response.isSuccess());
                assertEquals(UserRegistrationManager.FAIL_PASSWORDS_DONT_MATCH, response.getMessage());
            });

        }

    }

}