package com.tanishq_sarawat.Expense_Tracker_API.Controller;

import com.tanishq_sarawat.Expense_Tracker_API.Entity.Expenses;
import com.tanishq_sarawat.Expense_Tracker_API.Entity.User;
import com.tanishq_sarawat.Expense_Tracker_API.Repository.ExpensesRepository;
import com.tanishq_sarawat.Expense_Tracker_API.Service.ExpensesService;
import com.tanishq_sarawat.Expense_Tracker_API.Service.UserServices;
import com.tanishq_sarawat.Expense_Tracker_API.dto.DateDto;
import com.tanishq_sarawat.Expense_Tracker_API.dto.ExpenseRequestDto;
import com.tanishq_sarawat.Expense_Tracker_API.dto.ExpenseResponseDto;
import io.lettuce.core.dynamic.annotation.Param;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Expenses")
public class ExpenseController {
    @Autowired
    ExpensesService expensesService;
    @Autowired
    UserServices userServices;
    @Autowired
    private ExpensesRepository expensesRepository;

    @GetMapping("/home")
    public ResponseEntity<List<Expenses>> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = userServices.findByUsername(authentication.getName()).getId_user();
        List<Expenses> expenses = expensesService.findByUserId(userid);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expenses> save(@RequestBody ExpenseRequestDto expenseRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userServices.findByUsername(username);
        Expenses expenses = new Expenses();
        expenses.setUserId(user.getId_user());
        expenses.setName_of_item(expenseRequestDto.getName_of_item());
        expenses.setAmount(expenseRequestDto.getAmount());
        expenses.setCategory(expenseRequestDto.getCategory());
        expensesRepository.save(expenses);
        return new ResponseEntity<>(expenses, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Expenses> delete(@PathVariable String id)
    {
        expensesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> findById(@PathVariable String id) {
        Expenses expenses = expensesService.findById(id);
        if (expenses == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto();
        expenseResponseDto.setName_of_item(expenses.getName_of_item());
        expenseResponseDto.setAmount(expenses.getAmount());
        expenseResponseDto.setId(expenses.getId());
        return new  ResponseEntity<>(expenseResponseDto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Expenses> update(@PathVariable String id, @RequestBody ExpenseRequestDto expenses) {
        Expenses expensesToUpdate = expensesService.findById(id);
        if (expensesToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        expensesToUpdate.setName_of_item(expenses.getName_of_item());
        expensesToUpdate.setAmount(expenses.getAmount());
        expensesToUpdate.setDate(LocalDate.now());
        expensesService.save(expensesToUpdate);
        return new ResponseEntity<>(expensesToUpdate, HttpStatus.OK);
    }
    @GetMapping("/totalexpense")
    public ResponseEntity<?> totalExpensetilldate(@RequestParam("start") LocalDate start, @RequestParam("end") LocalDate end) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userServices.findByUsername(username);
        List<Expenses> list = new ArrayList<>(expensesService.findexpensestilldate(start, end, user.getId_user()));
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
