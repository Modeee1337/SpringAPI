package com.ktu.csgo.insight.match.dtos;

import com.ktu.csgo.insight.match.Match;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Match}
 */
public record MatchEditDto(
        @NotNull(message = "Date must be not null") @Future OffsetDateTime startDate) implements Serializable {
}