package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Dto.ExpenseDto;
import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Repository.ExpenseRepository;
import com.example.HTTD.Service.ExpenseService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getExpensesByMonthAndYear(int month, int year) {
        return expenseRepository.findByMonthAndYear(month, year);
    }

    @Override
    public List<ExpenseDto> getExpenseChartData(int month, int year) {
        String queryString = "SELECT e.category.name AS category, SUM(e.amount) AS totalAmount " +
                "FROM Expense e " +
                "WHERE MONTH(e.date_created) = :month AND YEAR(e.date_created) = :year " +
                "GROUP BY e.category.name";

        Query query = entityManager.createQuery(queryString)
                .setParameter("month", month)
                .setParameter("year", year);

        List<Object[]> results = query.getResultList();
        return mapToExpenseDTOList(results);
    }
    private List<ExpenseDto> mapToExpenseDTOList(List<Object[]> results) {
        List<ExpenseDto> expenseDTOList = new ArrayList<>();

        for (Object[] result : results) {
            String category = (String) result[0];
            Float totalAmount = (Float) result[1];

            ExpenseDto expenseDto = new ExpenseDto(category, totalAmount);
            expenseDTOList.add(expenseDto);
        }

        return expenseDTOList;
    }
    @Override
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(Long expenseId) {
        Optional<Expense> optional = expenseRepository.findById(expenseId);
        return optional.orElse(null);
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense updateExpense(Expense expense) {
        Expense existing = expenseRepository.findById(expense.getId()).get();
        existing.setName(expense.getName());
        existing.setAmount(expense.getAmount());
        existing.setDescription(expense.getDescription());
        existing.setCategory(expense.getCategory());
        existing.setWallet(expense.getWallet());
        Expense updated = expenseRepository.save(existing);
        return updated;
    }

    @Override
    public boolean deleteExpense(Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isPresent()) {
            expenseRepository.deleteById(expenseId);
            return true;
        }
        return false;
    }
}
