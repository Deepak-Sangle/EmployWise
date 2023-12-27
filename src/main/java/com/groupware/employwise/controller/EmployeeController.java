package com.groupware.employwise.controller;

import com.groupware.employwise.model.Response;
import com.groupware.employwise.model.User;
import com.groupware.employwise.service.EmployeeService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Response> createEmployee(@RequestBody User user) {
        try{
            user.setID(UUID.randomUUID().toString());
            System.out.println(user.getID());
            employeeService.addEmployee(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response("Employee created successfully", user));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("get-all-employees")
    public List<User> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
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
