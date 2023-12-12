package com.example.refactoringlibraryapp.controller.user;

import com.example.refactoringlibraryapp.dto.search.response.BookSearchResponse;
import com.example.refactoringlibraryapp.dto.search.response.UserSearchResponse;
import com.example.refactoringlibraryapp.dto.user.request.UserReqeust;
import com.example.refactoringlibraryapp.dto.user.response.UserResponse;
import com.example.refactoringlibraryapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("refactoring-library-app")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 등록
    @PostMapping("/user")
    public void createUser(@RequestBody UserReqeust request){
        userService.createUser(request);
    }

    // 사용자 리스트 출력
    @GetMapping("/users")
    public List<UserResponse> userList() {
        return userService.userList();
    }

    // 수정 #1 param id로 특정 사용자 정보 가지고 오기 (-> 수정 폼 불러오기)
    @PostMapping("/getuser")
    public UserResponse getUser(@RequestBody UserReqeust request) {
        return userService.getUser(request.getId());
    }

    // 수정 #2 수정할 데이터 넣고 수정 버튼 클릭 시 수행
    @PutMapping("/user")
    public void userUpdate(@RequestBody UserReqeust request) {
       // System.out.println("컨트롤러의 수정 매핑 탐");
        userService.updateUser(request);
    }

    // 삭제 버튼 클릭 시 수행
    @DeleteMapping("/user")
    public void userDelete(@RequestParam Long id) {
        userService.userDelete(id);
    }

    @GetMapping("/user-search")
    public List<UserSearchResponse> userSearch(@RequestParam String username, @RequestParam String phone){
        //System.out.println("유저 검색 탔음");
        return userService.userSearch(username, phone);
    }

}
