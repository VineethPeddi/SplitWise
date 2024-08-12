package org.example.splitwise.strategies;

import org.example.splitwise.models.Strategies;
import org.springframework.stereotype.Component;

@Component
public class PricingStrategyFactory {
    public PricingStrategy getPricingStrategy(Strategies strategy){
        if (strategy==Strategies.TWO_POINTER)return new TwoPointerPricingStrategy();
        return null;
    }
}
