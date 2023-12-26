package com.groupware.employwise.controller;

import com.groupware.employwise.model.User;
import com.groupware.employwise.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @PostMapping("/create")
    public User createEmployee(@RequestBody User user) {
        return EmployeeService.addEmployee(user);
    }

    @GetMapping("get")
    public List<User> getAllEmployees() {
        return EmployeeService.getAllEmployees();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable String id) {
        EmployeeService.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    public User updateEmployee(@PathVariable String id, @RequestBody User user) {
        return EmployeeService.updateEmployee(id, user);
    }
}
