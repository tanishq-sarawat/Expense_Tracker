package com.tanishq_sarawat.Expense_Tracker_API.Service;

import com.tanishq_sarawat.Expense_Tracker_API.Entity.Expenses;
import com.tanishq_sarawat.Expense_Tracker_API.Entity.User;
import com.tanishq_sarawat.Expense_Tracker_API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public void addUser(User user) {
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }
    public User findByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        if(user != null){
            return user;
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }

    }
    public void DeleteByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }

}
