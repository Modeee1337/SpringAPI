package com.ktu.csgo.insight.match;

import com.ktu.csgo.insight.error.exceptions.BadRequestException;
import com.ktu.csgo.insight.match.dtos.MatchCreateDto;
import com.ktu.csgo.insight.match.dtos.MatchEditDto;
import com.ktu.csgo.insight.match.dtos.MatchGetDto;
import com.ktu.csgo.insight.tournament.Tournament;
import com.ktu.csgo.insight.tournament.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchMapper matchMapper;

    @GetMapping
    public List<MatchGetDto> getMatches(@PathVariable Long tournamentId) {
        tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        List<Match> matches = matchRepository.findMatchesByTournamentId(tournamentId);
        return matches.stream().map(matchMapper::toDto).toList();
    }

    //TODO pasitvarkyt
    @GetMapping("/{id}")
    public Match getMatch(@PathVariable Long id, @PathVariable Long tournamentId) {
        tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        return matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match not found"));
    }

    //insert match to tournament
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Match addMatch(@Valid @RequestBody MatchCreateDto matchCreateDto, @PathVariable Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match match = matchMapper.toEntity(matchCreateDto);
        match.setTournament(tournament);

        return matchRepository.save(match);
    }

    //update match
    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable Long id,@Valid @RequestBody MatchEditDto matchEditDto, @PathVariable Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match matchFromDb = matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        //check if match belongs to tournament
        if (!matchFromDb.getTournament().getId().equals(tournament.getId())) {
            throw new BadRequestException("Match does not belong to tournament");
        }
        Match match = matchMapper.toEntity(matchEditDto);
        match.setTournament(tournament);
        match.setId(matchFromDb.getId());
        return matchRepository.save(match);
    }

    //delete match
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable Long id, @PathVariable Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Match match = matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        //check if match belongs to tournament
        if (!match.getTournament().getId().equals(tournament.getId())) {
            throw new BadRequestException("Match does not belong to tournament");
        }
        matchRepository.delete(match);
    }
}
