package org.informatics.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class StoreCheckoutMachine implements Serializable {
    private StaffPersonnel occupant;
    private Map<Product, BigDecimal> scannedProducts;
    private Set<Receipt> cachedReceipts;

    public StoreCheckoutMachine() {
        this.occupant = null;
        this.scannedProducts = null;
        this.cachedReceipts = new HashSet<>();
    }

    public void addReceipt(Receipt receipt) {
        cachedReceipts.add(receipt);
    }
}
