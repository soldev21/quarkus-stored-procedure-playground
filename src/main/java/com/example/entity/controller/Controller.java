package com.example.entity.controller;

import com.example.entity.Tournament;
import com.example.entity.repository.TournamentRepository;
import com.example.entity.service.TestService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Path("/api/test/func")
@RequiredArgsConstructor
public class Controller {

    private final TestService testService;

    @GET
    public String act() {
        Long id = testService.setUp();
        testService.test(id);
        return "ok" + id;
    }
}
