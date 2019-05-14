package com.wuhulala.javase.async;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.concurrent.CompletableFuture;



/**
 * 功能
 *
 * @author xueah20964 2019/4/9 Create 1.0  <br>
 * @version 1.0
 */
public class CompletableFutureDemo {

    @DisplayName("thenApply")
    @Test
    public void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            Assertions.assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        Assertions.assertEquals("MESSAGE", cf.getNow(null));
    }

}
