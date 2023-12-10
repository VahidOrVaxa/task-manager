package com.example.app.service;

import java.util.Iterator;
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

    public Task getCurrentTask() {
        return tasks.peek();
    }

    public Task doCurrentTask() {
        return tasks.poll();
    }

    public List<Task> searchTasksByName(final String name) {
        return tasks.stream().filter(x -> x.getName().equals(name)).toList();
    }

    public List<Task> searchTasksByDateOfCreation(final String date) {
        return tasks.stream().filter(x -> x.getSimpleCreationDate().equals(date)).sorted().toList();
    }

    public boolean deleteTask(String name) {
        Iterator<Task> taskIterator = tasks.iterator();
        boolean trigger = false;
        while (taskIterator.hasNext()) {
            if (taskIterator.next().getName().equals(name)) {
                taskIterator.remove();
                trigger = true;
            }
        }
        return trigger;
    }

    public boolean deleteTask(String name, String date) {
        Iterator<Task> taskIterator = tasks.iterator();
        boolean trigger = false;
        while (taskIterator.hasNext()) {
            Task task = taskIterator.next();
            if (task.getName().equals(name) && task.getSimpleCreationDate().equals(date)) {
                taskIterator.remove();
                trigger = true;
            }
        }
        return trigger;
    }

}
