package com.example.manager.dto.team;

import com.example.manager.dto.player.PlayerResponseDto;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponseDto {
    private String name;
    private Long commission;
    private BigDecimal bankAccountAmount;
    private List<PlayerResponseDto> squad;
}
