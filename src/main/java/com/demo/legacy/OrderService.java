package com.demo.legacy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Legacy order service used for the SRAO modernization demo.
 *
 * Intentionally included patterns:
 * - Raw collections
 * - Unchecked casts
 * - Traditional loops
 * - Explicit null checks
 * - StringBuffer
 * - String concatenation
 * - Traditional switch statement
 * - Thread
 * - Runnable
 * - Anonymous inner class
 * - synchronized method
 * - Thread.sleep()
 * - Callback interface
 */
@Service
public class OrderService {

    /**
     * Legacy callback contract.
     */
    public interface OrderCallback {

        void onComplete(String result);

        void onError(Exception exception);
    }

    /**
     * Generates a simple order summary.
     *
     * Legacy patterns:
     * - Raw List
     * - Unchecked cast
     * - Indexed loop
     * - Explicit null checks
     * - StringBuffer
     * - String concatenation
     * - Traditional switch
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String generateOrderSummary(
            String customer,
            String status) {

        if (customer == null || customer.trim().length() == 0) {
            customer = "UNKNOWN";
        }

        if (status == null || status.trim().length() == 0) {
            status = "NEW";
        }

        List items = new ArrayList();

        items.add("BOOK");
        items.add("LAPTOP");

        String normalizedStatus;

        switch (status.toUpperCase()) {

            case "NEW":
                normalizedStatus = "NEW";
                break;

            case "PROCESSING":
                normalizedStatus = "PROCESSING";
                break;

            case "COMPLETED":
                normalizedStatus = "COMPLETED";
                break;

            default:
                normalizedStatus = "UNKNOWN";
                break;
        }

        StringBuffer itemBuffer = new StringBuffer();

        for (int i = 0; i < items.size(); i++) {

            String item = (String) items.get(i);

            itemBuffer.append(item);

            if (i < items.size() - 1) {
                itemBuffer.append(",");
            }
        }

        return "Customer: "
                + customer
                + " | Status: "
                + normalizedStatus
                + " | Items: "
                + itemBuffer.toString()
                + " | Processed: true";
    }

    /**
     * Simulates asynchronous legacy order processing.
     *
     * Legacy patterns:
     * - synchronized method
     * - Thread
     * - Runnable
     * - Anonymous inner class
     * - Blocking Thread.sleep()
     * - Callback interface
     */
    public synchronized void processOrderAsync(
            final String customer,
            final OrderCallback callback) {

        if (callback == null) {
            return;
        }

        Thread worker = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    Thread.sleep(500);

                    String result =
                            "Order processed for customer: " + customer;

                    callback.onComplete(result);

                } catch (InterruptedException exception) {

                    Thread.currentThread().interrupt();

                    callback.onError(exception);

                } catch (Exception exception) {

                    callback.onError(exception);
                }
            }
        });

        worker.start();
    }
}