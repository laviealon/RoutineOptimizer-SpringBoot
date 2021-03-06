package com.csc207.cli;

import com.csc207.domain.DaysInjector;
import com.csc207.domain.FixedTask;
import com.csc207.domain.NonFixedTask;
import com.csc207.domain.Week;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertTrue;

public class CheckerControllerTest {

    Week week;
    FixedTask task1;
    NonFixedTask task2;
    NonFixedTask[] tasks;
    @Before
    public void setUp(){
        LocalDate startDate = LocalDate.of(2021, 4, 3);
        week = new Week(1L, DaysInjector.constructDayList(startDate));
        task1 = new FixedTask("Piano Practice", LocalDateTime.of(2021, 4, 3, 2, 0), LocalTime.of(6, 0), 1L);
        task2 = new NonFixedTask("Skipping", LocalDateTime.of(2021, 4, 3, 2, 0), LocalTime.of(6, 0), 2L);
        tasks = new NonFixedTask[]{task2};
    }

    @After
    public void tearDown(){
    }
    /**
     * Tests the checkProjectScheduling method.
     */
    @Test
    public void testCheckNonFixedTaskScheduling(){
        assertTrue(CheckerController.checkFixedTaskScheduling(week, task1));
    }

    /**
     * Tests the checkProjectScheduling method.
     */
    @Test
    public void testCheckProjectScheduling(){
        assertTrue(CheckerController.checkProjectScheduling(week, tasks));
    }

    /**
     * Tests the checkFixedTaskScheduling method.
     */
    @Test
    public void testCheckFixedTaskScheduling(){
        assertTrue(CheckerController.checkNonFixedTaskScheduling(week, task2));
    }
}
