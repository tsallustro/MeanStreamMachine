package com.meanmachines.MeanStreamMachine.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploadServiceTest {

    @Test
    void getCanonicalName() {
        String original = "   white    space is cool    ";
        String expected = "white_space_is_cool";

        assertEquals(expected, UploadService.toCanonicalName(original));
    }
}