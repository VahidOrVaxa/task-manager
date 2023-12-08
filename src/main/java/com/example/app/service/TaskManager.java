package com.example.app.service;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskManager {
    private Queue<Task> tasks;

    public TaskManager() {
        tasks = new PriorityQueue<>();
    }

    public boolean addTask(Task task) {
        try {
            tasks.offer(task);
            return true;
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            System.err.println("There are no object in function!");
            return false;
        }
    }

    public Task getCurrentTask() throws NullPointerException {
        return tasks.element();
    }

    public Task doCurrentTask() throws NullPointerException {
        return tasks.remove();
    }

    public List<Task> searchTasksByName(final String name) {
        return tasks.stream().filter(x -> x.getName().equals(name)).toList();
    }

    public List<Task> searchTasksByDateOfCreation(final String date) {
        return tasks.stream().filter(x -> x.getSimpleData().equals(date)).sorted().toList();
    }

}
