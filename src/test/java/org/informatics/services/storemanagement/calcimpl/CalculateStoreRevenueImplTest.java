package org.informatics.services.storemanagement.calcimpl;

import org.informatics.models.Product;
import org.informatics.models.Receipt;
import org.informatics.models.RetailStore;
import org.informatics.models.StoreCheckoutMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateStoreRevenueImplTest {

    private final int checkoutId = 1;

    @Mock
    private RetailStore store;

    @Mock
    private StoreCheckoutMachine checkout1;

    @Mock
    private Receipt receipt1;

    @Mock
    private Receipt receipt2;

    @Mock
    private Product product1;

    @Mock
    private Product product2;

    @InjectMocks
    private CalculateStoreRevenueImpl revenueService;

    @BeforeEach
    void setup() {
        BigDecimal givenReceipt1Total = BigDecimal.valueOf(150);
        BigDecimal givenReceipt2Total = BigDecimal.valueOf(250);

        BigDecimal givenProduct1BasePrice = BigDecimal.valueOf(50.00);
        BigDecimal givenProduct2BasePrice = BigDecimal.valueOf(75.00);

        when(receipt1.getReceiptTotal()).thenReturn(givenReceipt1Total);
        when(receipt2.getReceiptTotal()).thenReturn(givenReceipt2Total);

        when(receipt1.getReceiptProducts()).thenReturn(List.of(product1));
        when(receipt2.getReceiptProducts()).thenReturn(List.of(product2));

        when(product1.getProductDeliveryPrice()).thenReturn(givenProduct1BasePrice);
        when(product2.getProductDeliveryPrice()).thenReturn(givenProduct2BasePrice);

        when(checkout1.getCachedReceipts()).thenReturn(Set.of(receipt1, receipt2));

        when(store.getCheckouts()).thenReturn(Map.of(checkoutId, checkout1));
    }

    @Test
    void whenCalculateRevenue_thenCorrectResultReturned() {
        BigDecimal expected = BigDecimal.valueOf(275.00);
        BigDecimal result = revenueService.calculateStoreRevenue();

        assertEquals(expected.stripTrailingZeros(), result.stripTrailingZeros());
    }
}