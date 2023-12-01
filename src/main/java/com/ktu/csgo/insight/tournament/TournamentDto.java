package com.ktu.csgo.insight.tournament;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.ktu.csgo.insight.tournament.Tournament}
 */
public record TournamentDto(@Size(min = 10, max = 255) String name) implements Serializable {
}