package jug.istanbul.adapter.in.web;

import jug.istanbul.application.port.in.DiscountUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/discounts")
// Adapter: This is an "Inbound Adapter" (or Driving Adapter).
// It translates external HTTP requests into internal application commands.
public class DiscountController {

    // Port: The adapter communicates with the core logic via the "Input Port" (DiscountUseCase interface).
    // It does NOT depend on the implementation (DiscountService).
    private final DiscountUseCase discountUseCase;

    public DiscountController(DiscountUseCase discountUseCase) {
        this.discountUseCase = discountUseCase;
    }

    @PostMapping("/apply")
    public double apply(@RequestBody Map<String, Double> request) {
        return discountUseCase.applyDiscount(request.get("amount"));
    }
}
