package com.example.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable{
    private String name;
    private String description;
    private Date currentDate;
    private Date deadline = null;
    private int priority = 99;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.currentDate = new Date();
    }

    public Task(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.currentDate = new Date();
        this.priority = priority;
    }

    public Task(String name, String description, int priority, String deadline) {
        this.name = name;
        this.description = description;
        this.currentDate = new Date();
        this.priority = priority;
        try {
            this.deadline = dateFormat.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("This task has not deadline!");
        }

    }

    @Override
    public int compareTo(Object o) {
        Task task = (Task) o;
        int result = priority - task.priority;
        if (result != 0) {
            return result;
        } else {
            return currentDate.compareTo(task.currentDate);
        }
    }

    @Override
    public String toString() {
        return "Task:\n" +
                "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "Data of creation: " + currentDate + "\n" +
                "Deadline: " + (deadline==null ? "No deadline!" : deadline) + "\n" +
                "Priority: " + (priority == 99 ? "No priority!" : priority) + "\n" +
                "==================================\n";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getFullData() {
        return currentDate;
    }

    public String getSimpleData(){
        return dateFormat.format(currentDate);
    }

    public int getPriority() {
        return priority;
    }

    public String getDeadline() {
        return dateFormat.format(deadline);
    }
}
