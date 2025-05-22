package org.informatics.services.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.enums.TransactionStatus;
import org.informatics.exceptions.InsufficientBalanceException;
import org.informatics.models.RetailStore;
import org.informatics.services.clientcheckout.interfaces.ClientPayInterface;
import org.informatics.utils.ErrorMessagesManager;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ClientPayService implements ClientPayInterface {
    final private RetailStore store;

    @Override
    public TransactionStatus clientTransaction(BigDecimal clientBalance, int clientAtCheckoutMachine) {
        try {
            if (clientBalance.compareTo(BigDecimal.ZERO) <= 0)
                throw new InsufficientBalanceException(ErrorMessagesManager.getErrorMessage("error.insufficient_balance", clientAtCheckoutMachine));
        } catch(InsufficientBalanceException e) {
            return TransactionStatus.PURCHASE_DENIED;
        }


        return TransactionStatus.PURCHASE_GRANTED;
    }
}
