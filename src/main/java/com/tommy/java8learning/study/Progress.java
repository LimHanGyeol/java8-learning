package com.tommy.java8learning.study;

import java.time.Duration;

public class Progress {

    private Duration studyDuration;
    private boolean finished;

    public Duration getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(Duration studyDuration) {
        this.studyDuration = studyDuration;
    }

    public boolean isFinished() {
        return finished;
    }
}
