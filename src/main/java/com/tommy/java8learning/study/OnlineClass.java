package com.tommy.java8learning.study;

public class OnlineClass {

    private final int id;
    private final String title;
    private final boolean closed;

    public OnlineClass(int id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public boolean isContainsToTitle(String value) {
        return this.title.contains(value);
    }

    public boolean isStartsWithToTitle(String value) {
        return this.title.startsWith(value);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isClosed() {
        return closed;
    }
}
