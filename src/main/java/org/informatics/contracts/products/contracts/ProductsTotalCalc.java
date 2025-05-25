package org.informatics.contracts.products.contracts;

import java.math.BigDecimal;

public interface ProductsTotalCalc {
    BigDecimal calculateTotalWithDiscount(int checkoutId);
    BigDecimal calculateBaseTotal(int checkoutId);
}
