package com.example.entity.service;

import com.example.entity.Tournament;
import com.example.entity.repository.TournamentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class ActiveTournamentService {

    private final TournamentRepository tournamentRepository;

    @Transactional
    public void getActive(int n){
        List<Long> ids = tournamentRepository.getActiveTournaments(0, 1, n);

        log.info("before for: " + ids);
        for(Long id:ids) {
            log.info("for: " + id);
            Tournament tournament = tournamentRepository.findById(id);
            tournament.setStatus(2);
            tournamentRepository.persistAndFlush(tournament);
        }
    }
}
