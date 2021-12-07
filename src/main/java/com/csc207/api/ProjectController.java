package com.csc207.api;

import com.csc207.domain.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class ProjectController {
    @Autowired
    private final WeekSerializableInteractorDataOut weekSerializableInteractorDataOut;
    @Autowired
    private final TaskSerializableInteractorDataOut taskSerializableInteractorDataOut;

    public ProjectController(WeekSerializableInteractorDataOut weekSerializableInteractorDataOut,
                             TaskSerializableInteractorDataOut taskSerializableInteractorDataOut){
        this.weekSerializableInteractorDataOut = weekSerializableInteractorDataOut;
        this.taskSerializableInteractorDataOut = taskSerializableInteractorDataOut;
    }

    @GetMapping("/project/calculatehours/{userid}/{totalHoursStr}/{startDateStr}/{dueDateTimeStr}")
    @CrossOrigin
    JSONObject calculateHours(@PathVariable("userid") String userid,
                              @PathVariable("totalHoursStr") String totalHoursStr,
                              @PathVariable("startDateStr") String startDateStr,
                              @PathVariable("dueDateTimeStr") String dueDateTimeStr) throws JSONException {
//        Week week = importWeek(Long.getLong(userid));
//        Week week = new Week(Long.getLong(userid) * 1000, DaysInjector.constructDayList(LocalDate.of(2021, 10, 10)));
        LocalDate startDate = StringToDateTime.stringToLocalDate(startDateStr);
        LocalDateTime dueDateTime = StringToDateTime.stringToLocalDateTime(dueDateTimeStr);
        int totalHours = Integer.parseInt(totalHoursStr);
//        double minHours = CreateProject.calculateMinHours(week, startDate, dueDateTime, totalHours, 7);
//        double maxHours = CreateProject.calculateMaxHoursWeek(week);
        double minHours = 10;
        double maxHours = 10;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("minHours", minHours);
        jsonObject.put("maxHours", maxHours);
        return jsonObject;
    }

    public Week importWeek(long userId) {
        Week week;
        WeekSerializable weekSers = this.weekSerializableInteractorDataOut.getWeekSerializableByUserId(userId);
        this.weekSerializableInteractorDataOut.removeWeekSerializableByUserId(userId);
        ArrayList<TaskSerializable> tasksSers = this.taskSerializableInteractorDataOut.getTasksByUserId(userId);
        this.taskSerializableInteractorDataOut.removeTaskSerializablesByUserId(userId);
        week = SerializableToWeekAdapter.SerializableToWeek(weekSers, tasksSers);
        return week;
    }
}
