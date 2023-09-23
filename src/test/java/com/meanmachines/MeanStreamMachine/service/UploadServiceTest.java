package com.meanmachines.MeanStreamMachine.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploadServiceTest {

    @Test
    void getCanonicalName() {
        String original = "   white    space is  cool  2  ";
        String expected = "white_space_is_cool_2";

        assertEquals(expected, MediaService.toCanonicalName(original));
    }
}