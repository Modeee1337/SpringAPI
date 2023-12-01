package com.ktu.csgo.insight.tournament;

import com.ktu.csgo.insight.error.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    @GetMapping
    List<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Tournament addTournament(@Validated @RequestBody TournamentDto tournamentDto) {
        Tournament tournament = tournamentMapper.toEntity(tournamentDto);
        return tournamentRepository.save(tournament);
    }

    @GetMapping("/{id}")
    Tournament getTournament(@PathVariable Long id) {
        return tournamentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
    }

    @PutMapping("/{id}")
    Tournament updateTournament(@PathVariable Long id, @Valid @RequestBody TournamentDto tournamentDto) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));

        if (!id.equals(tournament.getId())) {
            throw new BadRequestException("Tournament id's do not match");
        }

        Tournament updatedTournament = tournamentMapper.toEntity(tournamentDto);
        updatedTournament.setId(id);

        return tournamentRepository.save(updatedTournament);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTournament(@PathVariable Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        tournamentRepository.delete(tournament);
    }
}
