package com.example.refactoringlibraryapp.domain.book;

import com.example.refactoringlibraryapp.dto.search.response.BookSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    // 이미 존재하는 도서인지 확인
    boolean existsByNameAndWriter(String name, String writer);

    // id로 도서 정보 불러오기
    Optional<Book> findById(Long id);

    // 대출 - 도서 이름과 저자 이름으로 도서 id 가지고 오기
    Optional<Book> findIdByNameAndWriter(String name, String writer);

    // 도서 검색 결과 있는지
    boolean existsByNameContainingAndWriterContaining(String name, String writer);

    // 책 검사 결과
    // DATE_FORMAT(your_date_column, '%Y-%m-%d %H:%i') 적용 후
    @Query(value = "SELECT book.name AS bookName, book.writer AS writer, user.name AS userName, user.phone AS phone,  DATE_FORMAT(history.loan_date, '%Y-%m-%d %H:%i') AS loanDate, IFNULL(DATE_FORMAT(history.return_date, '%Y-%m-%d %H:%i'),'미반납') AS returnDate"
                    + " FROM book LEFT JOIN user_loan_history history on history.book_id = book.id LEFT JOIN user on history.user_id = user.id"
                    + " WHERE book.name LIKE CONCAT('%',:bookname,'%') AND book.writer LIKE CONCAT('%',:writer,'%') ORDER BY history.loan_date DESC LIMIT 1",nativeQuery = true)
    List<Object[]> bookSearch(@Param("bookname") String bookName, @Param("writer") String writer);

}
