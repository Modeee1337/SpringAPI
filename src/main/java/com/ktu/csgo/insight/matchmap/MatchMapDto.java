package com.ktu.csgo.insight.matchmap;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link MatchMap}
 */
public record MatchMapDto(@PositiveOrZero(message = "Round win count must be more than 0") int resultTeamOne,
                          @PositiveOrZero(message = "Round win count must be more or equal to 0") int resultTeamTwo,
                          @PositiveOrZero(message = "Round won count on ct side must be more equal 0") int ctOne,
                          @PositiveOrZero(message = "Round won count on ct side must be more equal 0") int ctTwo,
                          @PositiveOrZero(message = "Round won count on t side must be more equal 0") int tOne,
                          @PositiveOrZero(message = "Round won count on t side must be more equal 0") int tTwo,
                          @Positive(message = "Rank in the hltv must be more than 0 (positive)") int rankOne,
                          @Positive(message = "Rank in the hltv must be more than 0 (positive)") int rankTwo,
                          @PositiveOrZero(message = "Map wins must be more or equal to 1") int mapWinsOne,
                          @PositiveOrZero(message = "Map wins must be more or equal to 1") int mapWinsTwo,
                          @Range(message = "First or second team, nothing in between", min = 1, max = 2) int mapWinner,
                          MatchDto match) implements Serializable {
}