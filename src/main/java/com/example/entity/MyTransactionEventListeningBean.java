package com.example.entity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.BeforeDestroyed;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.TransactionScoped;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class MyTransactionEventListeningBean {

    private final AtomicInteger integer = new AtomicInteger(0);

    void onBeginTransaction(@Observes @Initialized(TransactionScoped.class) Object event) {
        System.out.println(integer.incrementAndGet() + ": " + new Date() + "  " + event);
        // This gets invoked when a transaction begins.
    }

    void onBeforeEndTransaction(@Observes @BeforeDestroyed(TransactionScoped.class) Object event) {
        // This gets invoked before a transaction ends (commit or rollback).
    }

    void onAfterEndTransaction(@Observes @Destroyed(TransactionScoped.class) Object event) {
//        TransactionScopedNotifier.TransactionId transactionImple = (TransactionScopedNotifier.TransactionId) event;
//        System.out.println(transactionImple);
        // This gets invoked after a transaction ends (commit or rollback).
    }
}
