package com.example.manager.service;

import com.example.manager.dto.player.PlayerCreateDto;
import com.example.manager.dto.player.PlayerResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    PlayerResponseDto addNewPlayer(PlayerCreateDto createDto);

    PlayerResponseDto getById(Long id);

    List<PlayerResponseDto> getAllPlayers(Pageable pageable);

    void updateById(Long id, PlayerCreateDto createDto);

    void deleteById(Long id);
}
