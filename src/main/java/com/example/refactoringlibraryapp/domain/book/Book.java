package com.example.refactoringlibraryapp.domain.book;

import com.example.refactoringlibraryapp.domain.loanhistory.LoanHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    /*
    +-----------+--------------+------+-----+-------------------+-------------------+
    | Field     | Type         | Null | Key | Default           | Extra             |
    +-----------+--------------+------+-----+-------------------+-------------------+
    | id        | bigint       | NO   | PRI | NULL              | auto_increment    |
    | name      | varchar(255) | NO   |     | NULL              |                   |
    | writer    | varchar(255) | YES  |     | NULL              |                   |
    | regi_date | timestamp    | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    +-----------+--------------+------+-----+-------------------+-------------------+
    */

    public Book(String name, String writer){
        this.name = name;
        this.writer = writer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    // UserLoanHistory와의 연관관계 처리를 위해 추가한 코드
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanHistory> userLoanHistories = new ArrayList<>();

    @Column(nullable = false, name="name")
    private String name;

    @Column(name="writer")
    private String writer;

    @Column(name="regi_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date regiDate;

    public void bookUpdate(String name, String writer) {
        this.name = name;
        this.writer = writer;
    }


}
