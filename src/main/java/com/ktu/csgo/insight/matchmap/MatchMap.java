package com.ktu.csgo.insight.matchmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktu.csgo.insight.match.Match;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "match_map")
public class MatchMap {
    @PositiveOrZero(message = "Round win count must be more than 0")
    @Column(nullable = false)
    int resultTeamOne;
    @PositiveOrZero(message = "Round win count must be more or equal to 0")
    @Column(nullable = false)
    int resultTeamTwo;
    @PositiveOrZero(message = "Round won count on ct side must be more equal 0")
    @Column(nullable = false)
    int ctOne;
    @PositiveOrZero(message = "Round won count on ct side must be more equal 0")
    @Column(nullable = false)
    int ctTwo;
    @PositiveOrZero(message = "Round won count on t side must be more equal 0")
    @Column(nullable = false)
    int tOne;
    @PositiveOrZero(message = "Round won count on t side must be more equal 0")
    @Column(nullable = false)
    int tTwo;
    @Positive(message = "Rank in the hltv must be more than 0 (positive)")
    @Column(nullable = false)
    int rankOne;
    @Positive(message = "Rank in the hltv must be more than 0 (positive)")
    @Column(nullable = false)
    int rankTwo;
    @PositiveOrZero(message = "Map wins must be more or equal to 1")
    @Column(nullable = false)
    int mapWinsOne;
    @PositiveOrZero(message = "Map wins must be more or equal to 1")
    @Column(nullable = false)
    int mapWinsTwo;
    @Range(message = "First or second team, nothing in between", min = 1, max = 2)
    @Column(nullable = false)
    int mapWinner;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonIgnore
    private Match match;

}