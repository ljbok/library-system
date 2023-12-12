package com.example.refactoringlibraryapp.dto.errorhandler.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String status;
    private String message;
}
