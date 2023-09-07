package com.example.entity.repository;

import com.example.entity.Tournament;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.narayana.jta.TransactionExceptionResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TournamentRepository implements PanacheRepository<Tournament> {

    @Inject
    EntityManager entityManager;

    @Transactional
    public List<Long> getActiveTournaments(int curStatus, int newStatus, int limit) {
//        return (List<Long>) QuarkusTransaction.joiningExisting()
//            .timeout(100)
//            .exceptionHandler(throwable -> TransactionExceptionResult.ROLLBACK)
//            .call(() -> entityManager.createNamedStoredProcedureQuery("findActiveTournaments")
//                .setParameter("ch_status", curStatus)
//                .setParameter("new_status_", newStatus)
//                .setParameter("limit_", limit)
//                .getResultList());
//    }


        List<Long> result = new ArrayList();
        Session session = (Session) this.getEntityManager().unwrap(Session.class);
        session.doWork((connection) -> {
            CallableStatement callable = connection.prepareCall("select get_tournaments_by_status(?, ?, ?)");

            try {
                callable.setInt(1, curStatus);
                callable.setInt(2, newStatus);
                callable.setInt(3, limit);
                ResultSet resultSet = callable.executeQuery();

                while (resultSet.next()) {
                    Long roomInfo = resultSet.getLong(1);
                    result.add(roomInfo);
                }
            } catch (Throwable var12) {
                if (callable != null) {
                    try {
                        callable.close();
                    } catch (Throwable var11) {
                        var12.addSuppressed(var11);
                    }
                }

                throw var12;
            }

            if (callable != null) {
                callable.close();
            }

        });
        return result;
    }

}
