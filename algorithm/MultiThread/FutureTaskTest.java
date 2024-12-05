package com.chenjunyi.MultiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest implements Callable {
    @Override
    public Object call() throws Exception {
        return 12;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTaskTest futureTask = new FutureTaskTest();
        FutureTask future = new FutureTask(futureTask);
        Thread thread = new Thread(future);
        thread.start();
        System.out.println(future.get());
    }
}
