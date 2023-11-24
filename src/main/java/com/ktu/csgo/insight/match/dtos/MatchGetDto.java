package com.ktu.csgo.insight.match.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

/**
 * DTO for {@link com.ktu.csgo.insight.match.Match}
 */
public record MatchGetDto(Long id, @NotNull(message = "Start date must not be null") OffsetDateTime startDate,
                          @NotNull(message = "Tournament cannot be null") MatchGetDto.TournamentDto1 tournament,
                          @Size(message = "Max ammount of match maps is 7 (BO7)", min = 0, max = 7) Set<MatchMapDto1> matchMaps) implements Serializable {
    /**
     * DTO for {@link com.ktu.csgo.insight.tournament.Tournament}
     */
    public record TournamentDto1(Long id) implements Serializable {
    }

    /**
     * DTO for {@link com.ktu.csgo.insight.matchmap.MatchMap}
     */
    public record MatchMapDto1(Long id,
                               @PositiveOrZero(message = "Round win count must be more than 0") int resultTeamOne,
                               @PositiveOrZero(message = "Round win count must be more or equal to 0") int resultTeamTwo,
                               @Range(message = "First or second team, nothing in between", min = 1, max = 2) int mapWinner) implements Serializable {
    }
}