package com.chenjunyi.MultiThread;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {

    private int begin;
    private int end;

    public SumTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }




    @Override
    protected Long compute() {
        long sum = 0;
        //base case
        System.out.println("thread: " + Thread.currentThread().getName());
        if (end - begin < 100) {

            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = begin + ((end - begin) >> 1);
            SumTask sumTask1 = new SumTask(begin, mid);
            SumTask sumTask2 = new SumTask(mid+1, end);
            sumTask1.fork();
            sumTask2.fork();

            long sum1 = sumTask1.join();
            long sum2 = sumTask2.join();
            sum = sum1 + sum2;

        }
        return sum;
    }

  
}
