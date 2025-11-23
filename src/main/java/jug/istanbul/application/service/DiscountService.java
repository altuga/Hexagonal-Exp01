package jug.istanbul.application.service;

import jug.istanbul.application.port.in.DiscountUseCase;
import jug.istanbul.domain.Discount;
import jug.istanbul.domain.port.DiscountRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService implements DiscountUseCase {

    private final DiscountRateRepository discountRateRepository;

    public DiscountService(DiscountRateRepository discountRateRepository) {
        this.discountRateRepository = discountRateRepository;
    }

    @Override
    public double applyDiscount(double amount) {
        List<Discount> discounts = discountRateRepository.findAll();
        return Discount.calculateDiscountedAmount(amount, discounts);
    }
}
