package com.tanishq_sarawat.Expense_Tracker_API.Controller;

import com.tanishq_sarawat.Expense_Tracker_API.Entity.Expenses;
import com.tanishq_sarawat.Expense_Tracker_API.Entity.User;
import com.tanishq_sarawat.Expense_Tracker_API.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<User> findAll() {
        return userServices.findAll();
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=  authentication.getName();
        userServices.DeleteByUsername(username);
        return new ResponseEntity(HttpStatus.NO_CONTENT );
    }
}
