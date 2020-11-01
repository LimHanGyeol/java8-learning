package com.tommy.java8learning.study;

import org.springframework.context.annotation.Description;

import java.util.Optional;

public class OnlineClass {

    private final int id;
    private final String title;
    private final boolean closed;
    private Progress progress;

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

    @Description("Optional 은 Return Type 에만 사용하는 것이 권장된다.")
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }

}
