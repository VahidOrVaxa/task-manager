package com.example.app;

import com.example.app.service.Task;
import com.example.app.service.TaskManager;

import java.util.Scanner;

public class TaskManagerApp {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskManager manager = new TaskManager();


    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("1 - Create task\n2 - Show current task\n" +
                    "3 - Do current task\n4 - Search task by name");
            System.out.print("Input your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    showTask();
                    break;
                case 3:
                    doTask();
                    break;
                case 4:
                    showAllTaskByName();
                    break;
            }
        } while (choice!=0);
    }

    private static void createTask() {
        System.out.print("Input name of task: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Input description: ");
        String description = scanner.nextLine();
        manager.addTask(new Task(name, description));
    }

    private static void showTask() {
        System.out.println(manager.getCurrentTask());
    }

    private static void doTask() {
        System.out.println(manager.doCurrentTask() +  "Task was completed!\n" +
                "==================================");
    }

    private static void showAllTaskByName() {
        System.out.print("Input name of task: ");
        scanner.nextLine();
        for (Task task : manager.searchTasksByName(scanner.nextLine())) {
            System.out.println(task);
        }

    }

}
