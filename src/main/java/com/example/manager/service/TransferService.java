package com.example.manager.service;

import com.example.manager.dto.team.TeamResponseDto;

public interface TransferService {
    TeamResponseDto assignTeam(Long playerId, Long teamId);

    TeamResponseDto transfer(Long playerId, Long fromTeamId, Long toTeamId);
}
