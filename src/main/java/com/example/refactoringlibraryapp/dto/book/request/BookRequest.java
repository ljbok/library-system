package com.example.refactoringlibraryapp.dto.book.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class BookRequest {

    private Long id;

    private String name;
    private String writer;
    private Date regiDate;

}
