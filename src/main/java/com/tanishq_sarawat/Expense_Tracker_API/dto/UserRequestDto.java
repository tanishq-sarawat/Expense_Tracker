package com.tanishq_sarawat.Expense_Tracker_API.dto;

import lombok.Data;

@Data

public class UserRequestDto {

    private String username;
    private String email;
    private String password;
}
