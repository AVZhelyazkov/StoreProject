package org.informatics.services.clientcheckout;

import org.informatics.enums.TransactionStatus;
import org.informatics.services.clientcheckout.contracts.ClientPay;
import org.informatics.services.products.ProductsTotalCalcImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTransactionTest {

    private ProductsTotalCalcImpl productsTotalCalcService;
    private ClientPay clientPayService;

    @BeforeEach
    void setUp() {
        productsTotalCalcService = mock(ProductsTotalCalcImpl.class);
        clientPayService = new ClientPayImpl(productsTotalCalcService);
    }

    @Test
    void whenBalanceIsEnough_thenPurchaseGranted() {
        BigDecimal clientBalance = BigDecimal.valueOf(100);
        int checkoutId = 0;
        when(productsTotalCalcService.calculateTotalWithDiscount(checkoutId)).thenReturn(BigDecimal.valueOf(50));

        TransactionStatus result = clientPayService.clientTransaction(clientBalance, checkoutId);

        assertEquals(TransactionStatus.PURCHASE_GRANTED, result);
    }

    @Test
    void whenBalanceIsZero_thenPurchaseDenied() {
        BigDecimal clientBalance = BigDecimal.ZERO;
        int checkoutId = 1;

        TransactionStatus result = clientPayService.clientTransaction(clientBalance, checkoutId);

        assertEquals(TransactionStatus.PURCHASE_DENIED, result);
    }

    @Test
    void whenBalanceIsLessThanTotal_thenPurchaseDenied() {
        BigDecimal clientBalance = BigDecimal.valueOf(30);
        int checkoutId = 2;
        when(productsTotalCalcService.calculateTotalWithDiscount(checkoutId)).thenReturn(BigDecimal.valueOf(50));

        TransactionStatus result = clientPayService.clientTransaction(clientBalance, checkoutId);

        assertEquals(TransactionStatus.PURCHASE_DENIED, result);
    }

    @Test
    void whenBalanceIsMinusTen_thenPurchaseDenied() {
        BigDecimal clientBalance = BigDecimal.valueOf(-10);
        int checkoutId = 1;

        TransactionStatus result = clientPayService.clientTransaction(clientBalance, checkoutId);

        assertEquals(TransactionStatus.PURCHASE_DENIED, result);
    }
}
