package com.example.manager.service;

import com.example.manager.dto.team.TeamCreateDto;
import com.example.manager.dto.team.TeamResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    TeamResponseDto addNewTeam(TeamCreateDto createDto);

    TeamResponseDto getById(Long id);

    List<TeamResponseDto> getAllTeams(Pageable pageable);

    void updateById(Long id, TeamCreateDto createDto);

    void deleteById(Long id);
}
