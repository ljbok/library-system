package com.example.refactoringlibraryapp.controller.loanhistory;

import com.example.refactoringlibraryapp.dto.loanhistory.request.LoanHistoryRequest;
import com.example.refactoringlibraryapp.dto.loanhistory.response.LoanHistoryResponse;
import com.example.refactoringlibraryapp.service.loanhistory.LoanHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("refactoring-library-app")
public class LoanHistoryController {

    private final LoanHistoryService loanHistoryService;

    public LoanHistoryController(LoanHistoryService loanHistoryService) {
        this.loanHistoryService = loanHistoryService;
    }

    @PostMapping("/loan")
    public void loanBook(@RequestBody LoanHistoryRequest loanHistoryRequest){
        loanHistoryService.loanBook(loanHistoryRequest);
    }

    @PutMapping("/return")
    public void returnBook(@RequestBody LoanHistoryRequest loanHistoryRequest){
        loanHistoryService.returnBook(loanHistoryRequest);
    }

    @GetMapping("/loanhistory")
    public List<LoanHistoryResponse> loanHistory(){
        return loanHistoryService.loanHistory();
    }
}
