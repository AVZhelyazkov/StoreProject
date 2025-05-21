package org.informatics.services.clientcheckout;

import org.informatics.models.RetailStore;
import org.informatics.services.clientcheckout.interfaces.ClientPayInterface;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ClientPayService implements ClientPayInterface {
    @Override
    public boolean clientTransaction(BigDecimal clientBalance, RetailStore clientOnCheckout, int clientAtCheckoutMachine, List<UUID> scannedProducts) {
        return false;
    }
}
