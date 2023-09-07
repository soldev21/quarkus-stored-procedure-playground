package com.example.entity;

import io.quarkus.narayana.jta.runtime.TransactionScopedNotifier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.BeforeDestroyed;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.TransactionScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
@Slf4j
public class MyTransactionEventListeningBean {

    private final AtomicInteger integer = new AtomicInteger(0);

    void onBeginTransaction(@Observes @Initialized(TransactionScoped.class) Object event) {
        TransactionScopedNotifier.TransactionId transactionId = (TransactionScopedNotifier.TransactionId) event;
//        log.info(integer.incrementAndGet() + ": " + new Date() + "  " + event);
        log.info(String.format("Transaction: %s", transactionId.toString()));
        // This gets invoked when a transaction begins.
    }

    void onBeforeEndTransaction(@Observes @BeforeDestroyed(TransactionScoped.class) Object event) {
        // This gets invoked before a transaction ends (commit or rollback).
    }

    void onAfterEndTransaction(@Observes @Destroyed(TransactionScoped.class) Object event) {
        TransactionScopedNotifier.TransactionId transactionId = (TransactionScopedNotifier.TransactionId) event;
//        log.info(integer.incrementAndGet() + ": " + new Date() + "  " + event);
        log.info(String.format("Transaction: %s", transactionId.toString()));
    }
}
