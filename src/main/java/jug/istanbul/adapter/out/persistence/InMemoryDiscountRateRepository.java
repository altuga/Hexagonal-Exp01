package jug.istanbul.adapter.out.persistence;

import jug.istanbul.domain.Discount;
import jug.istanbul.domain.port.DiscountRateRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDiscountRateRepository implements DiscountRateRepository {
    private final List<Discount> discounts;

    public InMemoryDiscountRateRepository() {
        this.discounts = new ArrayList<>();
        // Initialize with default discount rules
        discounts.add(new Discount(100, 0.05));    // 5% for amounts >= 100
        discounts.add(new Discount(1000, 0.1));    // 10% for amounts >= 1000
        discounts.add(new Discount(10000, 0.2));   // 20% for amounts >= 10000
    }

    @Override
    public List<Discount> findAll() {
        return new ArrayList<>(discounts);
    }
}