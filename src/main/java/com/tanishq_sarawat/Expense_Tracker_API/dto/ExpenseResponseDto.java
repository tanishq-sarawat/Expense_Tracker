package com.tanishq_sarawat.Expense_Tracker_API.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ExpenseResponseDto {
    String id;
    String name_of_item;
    String category;
    BigInteger amount;
}
