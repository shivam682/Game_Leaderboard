package com.backlight.leaderbod.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

//Entity of PLayer to store in database
@Entity
@Builder
@Table(name="Player")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Player {
    @Id
    @GeneratedValue(generator = "player_gen",strategy = GenerationType.AUTO)
    @SequenceGenerator(name="player_gen",sequenceName = "player_seq",initialValue = 1,allocationSize = 1)
    @Column(name="UID")
    private Integer UID;

    @Column(name="Name",nullable = false, length = 20)
    private String Name;

    @Min(value = 0, message = "Score should not be less than 0")
    @Max(value = 1000, message = "Score should not be greater than 100")
    @Column(name = "Score", nullable = false)
    private Integer Score;
    @Column(name="Country",length = 2)
    private String Country;

    @Column(name="TimeStamp")
    private Timestamp TimeStamp;


}
