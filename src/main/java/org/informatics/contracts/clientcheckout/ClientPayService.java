package org.informatics.contracts.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.enums.TransactionStatus;
import org.informatics.exceptions.InsufficientBalanceException;
import org.informatics.models.RetailStore;
import org.informatics.contracts.clientcheckout.contracts.ClientPay;
import org.informatics.contracts.products.ProductsTotalCalcImpl;
import org.informatics.utils.ErrorMessagesManager;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ClientPayService implements ClientPay {
    private final RetailStore store;
    private final ProductsTotalCalcImpl productsTotalCalcService;

    @Override
    public TransactionStatus clientTransaction(BigDecimal clientBalance, int clientAtCheckoutMachine) {
        try {
            if (clientBalance.compareTo(BigDecimal.ZERO) <= 0)
                throw new InsufficientBalanceException(ErrorMessagesManager.getErrorMessage("error.insufficient_balance", clientAtCheckoutMachine));
            else if (productsTotalCalcService.calculateTotalWithDiscount(clientAtCheckoutMachine).compareTo(clientBalance) > 0)
                throw new InsufficientBalanceException(ErrorMessagesManager.getErrorMessage("error.insufficient_balance_for_transaction", clientAtCheckoutMachine));
        } catch(InsufficientBalanceException e) {
            return TransactionStatus.PURCHASE_DENIED;
        }


        return TransactionStatus.PURCHASE_GRANTED;
    }
}
