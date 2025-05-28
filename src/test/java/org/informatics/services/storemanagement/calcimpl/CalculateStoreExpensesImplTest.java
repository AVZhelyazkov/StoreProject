package org.informatics.services.storemanagement.calcimpl;

import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateStoreExpensesImplTest {

    @Mock
    private RetailStore store;

    @Mock
    private StaffPersonnel employee1;
    @Mock
    private StaffPersonnel employee2;

    @Mock
    private Product product1;
    @Mock
    private Product product2;

    @InjectMocks
    private CalculateStoreExpensesImpl expensesService;

    @BeforeEach
    void setUp() {
        BigDecimal givenEmployee1Salary = BigDecimal.valueOf(2400.00);
        BigDecimal givenEmployee2Salary = BigDecimal.valueOf(1800.00);
        BigDecimal givenProduct1BasePrice = BigDecimal.valueOf(30.99);
        BigDecimal givenProduct2BasePrice = BigDecimal.valueOf(50.99);

        when(employee1.getStaffSalary()).thenReturn(givenEmployee1Salary);
        when(employee2.getStaffSalary()).thenReturn(givenEmployee2Salary);

        when(product1.getProductDeliveryPrice()).thenReturn(givenProduct1BasePrice);
        when(product2.getProductDeliveryPrice()).thenReturn(givenProduct2BasePrice);

        when(store.getPersonnel()).thenReturn(Set.of(employee1, employee2));
        when(store.getProductsInStock()).thenReturn(
                Map.of(
                        product1, BigDecimal.TEN,
                        product2, BigDecimal.ONE));
    }

    @Test
    void whenCalculatingStoreExpenses_thenCorrectTotalReturned() {
        BigDecimal result = expensesService.calcStoreExpenses();
        BigDecimal expected = BigDecimal.valueOf(4281.98);
        assertEquals(expected.stripTrailingZeros(), result.stripTrailingZeros());
    }
}