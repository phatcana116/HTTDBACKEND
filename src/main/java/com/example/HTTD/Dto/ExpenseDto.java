package com.example.HTTD.Dto;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ExpenseDto {
    private String name;
    private String description;
    private Float amount;
    private Long categoryId;
    private Long walletId;
    private Date date_created;

    private String category;
    private Float totalAmount;

    public ExpenseDto(String category, Float totalAmount) {
    }
}
