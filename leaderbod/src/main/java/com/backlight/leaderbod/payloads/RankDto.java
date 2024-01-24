package com.backlight.leaderbod.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
//playlods which return rank of player
public class RankDto {
    private int id;
    private String name;
    private Integer rank;
}
