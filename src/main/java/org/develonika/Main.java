package org.develonika;

import org.develonika.service.impl.ToDoListImpl;

public class Main {

    public static void main(String[] args) {
        ToDoListImpl toDoList = new ToDoListImpl();
        toDoList.run();
    }
}
