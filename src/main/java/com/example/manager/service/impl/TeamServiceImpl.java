package com.example.manager.service.impl;

import com.example.manager.dto.team.TeamCreateDto;
import com.example.manager.dto.team.TeamResponseDto;
import com.example.manager.exception.TransferException;
import com.example.manager.mapper.TeamMapper;
import com.example.manager.model.Team;
import com.example.manager.repository.TeamRepository;
import com.example.manager.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public TeamResponseDto addNewTeam(TeamCreateDto createDto) {
        Team team = teamRepository.save(teamMapper.toModel(createDto));
        return teamMapper.toResponseDto(team);
    }

    @Override
    public TeamResponseDto getById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find team with id " + id)
        );
        return teamMapper.toResponseDto(team);
    }

    @Override
    public List<TeamResponseDto> getAllTeams(Pageable pageable) {
        return teamRepository.findAll().stream()
                .map(teamMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void updateById(Long id, TeamCreateDto createDto) {
        teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to update non-existent team with id " + id)
        );
        Team team = teamMapper.toModel(createDto);
        team.setId(id);
        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to delete non-existent team with id " + id)
        );
        if (!team.getSquad().isEmpty()) {
            throw new TransferException("You try to delete team with active players."
                    + " Please transfer or resign all players before deleting team");
        }
        teamRepository.deleteById(id);
    }
}
