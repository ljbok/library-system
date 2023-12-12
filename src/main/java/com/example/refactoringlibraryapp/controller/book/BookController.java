package com.example.refactoringlibraryapp.controller.book;

import com.example.refactoringlibraryapp.dto.book.request.BookRequest;
import com.example.refactoringlibraryapp.dto.book.response.BookReponse;
import com.example.refactoringlibraryapp.dto.search.response.BookSearchResponse;
import com.example.refactoringlibraryapp.service.book.BookService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("refactoring-library-app")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // # 도서 등록 매핑
    @PostMapping("/book")
    public void createBook(@RequestBody BookRequest request){
        bookService.createBook(request);
    }

    // # 도서 리스트 출력 매핑
    @GetMapping("/books")
    public List<BookReponse> bookList(){
        return bookService.bookList();
    }

    // # 도서 id로 특정 도서 정보 불러오기
    @GetMapping("/book")
    public BookReponse getBook(@RequestParam Long id){
        return bookService.getBook(id);
    }

    @PutMapping("/book")
    public void bookUpdate(@RequestBody BookRequest bookRequest){
        bookService.bookUpdate(bookRequest);
    }

    @DeleteMapping("/book")
    public void bookDelete(@RequestParam Long id){
        bookService.bookDelete(id);
    }

    // 검색 기능 추가

    @GetMapping("/book-search")
    public List<BookSearchResponse> bookSearch(@RequestParam String bookName, @RequestParam String writer) {
         return bookService.bookSearch(bookName, writer);
    }

}
