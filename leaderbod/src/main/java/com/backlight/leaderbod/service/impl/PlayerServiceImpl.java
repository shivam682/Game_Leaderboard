package com.backlight.leaderbod.service.impl;

import com.backlight.leaderbod.config.PlayerCsvRepresentation;
import com.backlight.leaderbod.exception.ResourceNotFoundException;
import com.backlight.leaderbod.model.Player;
import com.backlight.leaderbod.payloads.PlayerDto;
import com.backlight.leaderbod.payloads.RankDto;
import com.backlight.leaderbod.repositries.PlayerRepo;
import com.backlight.leaderbod.service.PlayerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player =this.modelMapper.map(playerDto,Player.class);
        // we can set the current system time as timestamp in player class but we have to put the data into database randomly of one month so not using it.
//        player.setTimeStamp( new Timestamp(System.currentTimeMillis()));
        this.playerRepo.save(player);
        return this.modelMapper.map(player,PlayerDto.class);
    }

    @Override
    public PlayerDto updatePlayer(String playerId, PlayerDto playerDto) {
        Player player = this.playerRepo.findById(playerId).orElseThrow(()->new ResourceNotFoundException("Player","Id",playerId));
        player.setName(playerDto.getName());
        player.setScore(playerDto.getScore());
        player.setCountry(playerDto.getCountry());
        return this.modelMapper.map(player,PlayerDto.class);
    }

    @Override
    public PlayerDto getPlayerById(String playerId) {
        Player player = this.playerRepo.findById(playerId).orElseThrow(()->new ResourceNotFoundException("Player","Id",playerId));
        return this.modelMapper.map(player,PlayerDto.class);
    }

    @Override
    public void deletePlayer(String playerId) {
        Player player = this.playerRepo.findById(playerId).orElseThrow(()->new ResourceNotFoundException("Player","Id",playerId));
        this.playerRepo.delete(player);
    }

    @Override
    public RankDto getRankByPlayerId(String playerId) {
        Player player = this.playerRepo.findById(playerId).orElseThrow(()->new ResourceNotFoundException("Player","Id",playerId));
        RankDto rankDto = new RankDto();
        rankDto.setName(player.getName());
        rankDto.setId(player.getUID());
        rankDto.setRank(this.playerRepo.getUserRank(playerId));
        return rankDto;
    }
//
    @Override
    public List<PlayerDto> getCurrentWeekLeaderboard() {
        List<Player> players = this.playerRepo.getCurrentWeekLeaderboard();
        return players.stream().map((player)-> this.modelMapper.map(player,PlayerDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDto> getLastWeekLeaderboardByCountry(String country) {
        List<Player>players =this.playerRepo.getLastWeekLeaderboardByCountry(country);
        return players.stream().map((player)->this.modelMapper.map(player,PlayerDto.class)).collect(Collectors.toList());
    }




}
