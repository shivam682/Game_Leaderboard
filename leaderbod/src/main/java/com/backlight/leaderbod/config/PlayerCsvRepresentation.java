package com.backlight.leaderbod.config;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerCsvRepresentation {
    @CsvBindByName(column = "uid")
    private Integer uid;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "country")
    private String country;
    @CsvBindByName(column = "time_stamp")
    private Timestamp timestamp;
    @CsvBindByName(column = "score")
    private Integer score;
}
