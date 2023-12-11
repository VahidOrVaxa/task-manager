package com.example.app.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskManager {
    private Queue<Task> tasks;
    private SessionFactory factory;
    private Session session;

    public TaskManager() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Task.class)
                .buildSessionFactory();

        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Task> list = session.createQuery("from Task").getResultList();
        session.getTransaction().commit();

        tasks = new PriorityQueue<>(list);
    }

    public boolean addTask(Task task) {
        try {
            tasks.offer(task);
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(tasks);
            session.getTransaction().commit();
            return true;
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            System.err.println("There are no object in function!");
            return false;
        }
    }

    public List<Task> getAllTasks() {
        return tasks.stream().sorted().toList();
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

    public boolean deleteTask(int ID) {
        Iterator<Task> taskIterator = tasks.iterator();
        boolean trigger = false;
        while (taskIterator.hasNext()) {
            if (taskIterator.next().getID() == ID) {
                taskIterator.remove();
                trigger = true;
            }
        }
        return trigger;
    }

    public void destroy() {
        factory.close();
    }

}
