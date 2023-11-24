package com.ktu.csgo.insight.match;

import com.ktu.csgo.insight.matchmap.MatchMap;
import com.ktu.csgo.insight.tournament.Tournament;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @NotNull(message = "Start date must not be null") OffsetDateTime startDate;

    @NotNull(message = "Tournament cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @Size(message = "Max ammount of match maps is 7 (BO7)", min = 0, max = 7)
    @OneToMany(mappedBy = "match", orphanRemoval = true)
    private Set<MatchMap> matchMaps = new LinkedHashSet<>();
}