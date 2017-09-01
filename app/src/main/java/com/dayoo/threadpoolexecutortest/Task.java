package com.dayoo.threadpoolexecutortest;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;

/**
 * Created by rocker on 2017/8/30.
 */

public class Task implements Runnable {
    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        SystemClock.sleep(1000);
        Log.d("TASK", "Task name :" + getTaskName());
        getResult();
    }

    public String getResult() {
        return getTaskName();
    }

}
