package org.awbd.libraryapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsExceptions extends RuntimeException {
    public ResourceAlreadyExistsExceptions() {
    }

    public ResourceAlreadyExistsExceptions(String message) {
        super(message);
    }

    public ResourceAlreadyExistsExceptions(String message, Throwable throwable) {
        super(message, throwable);
    }
}
