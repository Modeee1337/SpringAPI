package com.ktu.csgo.insight.match.dtos;

import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link com.ktu.csgo.insight.matchmap.MatchMap}
 */
public record MatchMapDto(
        @Range(message = "First or second team, nothing in between", min = 1, max = 2) int mapWinner) implements Serializable {
}