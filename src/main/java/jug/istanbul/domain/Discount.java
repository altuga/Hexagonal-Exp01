package jug.istanbul.domain;

import java.util.List;

public class Discount {
    private final double minAmount;
    private final double discountPercent;

    public Discount(double minAmount, double discountPercent) {
        this.minAmount = minAmount;
        this.discountPercent = discountPercent;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public static double calculateDiscountedAmount(double amount, List<Discount> discounts) {
        double discountPercent = discounts.stream()
                .filter(discount -> amount >= discount.minAmount)
                .map(Discount::getDiscountPercent)
                .max(Double::compareTo)
                .orElse(0.0);
        return amount - (amount * discountPercent);
    }
}