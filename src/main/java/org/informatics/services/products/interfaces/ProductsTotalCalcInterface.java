package org.informatics.services.products.interfaces;

import java.math.BigDecimal;

public interface ProductsTotalCalcInterface {
    BigDecimal calculateTotalWithDiscount(int checkoutId);
    BigDecimal calculateBaseTotal(int checkoutId);
}
