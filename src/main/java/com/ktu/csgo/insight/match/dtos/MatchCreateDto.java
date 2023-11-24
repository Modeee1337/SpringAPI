package com.ktu.csgo.insight.match.dtos;

import com.ktu.csgo.insight.match.Match;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Match}
 */
public record MatchCreateDto(
        @NotNull(message = "Date must be not null") @Future(message = "Only a new tournament can be created (Date must be in the future)") OffsetDateTime startDate,
        TournamentDto tournament) implements Serializable {
}