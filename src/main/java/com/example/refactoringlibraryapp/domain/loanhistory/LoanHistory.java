package com.example.refactoringlibraryapp.domain.loanhistory;

import com.example.refactoringlibraryapp.domain.book.Book;
import com.example.refactoringlibraryapp.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user_loan_history")
@Getter
@NoArgsConstructor
public class LoanHistory {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    // user와의 연관관계 처리를 수행하기 전
    //@Column(nullable = false, name="userId")
    //private Integer userId;

    // user와의 연관관계 처리를 수행한 후
    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    // book와의 연관관계 처리를 수행하기 전
    //@Column(nullable = false, name="book_id")
    // private Long bookId;

    // book와의 연관관계 처리를 수행한 후
    @JoinColumn(nullable = false)
    @ManyToOne
    private Book book;

    @Column(name="loan_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date loanDate;

    @Column(name="return_date")
    private Date returnDate;

    public LoanHistory(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}
