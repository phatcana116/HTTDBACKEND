package com.example.HTTD.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="debt")
public class Debt {
    @Id
    @Column(name="id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name", length = 255)
    private String name;
    @Column(name="amount")
    private Float amount;

    @Column(name="startDay")
    private Date startDay;
    @Column(name="endDay")
    private Date endDay;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;
}
