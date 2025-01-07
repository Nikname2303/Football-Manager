package com.example.manager.controller;

import com.example.manager.dto.team.TeamCreateDto;
import com.example.manager.dto.team.TeamResponseDto;
import com.example.manager.service.TeamService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public TeamResponseDto addNewTeam(@RequestBody @Valid TeamCreateDto createDto) {
        return teamService.addNewTeam(createDto);
    }

    @GetMapping("/all")
    public List<TeamResponseDto> getAll(Pageable pageable) {
        return teamService.getAllTeams(pageable);
    }

    @GetMapping("/{id}")
    public TeamResponseDto getById(@PathVariable Long id) {
        return teamService.getById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@RequestBody TeamCreateDto createDto, @PathVariable Long id) {
        teamService.updateById(id, createDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        teamService.deleteById(id);
    }
}
