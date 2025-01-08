package com.example.manager.strategy.impl;

import com.example.manager.model.Player;
import com.example.manager.model.Team;
import com.example.manager.strategy.CommissionStrategy;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component("baseTransferCost")
public class BaseTransferCost implements CommissionStrategy {
    private static final Long BASIC_TRANSFER_COST = 100000L;

    @Override
    public BigDecimal getBaseTransferCost(Player player, Team team) {
        Double priceFromPlayer = (double) ((player.getExperience() * BASIC_TRANSFER_COST)
                        / player.getAge());
        return BigDecimal.valueOf(priceFromPlayer * team.getCommission() + priceFromPlayer);
    }
}
