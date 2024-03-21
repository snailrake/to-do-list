package org.develonika.entity;

public class Task {

    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", completed ? "[X]" : "[ ]", description);
    }
}
