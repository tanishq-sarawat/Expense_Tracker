package com.tanishq_sarawat.Expense_Tracker_API.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DateDto {
    private LocalDate startDate;
    private LocalDate endDate;
}
