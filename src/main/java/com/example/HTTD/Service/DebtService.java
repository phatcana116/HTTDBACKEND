package com.example.HTTD.Service;

import com.example.HTTD.Entity.Debt;

import java.util.List;

public interface DebtService {
    Debt createDebt(Debt debt);

    Debt getDebtById(Long debtId);

    List<Debt> getAllDebt();

    Debt updateDebt(Debt debt);

    boolean deleteDebt(Long debtId);
}
