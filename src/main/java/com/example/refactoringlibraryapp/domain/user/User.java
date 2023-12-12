package com.example.refactoringlibraryapp.domain.user;

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
public class User {
    /*
    +-----------+-------------+------+-----+-------------------+-------------------+
    | Field     | Type        | Null | Key | Default           | Extra             |
    +-----------+-------------+------+-----+-------------------+-------------------+
    | id        | bigint      | NO   | PRI | NULL              | auto_increment    |
    | name      | varchar(25) | NO   |     | NULL              |                   |
    | age       | int         | YES  |     | NULL              |                   |
    | phone     | varchar(15) | YES  |     | NULL              |                   |
    | join_date | timestamp   | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    +-----------+-------------+------+-----+-------------------+-------------------+
    */


    public User(String name, Integer age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    // user 와의 연관관계 처리를 위해 추가한 코드  userLoanHistory가 Many 이고 user 가 one 이므로
    // 변수 타입은 List가 되어야 한다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanHistory> userLoanHistories = new ArrayList<>();

    @Column(nullable = false, length = 25, name="name")
    private String name;

    @Column(nullable = false, name="age")
    private Integer age;

    @Column(nullable = false, length = 15, name="phone")
    private String phone;

    @Column(name="join_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date joinDate;

    public void updateUser(String name, String phone, Integer age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }

}
