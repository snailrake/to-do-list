package org.develonika.service.impl;

import org.develonika.entity.Task;
import org.develonika.service.ToDoList;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ToDoListImpl implements ToDoList {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Task> tasks = new ArrayList<>();
    private boolean running = true;

    public void run() {
        System.out.println("Добро пожаловать в приложение To-Do List!");
        System.out.println("1. Добавить новую задачу");
        System.out.println("2. Просмотреть список задач");
        System.out.println("3. Отметить задачу как выполненную");
        System.out.println("4. Удалить задачу");
        System.out.println("5. Выход");
        int choise;
        do {
            System.out.print("Выберите действие (введите номер): ");
            choise = getIndex() + 1;
            switch (choise) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> completeTask();
                case 4 -> removeTask();
                case 5 -> leave();
                default -> System.out.println("Некорректное действие. Пожалуйста введите номер от 1 до 5");
            }
        } while (running);
    }

    @Override
    public void addTask() {
        System.out.print("Введите описание задачи: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        if (description.isBlank()) {
            System.out.println("Задача должна иметь описание");
        }
        tasks.add(new Task(description));
    }

    @Override
    public void viewTasks() {
        System.out.print("Список задач:");
        if (tasks.isEmpty()) {
            System.out.println(" (пусто)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%n%d. %s", i + 1, tasks.get(i));
            }
            System.out.println();
        }
    }

    @Override
    public void completeTask() {
        System.out.print("Введите номер задачи для отметки как выполненной: ");
        int index = getIndex();
        if (index < 0 || index > tasks.size()) {
            System.out.println(String.format("Задачи под номером %d не существует", index));
            return;
        }
        Task task = tasks.get(index);
        task.markCompleted();
        System.out.printf("Задача \"%s\" выполнена%n", task.getDescription());
    }

    @Override
    public void removeTask() {
        System.out.print("Введите номер задачи для удаления: ");
        int index = getIndex();
        if (index < 0 || index > tasks.size()) {
            System.out.println(String.format("Задачи под номером %d не существует", index + 1));
            return;
        }
        tasks.remove(index);
    }

    @Override
    public void leave() {
        System.out.println("До свидания!");
        running = false;
    }

    private int getIndex() {
        while (true) {
            try {
                return scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Некорректный ввод. Пожалуйста, введите число: ");
            }
        }
    }
}
