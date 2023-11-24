package com.ktu.csgo.insight.tournament;

import com.ktu.csgo.insight.match.Match;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @NotNull(message = "Tournament name cannot be null") @Size(message = "Tournament name must be between 10 and 255 characters", min = 10)
    String name;
}