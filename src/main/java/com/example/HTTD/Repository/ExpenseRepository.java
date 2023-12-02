package com.example.HTTD.Repository;

import com.example.HTTD.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE MONTH(e.date_created) = :month AND YEAR(e.date_created) = :year")
    List<Expense> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
