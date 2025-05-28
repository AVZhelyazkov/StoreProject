package org.informatics.services.storemanagement.calcimpl;

import org.informatics.models.RetailStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateStoreProfitImplTest {

    @Mock
    private RetailStore store;

    @Mock
    private CalculateStoreRevenueImpl revenueService;

    @Mock
    private CalculateStoreExpensesImpl expensesService;

    @InjectMocks
    private CalculateStoreProfitImpl profitService;

    @BeforeEach
    void setUp() {
        BigDecimal givenStoreRevenue = BigDecimal.valueOf(10000.00);
        BigDecimal givenStoreExpenses = BigDecimal.valueOf(3000.00);

        when(revenueService.calculateStoreRevenue()).thenReturn(givenStoreRevenue);
        when(expensesService.calcStoreExpenses()).thenReturn(givenStoreExpenses);
    }

    @Test
    void whenCalculatingStoreProfit_thenCorrectProfitReturned() {
        BigDecimal expected = BigDecimal.valueOf(7000.00);
        BigDecimal result = profitService.calcNetProfit();
        assertEquals(expected.stripTrailingZeros(), result.stripTrailingZeros());
    }
}