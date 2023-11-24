package com.ktu.csgo.insight.matchmap;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchMapRepository extends JpaRepository<MatchMap, Long> {
    List<MatchMap> findMatchMapsByMatchId(Long matchId);
}