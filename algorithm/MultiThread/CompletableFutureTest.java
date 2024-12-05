package com.chenjunyi.MultiThread;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * jdk5 中的future， 用来表示一个异步执行的结果，可以使用isDone检查计算是否完成，或者使用get获取结果，直到计算完成
 * jdk8 中的completeFuture提供了更强大的future扩展功能
 *
 *
 * 异步分片请求： 分批并行调用，最后将结果进行聚合
 *
 */
public class CompletableFutureTest {


    public static void calculateAsync() {
        CompletableFuture<String> future = new CompletableFuture<>();
        
    }


    public static CompletableFuture<List<String>> rpcRequestAsync(List<Integer> ids) {
        return CompletableFuture.supplyAsync(()->rpcRequest(ids));
    }

    private static List<String> rpcRequest(List<Integer> ids) {
        return ids.stream().map(i->"NO."+i).collect(Collectors.toList());
    }

    public static void testShardRequest() {
        long start = System.currentTimeMillis();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(6);
        List<List<Integer>> partition = new ArrayList<>();
        for (int i =0; i < ids.size()-1; i+=2) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(ids.get(i));
            tmp.add(ids.get(i+1));
            partition.add(tmp);
        }
        // 发起异步并发请求
        List<CompletableFuture<List<String>>> futures = partition.stream().map(part -> rpcRequestAsync(part)).collect(Collectors.toList());
        System.out.println((System.currentTimeMillis()-start) + " ms");
        // 获取结果并将结果合并
        List<String> collect = futures.stream().flatMap(future -> future.join().stream()).collect(Collectors.toList());
        System.out.println(collect); // [NO.1, NO.2, NO.3, NO.4, NO.5, NO.6]


        //
//        List<String> mergedRet = partition.stream().map(part -> rpcRequestAsync(part))
//                .flatMap(future -> future.join().stream())
//                .collect(Collectors.toList());
//        System.out.println(mergedRet); // [NO.1, NO.2, NO.3, NO.4, NO.5, NO.6]

        System.out.println((System.currentTimeMillis() - start) + " ms"); // 3078ms  可以看到耗时和单次 rpc 耗时接近，达到并发调用的目的
        
    }

    public static void main(String[] args) {
        
       testShardRequest();
    }

}
