package org.informatics.services.clientcheckout.interfaces;

import org.informatics.enums.TransactionStatus;

import java.math.BigDecimal;

public interface ClientPayInterface {
    TransactionStatus clientTransaction(BigDecimal clientBalance, int clientAtCheckoutMachine);
}
