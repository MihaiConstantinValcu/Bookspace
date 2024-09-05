package org.awbd.libraryapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {

    @Autowired
    private SequenceInitializer sequenceInitializer;

    @Override
    public void run(String... args) {
        sequenceInitializer.createBookNumberSequence();
    }
}