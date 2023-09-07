package com.example.entity.repository;

import com.example.entity.Tournament;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.narayana.jta.TransactionExceptionResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TournamentRepository implements PanacheRepository<Tournament> {

    @Inject
    EntityManager entityManager;

    @Transactional
    public List<Long> getActiveTournaments(int curStatus, int newStatus, int limit) {
        return (List<Long>) QuarkusTransaction.joiningExisting()
            .timeout(100)
            .exceptionHandler(throwable -> TransactionExceptionResult.ROLLBACK)
            .call(() -> entityManager.createNamedStoredProcedureQuery("findActiveTournaments")
                .setParameter("ch_status", curStatus)
                .setParameter("new_status_", newStatus)
                .setParameter("limit_", limit)
                .getResultList());
    }

    public List<Tournament> findAllByIds(List<Long> ids) {
        return list("id in ?1", ids);
    }
}
