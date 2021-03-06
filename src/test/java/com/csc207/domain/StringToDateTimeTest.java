package com.csc207.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

/**
 * This tests the StringToDateTime class.
 */

public class StringToDateTimeTest {

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    /**
     * Tests stringToLocalDate method with a string to LocalDate
     */
    @Test
    public void stringToLocalDate() {
        String time = "2021-12-08";
        LocalDate date = LocalDate.of(2021, 12,8);
        assertEquals(StringToDateTime.stringToLocalDate(time), date);
    }

    /**
     * Tests stringToLocalTime method with a string to LocalTim
     */
    @Test
    public void stringToLocalTime() {
        String time = "12:00";
        LocalTime newTime = LocalTime.of(12, 0);
        assertEquals(StringToDateTime.stringToLocalTime(time), newTime);
    }

    /**
     * Tests stringToLocalDateTime method with a string to LocalDateTime
     */
    @Test
    public void stringToLocalDateTime() {
        String time = "2021-12-08$12:00";
        LocalDateTime newTime = LocalDateTime.of(2021, 12, 8, 12, 0);
        assertEquals(StringToDateTime.stringToLocalDateTime(time), newTime);
    }

}

