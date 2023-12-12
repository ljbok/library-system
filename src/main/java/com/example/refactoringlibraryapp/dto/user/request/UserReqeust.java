package com.example.refactoringlibraryapp.dto.user.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserReqeust {

    private Long id;

    private String name;
    private String phone;
    private Integer age;
    private Date date;
}
