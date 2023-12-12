package com.example.refactoringlibraryapp.domain.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {

    // 해당 책이 대출 가능한 상태인지
    boolean existsByBookIdAndReturnDate(Long bookId, Date returnDate);

    // 해당 책에 내 미반납의 대출이력이 있는지
    boolean existsByUserIdAndBookIdAndReturnDate(Long userId, Long bookId, Date returnDate);

    // 반납
    Optional<LoanHistory> findByUserIdAndBookIdAndReturnDate(Long userId, Long bookId, Date returnDate);

    // 반납
    @Modifying
    @Query("update user_loan_history set return_date = CURRENT_TIMESTAMP where id=:id")
    void bookReturn(@Param("id") Long id);
}
