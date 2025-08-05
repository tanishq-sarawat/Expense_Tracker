package com.tanishq_sarawat.Expense_Tracker_API.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @UuidGenerator
    private String id_user;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
