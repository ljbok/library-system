package com.example.refactoringlibraryapp.dto.search.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSearchResponse {

    private String userName;
    private String phone;

    private String bookName;
    private String writer;

    private String loanDate;
    private String returnDate;

    public UserSearchResponse(String userName, String phone, String bookName, String writer, String loanDate, String returnDate) {
        this.userName = userName;
        this.phone = phone;
        this.bookName = bookName;
        this.writer = writer;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
}
