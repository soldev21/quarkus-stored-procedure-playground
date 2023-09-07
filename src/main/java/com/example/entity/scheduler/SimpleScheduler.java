//package com.example.entity.scheduler;
//
//import com.example.entity.Tournament;
//import com.example.entity.repository.TournamentRepository;
//import io.quarkus.narayana.jta.QuarkusTransaction;
//import io.quarkus.scheduler.Scheduled;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.persistence.EntityManager;
//
//import java.util.List;
//
//@ApplicationScoped
//public class SimpleScheduler {
//
//    @Inject
//    TournamentRepository tournamentRepository;
//    @Inject
//    EntityManager entityManager;
//
////    @Scheduled(every = "100000s")
//    void increment() {
//        List<Long> res = tournamentRepository.getActiveTournaments(0, 1, 10);
//        tournamentRepository.findAllByIds(res)
//            .forEach(this::update);
//    }
//
//    private void update(Tournament tournament) {
//        tournament.setStatus(5);
//        QuarkusTransaction.requiringNew()
//            .run(() -> {
//                entityManager.merge(tournament);
//            });
//    }
//}
