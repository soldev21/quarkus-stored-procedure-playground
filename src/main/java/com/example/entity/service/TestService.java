package com.example.entity.service;

import com.example.entity.Tournament;
import com.example.entity.repository.TournamentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final TournamentRepository tournamentRepository;
    private final ActiveTournamentService activeTournamentService;

    @Transactional
    public Long setUp() {
        Tournament tournament = new Tournament();
        tournament.setStatus(0);
        tournament.setCreationTime(new Date());
        tournamentRepository.persist(tournament);
        log.info("createdTrId: " + tournament.getId());
        return tournament.getId();
    }

    public void test(Long id) {
        log.info("test: " + id);
        activeTournamentService.getActive(20);
    }
}
