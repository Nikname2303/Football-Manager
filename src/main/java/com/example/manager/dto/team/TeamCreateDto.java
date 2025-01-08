package com.example.manager.dto.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamCreateDto {
    @NotBlank
    private String name;
    @Positive
    private Double commission;
    @Positive
    private BigDecimal bankAccountAmount;
}
