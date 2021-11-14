package com.csc207.cli;

import entities.FixedTask;
import entities.NonFixedTask;
import entities.Week;

public class Controller {

    /**
     * Initiates scheduling and putting of fixed task if user chooses
     * to add one to their schedule in UI.
     */
    public static void activateFixedTaskScheduling(Week week, FixedTask taskToPut){
        Putter.putTask(week, taskToPut);
    }

    public static void activateNonFixedTaskScheduling(Week week, NonFixedTask taskToSchedule){
        NonFixedTask NonFixedTaskToPut = Scheduler.ScheduleTaskInWeek(week, taskToSchedule);
        Putter.putTask(week, NonFixedTaskToPut);
    }

    /**
     * Initiate scheduling and putting of project based on user preferences
     */
    public static void activateProjectScheduling(Week week, NonFixedTask[] projectTasksToSchedule){
        NonFixedTask[] projectTasksToPut = Scheduler.ScheduleProject(week, projectTasksToSchedule);
        Putter.putProject(projectTasksToPut[0].getName(), week, projectTasksToPut);
    }

    public static boolean checkNonFixedTaskScheduling(Week week,NonFixedTask taskToSchedule){
        return Checker.CheckScheduleNonFixedTask(week, taskToSchedule);
    }

    public static boolean checkProjectScheduling(Week week,NonFixedTask[] projectToSchedule){
        return Checker.CheckScheduleProject(week, projectToSchedule);
    }

    public static boolean checkFixedTaskScheduling(Week week,FixedTask taskToSchedule){
        return Checker.CheckScheduleFixedTask(week, taskToSchedule);
    }

}