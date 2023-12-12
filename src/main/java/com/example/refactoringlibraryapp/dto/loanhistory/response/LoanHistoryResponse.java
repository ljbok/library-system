package com.example.refactoringlibraryapp.dto.loanhistory.response;

import com.example.refactoringlibraryapp.domain.loanhistory.LoanHistory;
import lombok.Getter;

import java.util.Date;

@Getter
public class LoanHistoryResponse {

    /*
    +-------------+-----------+------+-----+-------------------+-------------------+
    | Field       | Type      | Null | Key | Default           | Extra             |
    +-------------+-----------+------+-----+-------------------+-------------------+
    | id          | bigint    | NO   | PRI | NULL              | auto_increment    |
    | user_id     | bigint    | NO   |     | NULL              |                   |
    | book_id     | bigint    | NO   |     | NULL              |                   |
    | loan_date   | timestamp | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | return_date | timestamp | YES  |     | NULL              |                   |
    +-------------+-----------+------+-----+-------------------+-------------------+
    */

    private Long id;
    private Long userId;
    private Long bookId;
    private Date loanDate;
    private Date returnDate;

    private String userName;
    private String phone;
    private String bookName;
    private String writer;

    public LoanHistoryResponse(LoanHistory loanHistory) {
        this.id = loanHistory.getId();
        this.userId = loanHistory.getUser().getId();
        this.bookId = loanHistory.getBook().getId();
        this.loanDate = loanHistory.getLoanDate();
        this.returnDate = loanHistory.getReturnDate();
    }

    public void setDetails (String userName, String phone, String bookName, String writer){
        this.userName = userName;
        this.phone = phone;
        this.bookName = bookName;
        this.writer = writer;
    }
}
