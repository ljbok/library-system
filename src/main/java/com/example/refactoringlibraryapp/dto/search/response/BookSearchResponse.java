package com.example.refactoringlibraryapp.dto.search.response;

import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
public class BookSearchResponse {

    private String bookName;
    private String writer;

    private String userName;
    private String phone;

    private String loanDate;
    private String returnDate;

    public BookSearchResponse(String bookName, String writer, String userName, String phone, String loanDate, String returnDate) {
        this.bookName = bookName;
        this.writer = writer;
        this.userName = userName;
        this.phone = phone;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
}
