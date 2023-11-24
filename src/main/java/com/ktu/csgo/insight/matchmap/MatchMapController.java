package com.ktu.csgo.insight.matchmap;

import com.ktu.csgo.insight.error.exceptions.BadRequestException;
import com.ktu.csgo.insight.match.Match;
import com.ktu.csgo.insight.match.MatchMapper;
import com.ktu.csgo.insight.match.MatchRepository;
import com.ktu.csgo.insight.tournament.Tournament;
import com.ktu.csgo.insight.tournament.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/matches/{matchId}/match-maps")
@RequiredArgsConstructor
public class MatchMapController {
    private final MatchMapRepository matchMapRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final MatchMapMapper matchMapMapper;

    @GetMapping
    public List<MatchMap> getMatchMaps(@PathVariable Long tournamentId, @PathVariable Long matchId) {
        //check if tournament and matches exist, also check if match belongs to tournament
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        if (!match.getTournament().getId().equals(tournament.getId())) {
            throw new BadRequestException("Match does not belong to tournament");
        }
        return matchMapRepository.findMatchMapsByMatchId(matchId);
    }

    @GetMapping("/{id}")
    public MatchMap getMatchMap(@PathVariable Long id, @PathVariable Long tournamentId, @PathVariable Long matchId) {
        //check if tournament and matches exist, also check if match belongs to tournament
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        if (!match.getTournament().getId().equals(tournament.getId())) {
            throw new BadRequestException("Match does not belong to tournament");
        }
        MatchMap matchMap = matchMapRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match map not found"));
        if (!matchMap.getMatch().getId().equals(match.getId())) {
            throw new BadRequestException("Match map does not belong to match");
        }
        return matchMap;
    }

    @PostMapping
    public MatchMap addMatchMap(@Valid @RequestBody MatchMapDto matchMapDto, @PathVariable Long tournamentId, @PathVariable Long matchId) {
        //check if tournament and matches exist, also check if match belongs to tournament
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        if (!match.getTournament().getId().equals(tournament.getId())) {
            throw new BadRequestException("Match does not belong to tournament");
        }
        MatchMap matchMap = matchMapMapper.toEntity(matchMapDto);
        matchMap.setMatch(match);
        return matchMapRepository.save(matchMap);
    }

    @PutMapping("/{id}")
    public MatchMap updateMatchMap(@PathVariable Long id, @RequestBody MatchMapDto matchMapDto, @PathVariable Long tournamentId, @PathVariable Long matchId) {
        //check if tournament and matches exist, also check if match belongs to tournament
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        if (!match.getTournament().getId().equals(tournament.getId())) {
            throw new BadRequestException("Match does not belong to tournament");
        }
        MatchMap matchMapFromDb = matchMapRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match map not found"));
        if (!matchMapFromDb.getMatch().getId().equals(match.getId())) {
            throw new BadRequestException("Match map does not belong to match");
        }
        MatchMap matchMap = matchMapMapper.toEntity(matchMapDto);
        matchMap.setId(matchMapFromDb.getId());
        matchMap.setMatch(match);
        return matchMapRepository.save(matchMap);
    }

    @DeleteMapping("/{id}")
    public void deleteMatchMap(@PathVariable Long id, @PathVariable Long tournamentId, @PathVariable Long matchId) {
        //check if tournament and matches exist, also check if match belongs to tournament
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        if (!match.getTournament().getId().equals(tournament.getId())) {
            throw new BadRequestException("Match does not belong to tournament");
        }
        MatchMap matchMap = matchMapRepository.findById(id).orElseThrow(() -> new RuntimeException("Match map not found"));
        if (!matchMap.getMatch().getId().equals(match.getId())) {
            throw new BadRequestException("Match map does not belong to match");
        }
        matchMap.setMatch(match);
        matchMapRepository.delete(matchMap);
    }
}
