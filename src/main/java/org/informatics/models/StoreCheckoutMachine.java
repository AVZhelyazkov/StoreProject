package org.informatics.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
public class StoreCheckoutMachine {
    private StaffPersonnel occupant;
    private Map<Product, BigDecimal> scannedProducts;
    private Set<Receipt> cachedReceipts;

    public StoreCheckoutMachine() {
        this.occupant = null;
        this.scannedProducts = null;
        this.cachedReceipts = new HashSet<>();
    }
}
