package com.example.manager.dto.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponseDto {
    private String name;
    private Long age;
    private Long experience;
    private Long teamId;
}
