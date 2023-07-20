//package com.example.entity;
//
//import com.example.entity.repository.TournamentRepository;
//import io.quarkus.runtime.Quarkus;
//import io.quarkus.runtime.QuarkusApplication;
//import io.quarkus.runtime.annotations.QuarkusMain;
//import io.smallrye.mutiny.Multi;
//import io.smallrye.mutiny.infrastructure.Infrastructure;
//import jakarta.inject.Inject;
//
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@QuarkusMain
//public class Main {
//    public static void main(String... args) {
//        Quarkus.run(MyApp.class, args);
//    }
//
//    public static class MyApp implements QuarkusApplication {
//
//        @Inject
//        TournamentRepository tournamentRepository;
//
//        @Override
//        public int run(String... args) throws Exception {
//            List<Integer> integers = IntStream.rangeClosed(1, 1000).boxed().toList();
//            ExecutorService executorService = Executors.newFixedThreadPool(100);
//            Multi.createFrom().iterable(integers)
//                .emitOn(executorService)
//                .map(integer -> {
//                    tournamentRepository.getActiveTournaments(0, 1, 10000);
//                    return integer;
//                }).subscribe().with(
//                    result -> {},
//                    failure -> {});
//            Quarkus.waitForExit();
//            return 0;
//        }
//    }
//}