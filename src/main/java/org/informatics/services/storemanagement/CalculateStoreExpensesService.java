package org.informatics.services.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.interfaces.CalculateStoreExpensesInterface;

@RequiredArgsConstructor
public class CalculateStoreExpensesService implements CalculateStoreExpensesInterface {

    private RetailStore store;
    @Override
    public void getTotalExpense() {

    }
}
