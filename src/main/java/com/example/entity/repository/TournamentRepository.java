package com.example.entity.repository;

import com.example.entity.Tournament;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TournamentRepository implements PanacheRepository<Tournament> {

    @Inject
    EntityManager entityManager;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Long> getActiveTournaments(int curStatus, int newStatus, int limit) {
        List res = entityManager.createNamedStoredProcedureQuery("findActiveTournaments")
            .setParameter("ch_status", curStatus)
            .setParameter("new_status_", newStatus)
            .setParameter("limit_", limit)
            .getResultList();
        System.out.println(res);
//        throw new RuntimeException();
        return res;
    }
}
