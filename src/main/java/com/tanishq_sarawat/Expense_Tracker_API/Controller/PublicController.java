package com.tanishq_sarawat.Expense_Tracker_API.Controller;

import com.tanishq_sarawat.Expense_Tracker_API.Entity.User;
import com.tanishq_sarawat.Expense_Tracker_API.Repository.UserRepository;
import com.tanishq_sarawat.Expense_Tracker_API.Service.UserServices;
import com.tanishq_sarawat.Expense_Tracker_API.Util.jwtUtil;
import com.tanishq_sarawat.Expense_Tracker_API.dto.LoginDto;
import com.tanishq_sarawat.Expense_Tracker_API.dto.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private jwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserServices userServices;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequestDto user) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        User check =userRepository.findByUsername(user.getUsername());
        if(check != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        else{
            userServices.addUser(newUser);
        }
        Map<String, String> response = new HashMap<>();
        response.put("id", newUser.getId_user());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String access_token = jwtUtil.generateToken(userDetails.getUsername());
            String refresh_token = jwtUtil.generateToken(userDetails.getUsername() + "refresh");



            Map<String, String> response = new HashMap<>();
            response.put("access_token", access_token);
            response.put("refresh_token", refresh_token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
