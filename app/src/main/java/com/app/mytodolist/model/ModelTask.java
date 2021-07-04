package com.app.mytodolist.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ModelTask {
    private String startDate;
    private String taskDescription;
    //0 means done, 1 is active
    private int status;
    private String finishDate;


    public ModelTask() {
        this.taskDescription = "this is test task from empty constructor";
        this.status = 1;
        this.startDate= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        this.finishDate= null;
    }

    public ModelTask(String str) {
        this.taskDescription = str;
        this.status = 1;
        this.startDate= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        this.finishDate= null;
    }

    public ModelTask(String taskDescription, int status, String startDate, String finishDate) {
        this.taskDescription = taskDescription;
        this.status = status;
        this.startDate= startDate;
        this.finishDate= finishDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
