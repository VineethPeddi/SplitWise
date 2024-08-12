package org.example.splitwise.strategies;

import org.example.splitwise.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PricingStrategy {
    List<Transaction> settleUp(Map<Long,Integer> mp);
}
