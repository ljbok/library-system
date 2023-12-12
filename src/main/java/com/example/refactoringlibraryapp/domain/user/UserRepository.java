package com.example.refactoringlibraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 사용자 등록 시 이미 존재하는 회원인지 확인하기 위한 jpa
    boolean existsByPhone(String phone);

    // 사용자 id로 사용자 정보 가지고 오는 메소드 (수정 - 1단계)
    @Override
    Optional<User> findById(Long id);

    // 사용자 수정 시 이미 다른 회원이 사용중인 연락처인지 확인하기 위한 jpa
    // SELECT COUNT(*) FROM user WHERE phone = ? AND id != ?
    boolean existsByPhoneAndIdNot(String phone, Long id);

    // 대출 시 사용자 이름으로 사용자 id 가지고 오는 로직
    Optional<User> findIdByNameAndPhone(String name, String phone);

    // 사용자 검색
    @Query(value="SELECT user.name AS userName, user.phone AS phone, book.name AS bookName, book.writer AS bookWriter, DATE_FORMAT(history.loan_date, '%Y-%m-%d %H:%i') AS loanDate, IFNULL(DATE_FORMAT(history.return_date, '%Y-%m-%d %H:%i'),'미반납') AS returnDate " +
            "FROM user " +
            "LEFT JOIN user_loan_history history ON history.user_id = user.id " +
            "LEFT JOIN book ON history.book_id = book.id " +
            "WHERE user.name =:username AND user.phone =:phone " +
            "ORDER BY history.loan_date DESC",nativeQuery = true)
    List<Object[]> userSearch(@Param("username") String username , @Param("phone") String phone);
}
