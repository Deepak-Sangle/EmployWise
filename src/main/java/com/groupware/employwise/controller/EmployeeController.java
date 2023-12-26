package com.groupware.employwise.controller;

import com.groupware.employwise.model.User;
import com.groupware.employwise.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @PostMapping("/create")
    public User createEmployee(@RequestBody User user) {
        user.setEmployeeID(UUID.randomUUID().toString());
        return EmployeeService.addEmployee(user);
    }
}
