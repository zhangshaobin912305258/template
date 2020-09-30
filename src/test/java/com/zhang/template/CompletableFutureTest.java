package com.zhang.template;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*@SpringBootTest
@RunWith(SpringRunner.class)*/
public class CompletableFutureTest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 1;
            return i;
        }, executorService).handle((t, u) -> {
            if (t != null) {
                System.out.println(t);
                return t * 2;
            }
            System.out.println("异常:" + u);
            return 0;
        });
        System.out.println(handle.get());
    }
}
