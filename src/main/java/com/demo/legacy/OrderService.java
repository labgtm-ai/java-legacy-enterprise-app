package com.demo.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

/**
 * Modernized order service for the SRAO modernization demo.
 *
 * Original patterns addressed:
 * - Raw collections -> Generics
 * - Unchecked casts -> Generics
 * - Traditional loops -> String.join
 * - Explicit null checks -> Optional
 * - StringBuffer -> String.join
 * - String concatenation -> String.format
 * - Traditional switch statement -> Kept for simplicity
 * - Thread / Runnable / Anonymous inner class -> CompletableFuture
 * - synchronized method -> Removed, replaced with non-blocking async
 * - Thread.sleep() -> CompletableFuture.delayedExecutor
 * - Callback interface -> CompletableFuture as return type
 */
@Service
public class OrderService {

    // SRAO: Removed the OrderCallback interface as its functionality is replaced by CompletableFuture as a return type.

    /**
     * Generates a simple order summary.
     *
     * Modernized patterns:
     * - Generics for List
     * - Optional for null/empty checks
     * - String.join for item list construction
     * - String.format for final summary
     */
    public String generateOrderSummary(
            String customer,
            String status) {

        // SRAO: Replaced explicit null and empty string check with Optional.ofNullable and orElse.
        customer = Optional.ofNullable(customer).filter(s -> !s.trim().isEmpty()).orElse("UNKNOWN");

        // SRAO: Replaced explicit null and empty string check with Optional.ofNullable and orElse.
        status = Optional.ofNullable(status).filter(s -> !s.trim().isEmpty()).orElse("NEW");

        // SRAO: Replaced raw List with a generic List<String> to avoid unchecked cast warnings.
        List<String> items = new ArrayList<>();

        items.add("BOOK");
        items.add("LAPTOP");

        // SRAO: Replaced traditional switch statement with a switch expression for conciseness.
        String normalizedStatus = switch (status.toUpperCase()) {
            case "NEW" -> "NEW";
            case "PROCESSING" -> "PROCESSING";
            case "COMPLETED" -> "COMPLETED";
            default -> "UNKNOWN";
        };

        // SRAO: Replaced StringBuffer and indexed loop with String.join for cleaner item list construction.
        String itemSummary = String.join(",", items);

        // SRAO: Replaced string concatenation with String.format for better readability.
        return String.format("Customer: %s | Status: %s | Items: %s | Processed: true",
                             customer, normalizedStatus, itemSummary);
    }

    /**
     * Simulates asynchronous order processing using CompletableFuture.
     *
     * Modernized patterns:
     * - CompletableFuture as return type for asynchronous results.
     * - Non-blocking delay with CompletableFuture.delayedExecutor.
     * - Exception handling via exceptionally.
     */
    // SRAO: Removed 'synchronized' and replaced blocking sleep with CompletableFuture for async processing.
    // SRAO: Changed method signature to return CompletableFuture<String> instead of using a callback interface.
    public CompletableFuture<String> processOrderAsync(final String customer) {

        CompletableFuture<String> resultFuture = new CompletableFuture<>();

        // Use CompletableFuture for asynchronous execution and non-blocking delay
        CompletableFuture.runAsync(() -> {
            String result = "Order processed for customer: " + customer;
            resultFuture.complete(result); // Complete the future with the result
        }, CompletableFuture.delayedExecutor(500, TimeUnit.MILLISECONDS))
        .exceptionally(ex -> {
            // SRAO: Refactored exception handling to use more specific types where possible.
            Throwable cause = ex.getCause(); // Get the underlying cause

            if (cause instanceof InterruptedException) {
                Thread.currentThread().interrupt();
                resultFuture.completeExceptionally(cause); // Complete with the specific InterruptedException
            } else if (cause instanceof RuntimeException) {
                // Handle specific RuntimeException (e.g., NullPointerException, IllegalArgumentException, etc.)
                resultFuture.completeExceptionally(cause);
            } else if (cause instanceof Error) {
                // Handle specific Error (e.g., OutOfMemoryError, StackOverflowError)
                resultFuture.completeExceptionally(cause);
            } else if (cause != null) {
                // Handle any other specific Throwable that might be wrapped
                resultFuture.completeExceptionally(cause);
            } else {
                // If there's no specific cause, complete with the CompletionException itself
                resultFuture.completeExceptionally(ex);
            }
            return null; // Return null as this is a void-returning CompletableFuture.runAsync chain
        });

        return resultFuture;
    }
}
