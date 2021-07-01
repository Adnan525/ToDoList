package com.app.mytodolist;

public class ModelTask {
    private String taskDescription;
    private boolean status;


    public ModelTask() {
        this.taskDescription = "this is a test task";
        this.status = true;
    }
    public ModelTask(String str) {
        this.taskDescription = str;
        this.status = true;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
