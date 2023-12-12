package com.example.refactoringlibraryapp.service.loanhistory;

import com.example.refactoringlibraryapp.domain.book.Book;
import com.example.refactoringlibraryapp.domain.book.BookRepository;
import com.example.refactoringlibraryapp.domain.loanhistory.LoanHistory;
import com.example.refactoringlibraryapp.domain.loanhistory.LoanHistoryRepository;
import com.example.refactoringlibraryapp.domain.user.User;
import com.example.refactoringlibraryapp.domain.user.UserRepository;
import com.example.refactoringlibraryapp.dto.loanhistory.request.LoanHistoryRequest;
import com.example.refactoringlibraryapp.dto.loanhistory.response.LoanHistoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanHistoryService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanHistoryRepository loanHistoryRepository;

    public LoanHistoryService(UserRepository userRepository,
                              BookRepository bookRepository,
                              LoanHistoryRepository loanHistoryRepository) {

        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanHistoryRepository = loanHistoryRepository;
    }

    // # 도서 대출
    @Transactional
    public void loanBook(LoanHistoryRequest request){
        
        User user = userRepository.findIdByNameAndPhone(request.getUserName(), request.getPhone())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Book book = bookRepository.findIdByNameAndWriter(request.getBookName(), request.getWriter())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 도서입니다."));

       boolean check = loanHistoryRepository.existsByBookIdAndReturnDate(book.getId(), null);
       if (check){
           throw new IllegalArgumentException("이미 대여 중인 도서입니다.");
       }

       loanHistoryRepository.save(new LoanHistory(user, book));
    }
    
    // # 도서 반납
    @Transactional
    public void returnBook(LoanHistoryRequest request){

        User user = userRepository.findIdByNameAndPhone(request.getUserName(), request.getPhone())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Book book = bookRepository.findIdByNameAndWriter(request.getBookName(), request.getWriter())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 도서입니다."));

        LoanHistory loanHistory = loanHistoryRepository.findByUserIdAndBookIdAndReturnDate(user.getId(), book.getId(), null)
                .orElseThrow(()-> new IllegalArgumentException("반납할 도서가 존재하지 않습니다."));

        loanHistoryRepository.bookReturn(loanHistory.getId());
    }

    // # 대출 이력 출력
    @Transactional
    public List<LoanHistoryResponse> loanHistory(){
        List<LoanHistoryResponse> loanHisotries = loanHistoryRepository.findAll().stream()
                 .map(loanHistory -> {
                     // search
                     User user = userRepository.findById(loanHistory.getUser().getId())
                             .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
                     Book book = bookRepository.findById(loanHistory.getBook().getId())
                             .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

                     LoanHistoryResponse loanHistoryResponse = new LoanHistoryResponse(loanHistory);
                     loanHistoryResponse.setDetails(user.getName(), user.getPhone(), book.getName(), book.getWriter());
                     return loanHistoryResponse;
                 })
                 .collect(Collectors.toList());

        if (loanHisotries.isEmpty()) {
            throw new IllegalArgumentException("대출 이력이 없습니다.");
        }

        return loanHisotries;
    }
}
