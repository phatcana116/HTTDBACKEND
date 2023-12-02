package com.example.HTTD.reponse;

import com.example.HTTD.Entity.Role;
import lombok.*;


import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Byte gender;
    private Date birthday;
    private String phone;
    private Set<Role> roles;
}
