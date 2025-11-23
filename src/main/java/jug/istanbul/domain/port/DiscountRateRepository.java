package jug.istanbul.domain.port;

import jug.istanbul.domain.Discount;

import java.util.List;

public interface DiscountRateRepository {
    List<Discount> findAll();
}