package com.example.HTTD.Dto;

public class UserResponseDto {
    private String username;
    // Include other non-sensitive user information here

    public UserResponseDto(String username /*, add other fields as needed*/) {
        this.username = username;
        // Set other fields
    }

    // Generate getters and setters
}
