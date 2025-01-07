package com.example.manager.mapper;

import com.example.manager.config.MapperConfig;
import com.example.manager.dto.player.PlayerCreateDto;
import com.example.manager.dto.player.PlayerResponseDto;
import com.example.manager.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MapperConfig.class,
        componentModel = "spring"
)
public interface PlayerMapper {
    @Mapping(source = "team.id", target = "teamId")
    PlayerResponseDto toResponseDto(Player player);

    Player toModel(PlayerCreateDto createDto);
}
