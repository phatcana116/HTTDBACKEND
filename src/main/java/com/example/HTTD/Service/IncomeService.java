package com.example.HTTD.Service;

import com.example.HTTD.Entity.Income;

import java.util.List;

public interface IncomeService {
    Income create(Income income);

    Income getById(Long id);

    List<Income> getAll();

    Income update(Income income);

    boolean delete(Long id);
}
