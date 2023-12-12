package com.example.refactoringlibraryapp.service.book;

import com.example.refactoringlibraryapp.domain.book.Book;
import com.example.refactoringlibraryapp.domain.book.BookRepository;
import com.example.refactoringlibraryapp.dto.book.request.BookRequest;
import com.example.refactoringlibraryapp.dto.book.response.BookReponse;
import com.example.refactoringlibraryapp.dto.search.response.BookSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // # 도서 등록
    @Transactional
    public void createBook(BookRequest request){
        if(repository.existsByNameAndWriter(request.getName(), request.getWriter())) {
            throw new IllegalArgumentException("이미 존재하는 도서입니다.");
        }

        repository.save(new Book(request.getName(), request.getWriter()));
    }

    // # 도서 리스트 출력
    @Transactional
    public List<BookReponse> bookList() {
        return repository.findAll().stream()
                 .map(book -> new BookReponse(book))
                    .collect(Collectors.toList());
    }

    // # 수정 step - (1) id 로 책 정보 불러오기
    @Transactional
    public BookReponse getBook(Long id){
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

        return new BookReponse(book);
    }

    // # 수정 step - (2) 수정할 책 이름과 저자를 입력하고
    //                  그 책이 이미 존재하지 않는 경우에만 수정 내용 변경 실행
    @Transactional
    public void bookUpdate(BookRequest bookRequest){
        boolean check = repository.existsByNameAndWriter(bookRequest.getName(), bookRequest.getWriter());
        if (check){
            throw  new IllegalArgumentException("이미 존재하는 도서입니다.");
        }

        Book book = repository.findById(bookRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

        book.bookUpdate(bookRequest.getName(),bookRequest.getWriter());
    }

    // # 도서 삭제 시 (id를 받아 삭제를 수행한다.)
    @Transactional
    public void bookDelete(Long id){
        boolean check = repository.existsById(id);
        if (!check){
            throw new IllegalArgumentException("존재하지 않는 도서입니다.");
        }
        repository.deleteById(id);
    }

    // 도서 검색 (parameter 으로 책이름과 저자를 전달 받아 검색 실행)
    @Transactional
    public List<BookSearchResponse> bookSearch(String bookName, String writer){
        boolean check = repository.existsByNameContainingAndWriterContaining(bookName, writer);
        if (!check){
            throw  new IllegalArgumentException("존재하지 않는 도서입니다.");
        }

        List<BookSearchResponse> bookSearchResponseList = new ArrayList<BookSearchResponse>();

        //entity 가 book인 bookRepository에서 메소드의 반환형을 List<Object[]> 로 받고 그것을 List<BookSearchResponse>로 변경해주는 작업
        // 즉 entity사 book 인 repository에서 메소드의 반환형을 BookSearchReponse로 받고싶어서 컨터빙 해주는 메소드를 service에 정의한 것
        List<Object[]> searchResults = repository.bookSearch(bookName,writer);
        for (Object[] row : searchResults) {
            String bookResult = (String) row[0]; //bookName
            String writerResult = (String) row[1]; //writer

            String userName = "";
            if (row[2] != null)
                userName = (String) row[2]; // userName

            String phone = "";
            if (row[3] != null)
                phone = (String) row[3]; // phone

            String loanDate = "";
            if (row[4] != null)
                loanDate = (String) row[4]; // phone

            String returnDate = "";
            if(row[4] != null && row[5] != null)
                returnDate = (String) row[5]; // returnDate

            bookSearchResponseList.add(new BookSearchResponse(bookResult,writerResult,userName,phone,loanDate,returnDate));
        }

        if(bookSearchResponseList.size() == 0){
            throw new IllegalArgumentException("검색 결과가 존재하지 않습니다.");
        }

        return bookSearchResponseList;
    }

}
