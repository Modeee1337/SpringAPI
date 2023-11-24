package com.ktu.csgo.insight.error;

import com.ktu.csgo.insight.error.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.OffsetDateTime;

@ControllerAdvice
public class CSGOInsightErrorHandler {
    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    @ResponseBody
    public ErrorDTO manageException(HttpServletResponse response, EntityNotFoundException ex) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return ErrorDTO.builder().message(ex.getMessage()).timestamp(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ErrorDTO manageException(HttpServletResponse response, BadRequestException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ErrorDTO.builder().message(ex.getMessage()).timestamp(OffsetDateTime.now()).build();
    }
}
