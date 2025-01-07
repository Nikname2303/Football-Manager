package com.example.manager.service.impl;

import com.example.manager.dto.team.TeamResponseDto;
import com.example.manager.exception.NotEnoughBankAmountException;
import com.example.manager.exception.TransferException;
import com.example.manager.mapper.TeamMapper;
import com.example.manager.model.Player;
import com.example.manager.model.Team;
import com.example.manager.repository.PlayerRepository;
import com.example.manager.repository.TeamRepository;
import com.example.manager.service.TransferService;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {
    private static final Long BASIC_TRANSFER_COST = 100000L;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TeamMapper teamMapper;

    @Override
    public TeamResponseDto assignTeam(Long playerId, Long teamId) {
        Player player = playerRepository.findById(playerId).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to assign non-existing player with id " + playerId)
        );
        if (player.getTeam() != null) {
            throw new TransferException("You try to assign player who already play in other team. "
                    + "Please use transfer option");
        }
        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to assign player in non-existing team with id " + teamId)
        );
        player.setTeam(team);
        team.getSquad().add(player);
        playerRepository.save(player);
        teamRepository.save(team);
        return teamMapper.toResponseDto(team);
    }

    @Override
    public TeamResponseDto transfer(Long playerId, Long fromTeamId, Long toTeamId) {
        Player player = playerRepository.findById(playerId).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to transfer non-existing player with id " + playerId)
        );
        Team fromTeam = teamRepository.findById(fromTeamId).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to transfer player from non-existing team with id " + fromTeamId)
        );
        Team toTeam = teamRepository.findById(toTeamId).orElseThrow(
                () -> new EntityNotFoundException(
                        "You try to transfer player to non-existing team with id " + toTeamId)
        );
        BigDecimal transferCost = getTransferCost(player, fromTeam);
        fromTeam.setBankAccountAmount(fromTeam.getBankAccountAmount().add(transferCost));
        if (fromTeam.getBankAccountAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughBankAmountException("You don't have enough money for that transfer");
        }
        toTeam.setBankAccountAmount(toTeam.getBankAccountAmount().subtract(transferCost));
        player.setTeam(toTeam);
        playerRepository.save(player);
        teamRepository.save(toTeam);
        teamRepository.save(fromTeam);
        return teamMapper.toResponseDto(toTeam);
    }

    private BigDecimal getTransferCost(Player player, Team team) {
        BigDecimal priceFromPlayer = BigDecimal.valueOf((player.getExperience()
                * BASIC_TRANSFER_COST)
                / player.getAge());
        BigDecimal commission = BigDecimal.valueOf(team.getCommission() / 100);
        return priceFromPlayer.add(priceFromPlayer.multiply(commission));
    }
}
