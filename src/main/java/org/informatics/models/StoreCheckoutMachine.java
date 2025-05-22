package org.informatics.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class StoreCheckoutMachine {
    private StaffPersonnel occupant;
    private Set<Product> scannedProducts;
    private List<Receipt> cachedReceipts;

    StoreCheckoutMachine() {
        this.occupant = null;
        this.scannedProducts = null;
        this.cachedReceipts = new ArrayList<>();
    }
}
