package com.tanishq_sarawat.Expense_Tracker_API.Repository;

import com.tanishq_sarawat.Expense_Tracker_API.Entity.Expenses;
import com.tanishq_sarawat.Expense_Tracker_API.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
