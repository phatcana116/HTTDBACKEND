package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name="id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", length = 255)
    private String name;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private List<Expense> expense;
}
