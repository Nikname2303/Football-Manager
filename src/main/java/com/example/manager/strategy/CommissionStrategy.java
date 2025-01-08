package com.example.manager.strategy;

import com.example.manager.model.Player;
import com.example.manager.model.Team;
import java.math.BigDecimal;

public interface CommissionStrategy {
    BigDecimal getBaseTransferCost(Player player, Team team);
}
