package com.tanishq_sarawat.Expense_Tracker_API.dto;

import com.tanishq_sarawat.Expense_Tracker_API.Constants.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class SummaryDto {
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigInteger total;
}
