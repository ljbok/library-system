package com.example.refactoringlibraryapp.controller.errorhandler;

import com.example.refactoringlibraryapp.dto.errorhandler.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        return new ErrorResponse("error", ex.getMessage());
    }
}
