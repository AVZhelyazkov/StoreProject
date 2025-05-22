package org.informatics.services.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.informatics.services.storemanagement.interfaces.CalculateStoreExpensesInterface;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CalculateStoreExpensesService implements CalculateStoreExpensesInterface {

    private RetailStore store;
    @Override
    public BigDecimal calcStoreExpenses() {
        return store.getPersonnel().stream()
                .map(StaffPersonnel::getStaffSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(
                        store.getProductsInStock().stream()
                                .map(Product::getProductDeliveryPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                );
    }
}
