package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name="wallet")
public class Wallet {
    @Id
    @Column(name="id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name", length = 255)
    private String name;
    @Column(name="amount", length = 255)
    private Float amount;
    @Column(name="date_created")
    private Date date_created;

    @PrePersist
    protected void onCreate() {
        this.date_created = new Date();
    }

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private Set<Expense> expense;
}
