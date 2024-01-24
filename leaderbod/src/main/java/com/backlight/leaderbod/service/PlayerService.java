package com.backlight.leaderbod.service;

import com.backlight.leaderbod.payloads.PlayerDto;
import com.backlight.leaderbod.payloads.RankDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlayerService {

    PlayerDto createPlayer(PlayerDto playerDto);
    PlayerDto updatePlayer(String playerId, PlayerDto playerDto);

    PlayerDto getPlayerById(String playerId);

    void deletePlayer(String playerId);

    RankDto getRankByPlayerId(String playerId);
//
    List<PlayerDto> getCurrentWeekLeaderboard();
    List<PlayerDto> getLastWeekLeaderboardByCountry(String country);



}
