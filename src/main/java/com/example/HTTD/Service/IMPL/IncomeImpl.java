package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Income;
import com.example.HTTD.Repository.IncomeRepository;
import com.example.HTTD.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeImpl implements IncomeService {
    @Autowired
    IncomeRepository incomeRepository;
    @Override
    public Income create(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public Income getById(Long id) {
        Optional<Income> optional = incomeRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Income> getAll() {
        return incomeRepository.findAll();
    }

    @Override
    public Income update(Income income) {
        Income existing = incomeRepository.findById(income.getId()).get();
        existing.setName(income.getName());
        existing.setAmount(income.getAmount());
        existing.setUser(income.getUser());
        Income updated = incomeRepository.save(existing);
        return updated;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Income> Optional = incomeRepository.findById(id);
        if (Optional.isPresent()) {
            incomeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
