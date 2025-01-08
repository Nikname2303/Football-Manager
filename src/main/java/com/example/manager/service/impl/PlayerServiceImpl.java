package com.example.manager.service.impl;

import com.example.manager.dto.player.PlayerCreateDto;
import com.example.manager.dto.player.PlayerResponseDto;
import com.example.manager.mapper.PlayerMapper;
import com.example.manager.model.Player;
import com.example.manager.repository.PlayerRepository;
import com.example.manager.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerResponseDto addNewPlayer(PlayerCreateDto createDto) {
        Player player = playerRepository.save(playerMapper.toModel(createDto));
        return playerMapper.toResponseDto(player);
    }

    @Override
    public PlayerResponseDto getById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find player with id " + id)
        );
        return playerMapper.toResponseDto(player);
    }

    @Override
    public List<PlayerResponseDto> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll().stream()
                .map(playerMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void updateById(Long id, PlayerCreateDto createDto) {
        playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to update non-existent player with id " + id)
        );
        Player player = playerMapper.toModel(createDto);
        player.setId(id);
        playerRepository.save(player);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to update non-existent player with id " + id)
        );
        playerRepository.deleteById(id);
    }
}
