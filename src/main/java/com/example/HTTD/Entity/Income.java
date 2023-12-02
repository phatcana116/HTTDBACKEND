package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="income")
public class Income {
    @Id
    @Column(name="id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name", length = 255)
    private String Name;
    @Column(name="amount", length = 255)
    private Float amount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
