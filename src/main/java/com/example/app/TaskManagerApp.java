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
            System.out.println("1 - Create simple task\n2 - Create priority task\n" +
                    "3 - Create task with deadline\n4 - Show current task\n" +
                    "5 - Do current task\n6 - Search task by name\n7 - Search task by date of creation\n" +
                    "8 - Delete task by name\n9 - Delete task by name and date of creation\n0 - Exit");
            System.out.print("Input your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    createPriorityTask();
                    break;
                case 3:
                    createTaskWithDeadline();
                    break;
                case 4:
                    showTask();
                    break;
                case 5:
                    doTask();
                    break;
                case 6:
                    showAllTasksByName();
                    break;
                case 7:
                    showAllTasksByDateOfCreation();
                    break;
                case 8:
                    deleteTaskByName();
                    break;
                case 9:
                    deleteTaskByNameAndDate();
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

    private static void createPriorityTask() {
        System.out.print("Input name of task: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Input description: ");
        String description = scanner.nextLine();
        System.out.print("Input priority: ");
        int priority = scanner.nextInt();
        manager.addTask(new Task(name, description, priority));
    }

    private static void createTaskWithDeadline() {
        System.out.print("Input name of task: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Input description: ");
        String description = scanner.nextLine();
        System.out.print("Input deadline(yyyy-mm-dd): ");
        String deadline = scanner.nextLine();
        System.out.print("Input priority: ");
        int priority = scanner.nextInt();
        manager.addTask(new Task(name, description, priority, deadline));
    }

    private static void showTask() {
        Task task = manager.getCurrentTask();
        System.out.println(task == null ? "\nTask manager is empty!\n" : task);
    }

    private static void doTask() {
        Task task = manager.doCurrentTask();
        if (task == null)
            System.out.println("\nTask manager is empty!\n");
        else
            System.out.println(task +  "Task was completed!\n" +
                    "==================================");
    }

    private static void showAllTasksByName() {
        System.out.print("Input name of task: ");
        scanner.nextLine();
        for (Task task : manager.searchTasksByName(scanner.nextLine())) {
            System.out.println(task);
        }
    }

    private static void showAllTasksByDateOfCreation() {
        System.out.print("Input date of task: ");
        scanner.nextLine();
        for (Task task : manager.searchTasksByDateOfCreation(scanner.nextLine())) {
            System.out.println(task);
        }
    }

    private static void deleteTaskByName() {
        System.out.print("Input name of task: ");
        scanner.nextLine();
        if (manager.deleteTask(scanner.nextLine()))
            System.out.println("\nTask(s) was/were deleted!\n");
        else
            System.out.println("\nThere aren't task with this name!\n");
    }

    private static void deleteTaskByNameAndDate() {
        System.out.print("Input name of task: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Input date of creation(yyyy-mm-dd): ");
        String date = scanner.nextLine();
        if (manager.deleteTask(name, date))
            System.out.println("\nTask(s) was/were deleted!\n");
        else
            System.out.println("\nThere aren't task with this name and this date!\n");
    }

}
