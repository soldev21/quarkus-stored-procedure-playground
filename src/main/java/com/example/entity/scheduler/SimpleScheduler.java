package com.example.entity.scheduler;

import com.example.entity.repository.TournamentRepository;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimpleScheduler {

    @Inject
    TournamentRepository tournamentRepository;

    @Scheduled(every = "1s")
    void increment() {
        tournamentRepository.getActiveTournaments(0, 1, 10);

    }
}
