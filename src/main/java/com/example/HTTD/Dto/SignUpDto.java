package com.example.HTTD.Dto;

import lombok.*;

import java.util.Date;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private Byte gender;
    private Date birthday;
    private String phone;
    private String email;
    private String password;
}
