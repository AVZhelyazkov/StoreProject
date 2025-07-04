package org.informatics.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
public abstract class StaffPersonnel implements Serializable {
    final private UUID staffId;
    final private String staffName;

    @Setter
    private BigDecimal staffSalary;


    public StaffPersonnel(String staffName, BigDecimal staffSalary) {
        this.staffId = UUID.randomUUID();
        this.staffName = staffName;
        this.staffSalary = staffSalary;
    }
}
