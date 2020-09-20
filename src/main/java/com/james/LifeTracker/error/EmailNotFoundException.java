package com.james.LifeTracker.error;

import com.james.LifeTracker.error.common.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Email not found!")
public class EmailNotFoundException extends NotFoundException {

    public EmailNotFoundException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
