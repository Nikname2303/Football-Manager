package com.example.manager.controller;

import com.example.manager.dto.player.PlayerCreateDto;
import com.example.manager.dto.player.PlayerResponseDto;
import com.example.manager.service.PlayerService;
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
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/all")
    public List<PlayerResponseDto> getAll(Pageable pageable) {
        return playerService.getAllPlayers(pageable);
    }

    @PostMapping
    public PlayerResponseDto addNewPlayer(@RequestBody @Valid PlayerCreateDto createDto) {
        return playerService.addNewPlayer(createDto);
    }

    @GetMapping("{id}")
    public PlayerResponseDto getById(@PathVariable Long id) {
        return playerService.getById(id);
    }

    @PutMapping("{id}")
    public void updateById(@RequestBody PlayerCreateDto createDto, @PathVariable Long id) {
        playerService.updateById(id, createDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        playerService.deleteById(id);
    }
}
