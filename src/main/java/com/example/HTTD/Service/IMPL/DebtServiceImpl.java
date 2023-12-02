package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Debt;
import com.example.HTTD.Repository.DebtRepository;
import com.example.HTTD.Service.DebtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DebtServiceImpl implements DebtService {

    private DebtRepository debtRepository;
    @Override
    public Debt createDebt(Debt debt) {
        return debtRepository.save(debt);
    }

    @Override
    public Debt getDebtById(Long debtId) {
        Optional<Debt> optionalDebt = debtRepository.findById(debtId);
        return optionalDebt.orElse(null);
    }

    @Override
    public List<Debt> getAllDebt() {
        return debtRepository.findAll();
    }

    @Override
    public Debt updateDebt(Debt debt) {
        Debt existingdebt = debtRepository.findById(debt.getId()).get();
        existingdebt.setName(debt.getName());
        existingdebt.setAmount(debt.getAmount());
        existingdebt.setStartDay(debt.getStartDay());
        existingdebt.setEndDay(debt.getEndDay());
        Debt updateddebt = debtRepository.save(existingdebt);
        return updateddebt;
    }

    @Override
    public boolean deleteDebt(Long debtId) {
        Optional<Debt> debtOptional = debtRepository.findById(debtId);
        if (debtOptional.isPresent()) {
            debtRepository.deleteById(debtId);
            return true;
        }
        return false;
    }
}
