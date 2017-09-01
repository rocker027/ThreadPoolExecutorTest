package com.dayoo.threadpoolexecutortest;

import java.util.concurrent.Callable;

/**
 * Created by rocker on 2017/9/1.
 */

public class FactorialCalculator implements Callable<String> {
    private String name ;

    public FactorialCalculator(Task task) {
        this.name = task.getResult();
    }

    @Override
    public String call() throws Exception {

        return getName();
    }

    public String getName() {
        return name;
    }
}
