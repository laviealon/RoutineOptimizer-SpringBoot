package com.csc207.api;

import com.csc207.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;


/**
 * This class is responsible for saving a week and its tasks into the database using weekSerializableInteractorDataIn
 * and taskSerializableInteractorDataIn.
 */

@RestController
public class WeekController {
    @Autowired
    private final WeekSerializableInteractorDataIn weekSerializableInteractorDataIn;
    @Autowired
    private final TaskSerializableInteractorDataIn taskSerializableInteractorDataIn;
    @Autowired
    private WeekSerializableInteractorDataOut weekSerializableInteractorDataOut;

    /**
     * The constructor for the WeekController class.
     * @param weekSerializableInteractorOut: The interactor used to access data from the week database.
     * @param weekSerializableInteractorDataIn: The interactor used to save data to the week database.
     * @param taskSerializableInteractorDataIn: The interactor used to save data to the task database.
     */
    public WeekController(WeekSerializableInteractorDataOut weekSerializableInteractorOut,
                          WeekSerializableInteractorDataIn weekSerializableInteractorDataIn,
                          TaskSerializableInteractorDataIn taskSerializableInteractorDataIn) {
        this.weekSerializableInteractorDataIn = weekSerializableInteractorDataIn;
        this.taskSerializableInteractorDataIn = taskSerializableInteractorDataIn;
        this.weekSerializableInteractorDataOut = weekSerializableInteractorOut;
    }


    @GetMapping("/weeks/{userid}")
    @CrossOrigin
    public WeekSerializable getWeeks(@PathVariable String userid){
        return this.weekSerializableInteractorDataOut.getWeekSerializableByUserId(Long.valueOf(userid));
    }

    /**
     * Saves the week and its tasks to the week database and the task database, respectively.
     * @param week: The week that will be saved to the database.
     */
    @Transactional
    public void saveWeek(Week week) {
        // convert to week serializable
        WeekSerializable convertedWeek = WeekToSerializableAdapter.WeekToWeekSerializable(week);
        // convert to task serializable
        ArrayList<TaskSerializable> convertedTasks = WeekToSerializableAdapter.WeekToTaskSerializable(week);
        // save weekSerializable
        this.weekSerializableInteractorDataIn.saveWeekSerializable(convertedWeek);
        // save taskSerializable
        for(TaskSerializable task: convertedTasks) {
            this.taskSerializableInteractorDataIn.saveTaskSerializable(task);
        }
    }

    @PostMapping("/weeks/remove/{userid}")
    @CrossOrigin
    public void removeWeekByUserId(@PathVariable String userid){
        this.weekSerializableInteractorDataIn.removeWeekByUserId(Long.parseLong(userid));
    }

    @PostMapping("/weeks")
    @CrossOrigin
    @Transactional
    public void saveWeekSerializable(WeekSerializable week){
        this.weekSerializableInteractorDataIn.saveWeekSerializable(week);
    }



}
