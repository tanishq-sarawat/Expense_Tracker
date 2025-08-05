package com.tanishq_sarawat.Expense_Tracker_API.dto;

import com.tanishq_sarawat.Expense_Tracker_API.Constants.Category;
import lombok.Data;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.Locale;

@Data
public class ExpenseRequestDto {
    private String name_of_item;
    private Category category;
    @NonNull
    private BigInteger amount;
}
