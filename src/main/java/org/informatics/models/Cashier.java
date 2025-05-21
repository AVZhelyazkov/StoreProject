package org.informatics.models;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

public class Cashier extends StaffPersonnel{
    public Cashier(String staffName, BigDecimal staffSalary) {
        super(staffName, staffSalary);
    }
}
