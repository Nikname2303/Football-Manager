package com.example.manager.controller;

import com.example.manager.dto.team.TeamResponseDto;
import com.example.manager.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/assign")
    public TeamResponseDto assignTeam(@RequestParam Long playerId,
                                      @RequestParam Long teamId) {
        return transferService.assignTeam(playerId, teamId);
    }

    @PostMapping("/transfer")
    public TeamResponseDto transfer(@RequestParam Long playerId,
                                    @RequestParam Long fromTeamId,
                                    @RequestParam Long toTeamId) {
        return transferService.transfer(playerId, fromTeamId, toTeamId);
    }
}
