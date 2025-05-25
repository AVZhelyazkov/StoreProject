package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.contracts.storemanagement.contracts.CalculateStoreProfit;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CalculateStoreProfitImpl implements CalculateStoreProfit {
    private RetailStore store;
    private CalculateStoreRevenueImpl revenueService;
    private CalculateStoreExpensesImpl expensesService;
    @Override
    public BigDecimal calcNetProfit() {
        return revenueService.calculateStoreRevenue().subtract(expensesService.calcStoreExpenses());
    }
}