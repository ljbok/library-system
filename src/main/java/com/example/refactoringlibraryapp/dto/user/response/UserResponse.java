package com.example.refactoringlibraryapp.dto.user.response;

import com.example.refactoringlibraryapp.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String name;
    private String phone;
    private Integer age;
    private Date date;
    
    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.age = user.getAge();
        this.date = user.getJoinDate();
    }
}
