package com.tanishq_sarawat.Expense_Tracker_API.Service;

import com.tanishq_sarawat.Expense_Tracker_API.Entity.Expenses;
import com.tanishq_sarawat.Expense_Tracker_API.Repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    public void save(Expenses expenses) {
        expensesRepository.save(expenses);
    }
    public void delete(String Id) {
        expensesRepository.deleteById(Id);
    }
    public List<Expenses> findByUserId(String userId) {
        return expensesRepository.findByUserId(userId);
    }
    public Expenses findById(String id) {
        if(expensesRepository.findById(id).isPresent()) {
            return expensesRepository.findById(id).get();
        }
        return null;
    }
    public List<Expenses> findexpensestilldate(LocalDate start, LocalDate end, String userId) {
        return expensesRepository.findByDateBetweenAndUserId(start,end,userId);
    }


}