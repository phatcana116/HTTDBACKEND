package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name="expense")
public class Expense {
    @Id
    @Column(name="id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name", length = 255)
    private String name;
    @Column(name="description", length = 255)
    private String description;
    @Column(name="amount")
    private Float amount;
    @Column(name="date_created")
    private Date date_created;


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

}
