package com.tanishq_sarawat.Expense_Tracker_API.Repository;

import com.tanishq_sarawat.Expense_Tracker_API.Entity.Expenses;
import com.tanishq_sarawat.Expense_Tracker_API.dto.SummaryDto;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, String>{


    @Query("SELECT k FROM Expenses k WHERE k.date >= startDate AND k.date <= endDate AND k.userId = userId")
    List<Expenses> findByDateBetweenAndUserId(@Param("startDate") LocalDate startDate,@Param("endDate")LocalDate endDate,@Param("userId") String userId);

    @Query("SELECT k FROM Expenses k WHERE k.user_id = userId")
    List<Expenses> findByUserId(@Param("user_id") String user_id);
}
