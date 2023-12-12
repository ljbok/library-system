package com.example.refactoringlibraryapp.dto.book.response;

import com.example.refactoringlibraryapp.domain.book.Book;
import lombok.Getter;

import java.util.Date;

@Getter
public class BookReponse {

    private Long id;

    private String name;
    private String writer;
    private Date regiDate;

    public BookReponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.writer = book.getWriter();
        this.regiDate = book.getRegiDate();
    }

}
