package com.example.manager.dto.player;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerCreateDto {
    @NotBlank
    private String name;
    @Min(18)
    private Long age;
    @PositiveOrZero
    private Long experience;
}
