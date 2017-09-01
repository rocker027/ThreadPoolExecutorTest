package com.dayoo.threadpoolexecutortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);


    }

    private void cacheThread() {
        // 無次數限制，但不保證順序性
        ExecutorService cache = ThreadPoolFactory.newCacheThreadPool();
        final StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 10; i++) {
            Task task = new Task("Cache " + i);
            cache.execute(task);
            sb.append(task.getResult());
        }

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.textView)).setText(sb.toString());
            }
        });
    }

    private void cacheThreadV2() throws ExecutionException, InterruptedException {
        final ExecutorService cache = ThreadPoolFactory.newCacheThreadPool();
        List<Future<String>> list = new ArrayList<>();
        // 無次數限制，但不保證順序性
        for (int i = 1; i < 11; i++) {
            Task task = new Task("cache " + i);
            FactorialCalculator calculator = new FactorialCalculator(task);
            Future<String> stringFuture = cache.submit(calculator);
            list.add(stringFuture);
        }
        final StringBuilder sb = new StringBuilder();
        for (Future<String> future : list) {
            sb.append(future.get());
        }

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.textView)).setText(sb.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                ((TextView) findViewById(R.id.textView)).setText("");
//                cacheThread();
                try {
                    cacheThreadV2();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
