package org.informatics.models;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Receipt implements Serializable {
    private static long receiptCounterId;

    private final long receiptId;
    private final UUID receiptIssuer;
    private final List<Product> receiptProducts;
    private final BigDecimal receiptTotal;
    private final LocalDateTime receiptDateTime;

    public Receipt(UUID receiptIssuer, List<Product> receiptProducts, BigDecimal receiptTotal) {
        this.receiptId = ++receiptCounterId;
        this.receiptDateTime = LocalDateTime.now();

        this.receiptIssuer = receiptIssuer;
        this.receiptProducts = receiptProducts;
        this.receiptTotal = receiptTotal;
    }
}
