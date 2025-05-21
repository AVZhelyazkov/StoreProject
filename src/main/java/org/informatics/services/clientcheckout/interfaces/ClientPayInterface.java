package org.informatics.services.clientcheckout.interfaces;

import org.informatics.models.RetailStore;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ClientPayInterface {
    boolean clientTransaction(BigDecimal clientBalance, RetailStore clientOnCheckout, int clientAtCheckoutMachine, List<UUID> scannedProducts);
}
