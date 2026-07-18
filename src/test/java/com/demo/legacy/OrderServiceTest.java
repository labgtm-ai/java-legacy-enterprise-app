package com.demo.legacy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void shouldGenerateOrderSummary() {

        String result = orderService.generateOrderSummary(
                "John",
                "NEW"
        );

        assertEquals(
                "Customer: John | Status: NEW | Items: BOOK,LAPTOP | Processed: true",
                result
        );
    }

    @Test
    public void shouldUseDefaultValuesWhenInputsAreNull() {

        String result = orderService.generateOrderSummary(
                null,
                null
        );

        assertEquals(
                "Customer: UNKNOWN | Status: NEW | Items: BOOK,LAPTOP | Processed: true",
                result
        );
    }

    @Test
    public void shouldReturnUnknownForUnsupportedStatus() {

        String result = orderService.generateOrderSummary(
                "Mary",
                "INVALID"
        );

        assertEquals(
                "Customer: Mary | Status: UNKNOWN | Items: BOOK,LAPTOP | Processed: true",
                result
        );
    }

    @Test
    public void shouldProcessOrderAsynchronously() throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);

        final AtomicReference<String> successResult =
                new AtomicReference<String>();

        final AtomicReference<Exception> errorResult =
                new AtomicReference<Exception>();

        // SRAO: Updated to use the modernized processOrderAsync method which returns CompletableFuture.
        CompletableFuture<String> future = orderService.processOrderAsync(
                "John"
        );

        // SRAO: Replaced the custom OrderCallback with CompletableFuture's whenComplete callback.
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                successResult.set(result);
            } else {
                // Wrap the Throwable in an Exception to match the original test's AtomicReference type.
                errorResult.set(new Exception(exception));
            }
            latch.countDown();
        });

        boolean completed = latch.await(
                2,
                TimeUnit.SECONDS
        );

        assertTrue(
                completed,
                "The asynchronous order processing did not complete in time."
        );

        assertEquals(
                "Order processed for customer: John",
                successResult.get()
        );

        assertEquals(
                null,
                errorResult.get()
        );
    }
}