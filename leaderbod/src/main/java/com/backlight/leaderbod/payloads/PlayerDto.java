package com.backlight.leaderbod.payloads;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.rmi.server.UID;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
//playload for player
public class PlayerDto {
    private int uid;
    @NotBlank(message= "Name is required")
    private String name;
    @NotBlank(message = "Score is required")
    private Integer score;
    private Timestamp timestamp;
    @NotBlank(message = "Country is required")
    private String country;


}
