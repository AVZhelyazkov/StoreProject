package org.informatics.contracts.clientcheckout.contracts;

import org.informatics.enums.TransactionStatus;

import java.math.BigDecimal;

public interface ClientPay {
    TransactionStatus clientTransaction(BigDecimal clientBalance, int clientAtCheckoutMachine);
}
