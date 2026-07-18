package com.demo.legacy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple REST controller used for demonstrating
 * Java modernization.
 *
 * Legacy Pattern:
 * ---------------
 * - Field Injection (@Autowired)
 *
 * Modernization:
 * --------------
 * - Constructor Injection
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    // SRAO: Replaced field injection with constructor injection.
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Sample Endpoint
     *
     * Example:
     * GET /api/orders/summary?customer=John&status=NEW
     */
    @GetMapping("/summary")
    public String getOrderSummary(
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String status) {

        return orderService.generateOrderSummary(customer, status);
    }

}
