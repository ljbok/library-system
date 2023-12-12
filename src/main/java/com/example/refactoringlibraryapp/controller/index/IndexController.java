package com.example.refactoringlibraryapp.controller.index;

import com.example.refactoringlibraryapp.dto.book.response.BookReponse;
import com.example.refactoringlibraryapp.dto.user.response.UserResponse;
import com.example.refactoringlibraryapp.service.book.BookService;
import com.example.refactoringlibraryapp.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
/*@RequestMapping("refactoring-library-app")*/
public class IndexController {

    private final HttpServletRequest request;
    private final UserService userService;
    private final BookService bookService;

    public IndexController(HttpServletRequest request, UserService userService, BookService bookService) {
        this.request = request;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/index1")
    public ModelAndView indexPage(ModelAndView mav){

        mav.setViewName("index1");
        return mav;
    }

    @GetMapping("/index2")
    public ModelAndView indexPage2(ModelAndView mav){

        mav.setViewName("index2");
        return mav;
    }

    @GetMapping("/user-modify")
    public ModelAndView userModify(ModelAndView mav){

        Long id = Long.parseLong(request.getParameter("id"));

        UserResponse userResponse = userService.getUser(id);

        mav.setViewName("user-modify");
        mav.addObject("user", userResponse);
        return mav;
    }

    @GetMapping("/book-modify")
    public ModelAndView bookModify(ModelAndView mav){

        Long id = Long.parseLong(request.getParameter("id"));

        BookReponse bookReponse = bookService.getBook(id);

        mav.setViewName("book-modify");
        mav.addObject("book", bookReponse);
        return mav;
    }
}
