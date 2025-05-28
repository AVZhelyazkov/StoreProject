package org.informatics.services.products;

import org.informatics.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductExpirationImplTest {

    @InjectMocks
    private ProductExpirationImpl expiration;
    @Mock
    private Product product;

    @BeforeEach
    void setUp() {}

    @Test
    void whenProductIsExpired_thenTrue() {
        LocalDate dateForTest = LocalDate.of(2025, 5, 23);

        when(product.getProductExpiryDate()).thenReturn(dateForTest);

        assertTrue(expiration.isExpired(product));
    }

    @Test
    void whenProductIsNotExpired_thenFalse() {
        LocalDate dateForTest = LocalDate.now().plusDays(3);

        when(product.getProductExpiryDate()).thenReturn(dateForTest);

        assertFalse(expiration.isExpired(product));
    }
}