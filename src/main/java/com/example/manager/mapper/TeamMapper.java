package com.example.manager.mapper;

import com.example.manager.config.MapperConfig;
import com.example.manager.dto.team.TeamCreateDto;
import com.example.manager.dto.team.TeamResponseDto;
import com.example.manager.model.Team;
import org.mapstruct.Mapper;

@Mapper(
        config = MapperConfig.class,
        componentModel = "spring",
        uses = PlayerMapper.class
)
public interface TeamMapper {
    TeamResponseDto toResponseDto(Team team);

    Team toModel(TeamCreateDto createDto);

}
