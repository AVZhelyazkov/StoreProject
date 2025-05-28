package org.informatics.services.storemanagement.calcimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.informatics.services.storemanagement.contracts.CalculateStoreExpenses;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CalculateStoreExpensesImpl implements CalculateStoreExpenses {

    private final RetailStore store;
    @Override
    public BigDecimal calcStoreExpenses() {
        return store.getPersonnel().stream()
                .map(StaffPersonnel::getStaffSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(
                        store.getProductsInStock().keySet().stream()
                                .map(Product::getProductDeliveryPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                );
    }
}
