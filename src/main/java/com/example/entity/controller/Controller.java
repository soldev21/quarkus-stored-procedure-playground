package com.example.entity.controller;

import com.example.entity.repository.TournamentRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Path("/api/test/func")
@RequiredArgsConstructor
public class Controller {

    private final TournamentRepository tournamentRepository;

    @GET
    public String act(){
        tournamentRepository.getActiveTournaments(0, 1, 20);
        return UUID.randomUUID().toString();
    }
}
