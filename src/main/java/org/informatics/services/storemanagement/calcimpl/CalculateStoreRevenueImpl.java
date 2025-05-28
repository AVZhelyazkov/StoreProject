package org.informatics.services.storemanagement.calcimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Product;
import org.informatics.models.Receipt;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.contracts.CalculateStoreRevenue;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CalculateStoreRevenueImpl implements CalculateStoreRevenue {
    private RetailStore store;
    @Override
    public BigDecimal calculateStoreRevenue() {
        BigDecimal allReceiptsTotalSummed = store.getCheckouts().values().stream()
                .flatMap(checkout -> checkout.getCachedReceipts().stream())
                .map(Receipt::getReceiptTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal allBaseProductPriceSummed = store.getCheckouts().values().stream()
                .flatMap(checkout -> checkout.getCachedReceipts().stream())
                .flatMap(receipt -> receipt.getReceiptProducts().stream())
                .map(Product::getProductDeliveryPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return allReceiptsTotalSummed.subtract(allBaseProductPriceSummed);
    }
}
