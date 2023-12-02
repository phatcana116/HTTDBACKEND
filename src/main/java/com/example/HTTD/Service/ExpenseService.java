package com.example.HTTD.Service;

import com.example.HTTD.Dto.ExpenseDto;
import com.example.HTTD.Entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getExpensesByMonthAndYear(int month, int year);
    List<ExpenseDto> getExpenseChartData(int month, int year);
    Expense createExpense(Expense expense);

    Expense getExpenseById(Long expenseId);

    List<Expense> getAllExpense();

    Expense updateExpense(Expense expense);

    boolean deleteExpense(Long expenseId);
}
