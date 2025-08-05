package com.tanishq_sarawat.Expense_Tracker_API.Entity;

import com.tanishq_sarawat.Expense_Tracker_API.Constants.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.web.bind.annotation.Mapping;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expenses{
    @Id
    @UuidGenerator
    private String id;
    private String userId;
    private String name_of_item;
    @Enumerated(EnumType.STRING)
    private Category category;
    @NonNull
    private BigInteger amount;
    private LocalDate date =  LocalDate.now();
}
