package org.awbd.libraryapp.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class SequenceInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createBookNumberSequence() {
        entityManager.createNativeQuery("CREATE SEQUENCE IF NOT EXISTS book_number_sequence START WITH 1 INCREMENT BY 1")
                .executeUpdate();
    }
}
