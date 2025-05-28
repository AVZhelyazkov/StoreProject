package org.informatics.services.products;

import org.informatics.enums.ProductCategory;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.models.StoreCheckoutMachine;
import org.informatics.services.products.contracts.ProductExpiration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsTotalCalcImplTest {
    @Mock
    private StoreCheckoutMachine checkoutMachine;

    @Mock
    private Product product;

    @Mock
    private RetailStore store;

    @Mock
    private ProductExpiration expirationInterface;

    @InjectMocks
    private ProductExpirationImpl productExpirationService;

    @InjectMocks
    private ProductsTotalCalcImpl productsTotalCalcImpl;

    private final int checkoutId = 0;

    private final Map<Product, BigDecimal> scannedProducts = new HashMap<>();

    @Mock
    private Product product1;
    @Mock
    private Product product2;
    @Mock
    private Product product3;

    private Map<Integer, StoreCheckoutMachine> checkouts;

    @BeforeEach
    void setUp() {
        checkouts = new HashMap<>();
        checkouts.put(0, checkoutMachine);
    }

    @Test
    void whenCalculateTotalWithMarkupAndHasDiscountOfOneProduct_thenCorrectTotalReturned() {
        BigDecimal givenBasePrice = BigDecimal.valueOf(120.00);
        BigDecimal givenMarkup = BigDecimal.valueOf(20.00);

        BigDecimal givenDiscount = BigDecimal.valueOf(10.00);
        Integer givenDiscountDays = 5;

        BigDecimal expectedTotal = BigDecimal.valueOf(132);

        scannedProducts.put(product, BigDecimal.ONE);

        when(store.getCheckouts()).thenReturn(checkouts);
        when(checkoutMachine.getScannedProducts()).thenReturn(scannedProducts);

        when(store.getCategoryMarkupPercent()).thenReturn(Map.of(ProductCategory.CONSUMABLE, givenMarkup));

        when(store.getProductExpiryDiscountInPercent()).thenReturn(givenDiscount);
        when(store.getProductExpiryDiscountDays()).thenReturn(givenDiscountDays);

        when(product.getProductCategory()).thenReturn(ProductCategory.CONSUMABLE);
        when(product.getProductDeliveryPrice()).thenReturn(givenBasePrice);
        when(product.getProductExpiryDate()).thenReturn(LocalDate.now().plusDays(2));


        BigDecimal result = productsTotalCalcImpl.calculateTotalWithDiscount(checkoutId);
        // 120 * ((100 + 20 - 10) / 100) = 120 * 1.1 = 132

        assertEquals(expectedTotal.stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    void whenCalculateBaseTotalOfOneProduct_thenBasePriceReturned() {
        BigDecimal givenValue = BigDecimal.valueOf(50.00);
        BigDecimal expectedValue = BigDecimal.valueOf(50.00);

        scannedProducts.put(product, BigDecimal.ONE);

        Map<Integer, StoreCheckoutMachine> checkouts = new HashMap<>();
        checkouts.put(checkoutId, checkoutMachine);

        when(store.getCheckouts()).thenReturn(checkouts);
        when(checkoutMachine.getScannedProducts()).thenReturn(scannedProducts);

        when(product.getProductDeliveryPrice()).thenReturn(givenValue);

        BigDecimal result = productsTotalCalcImpl.calculateBaseTotal(checkoutId);

        assertEquals(expectedValue.stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    void whenCalculateMapWithProductsWithDiscount_thenCorrectTotalReturned() {
        Map<Product, BigDecimal> products = Map.of(
                product1, BigDecimal.TEN,
                product2, BigDecimal.ONE,
                product3, BigDecimal.valueOf(5));

        Map<ProductCategory, BigDecimal> givenMarkupForCategories = Map.of(
                ProductCategory.CONSUMABLE, BigDecimal.valueOf(15.00),
                ProductCategory.NON_CONSUMABLE, BigDecimal.valueOf(25.00)
        );

        ProductCategory givenProduct1Category = ProductCategory.CONSUMABLE;
        ProductCategory givenProduct2Category = ProductCategory.NON_CONSUMABLE;
        ProductCategory givenProduct3Category = ProductCategory.CONSUMABLE;

        BigDecimal givenProduct1BasePrice = BigDecimal.valueOf(80.00);
        BigDecimal givenProduct2BasePrice = BigDecimal.valueOf(115.00);
        BigDecimal givenProduct3BasePrice = BigDecimal.valueOf(120.00);

        BigDecimal givenStoreDiscount = BigDecimal.valueOf(10.00);

        BigDecimal expectedTotal = BigDecimal.valueOf(354.25);

        when(store.getCategoryMarkupPercent()).thenReturn(givenMarkupForCategories);

        when(store.getProductExpiryDiscountInPercent()).thenReturn(givenStoreDiscount);
        when(store.getProductExpiryDiscountDays()).thenReturn(5);

        when(store.getCheckouts()).thenReturn(Map.of(checkoutId, checkoutMachine));
        when(checkoutMachine.getScannedProducts()).thenReturn(products);

        when(product1.getProductCategory()).thenReturn(givenProduct1Category);
        when(product2.getProductCategory()).thenReturn(givenProduct2Category);
        when(product3.getProductCategory()).thenReturn(givenProduct3Category);

        when(product1.getProductDeliveryPrice()).thenReturn(givenProduct1BasePrice);
        when(product2.getProductDeliveryPrice()).thenReturn(givenProduct2BasePrice);
        when(product3.getProductDeliveryPrice()).thenReturn(givenProduct3BasePrice);

        when(product1.getProductExpiryDate()).thenReturn(LocalDate.now().plusDays(2));
        when(product2.getProductExpiryDate()).thenReturn(LocalDate.now().plusDays(1));
        when(product3.getProductExpiryDate()).thenReturn(LocalDate.now().plusDays(8));

        /*
            80.00 * ((100.00 + 15.00 - 10.00) / 100) => 80.00 * 1.05 => 84
            +
            115.00 * ((100.00 + 25.00 - 10.00) / 100) => 115.00 * 1.15 => 132.25
            +
            120.00 * ((100.00 + 15.00 - 0.00) / 100) => 120.00 * 1.15 => 138.00
            =
            354.25lv
         */

        assertEquals(expectedTotal.stripTrailingZeros(), productsTotalCalcImpl.calculateTotalWithDiscount(checkoutId).stripTrailingZeros());
    }
}