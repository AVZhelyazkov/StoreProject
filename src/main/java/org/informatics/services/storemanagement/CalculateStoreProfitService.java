package org.informatics.services.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.interfaces.CalculateStoreProfitInterface;

@RequiredArgsConstructor
public class CalculateStoreProfitService implements CalculateStoreProfitInterface {
    private RetailStore store;
    @Override
    public void getTotalProfit() {

    }
}
