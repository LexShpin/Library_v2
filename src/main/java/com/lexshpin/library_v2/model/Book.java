package com.lexshpin.library_v2.model;

import java.util.Optional;

public class Book {

    private int id;
    private String title;
    private String author;
    private int year;
    private Optional<Integer> assignedTo;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Optional<Integer> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Optional<Integer> assignedTo) {
        this.assignedTo = assignedTo;
    }
}
