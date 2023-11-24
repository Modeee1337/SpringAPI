package com.ktu.csgo.insight.tournament;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link Tournament}
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public record TournamentDto(
        @NotNull(message = "Tournament name cannot be null") @Size(message = "Tournament name must be between 10 and 255 characters", min = 10) String name) implements Serializable {
}