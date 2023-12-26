package com.groupware.employwise.controller;

import com.groupware.employwise.model.User;
import com.groupware.employwise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public User createEmployee(@RequestBody User user) {
        return employeeService.addEmployee(user);
    }

    @GetMapping("get-all-employees")
    public List<User> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "none") String sortBy
    ) {
        return employeeService.getAllEmployees(page, size, sortBy);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    public User updateEmployee(@PathVariable String id, @RequestBody User user) {
        return employeeService.updateEmployee(id, user);
    }

    @GetMapping("/get-nth-manager/{n}/{id}")
    public User getNthLevelManager(@PathVariable int n, @PathVariable String id) {
        return employeeService.getNthLevelManager(n, id);
    }
}
