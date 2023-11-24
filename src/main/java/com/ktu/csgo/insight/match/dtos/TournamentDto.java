package com.ktu.csgo.insight.match.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link com.ktu.csgo.insight.tournament.Tournament}
 */
public record TournamentDto(
        @NotNull(message = "The reference id of tournament can't be null") @Positive Long id) implements Serializable {
}