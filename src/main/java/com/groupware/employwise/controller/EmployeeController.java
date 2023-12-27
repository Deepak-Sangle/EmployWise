package com.groupware.employwise.controller;

import com.groupware.employwise.model.Response;
import com.groupware.employwise.model.User;
import com.groupware.employwise.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestMapping("/employee")
@RestController
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Response> createEmployee(@Valid @RequestBody User user) {
        if(Objects.equals(user.getName(), "") || Objects.equals(user.getEmail(), "") || Objects.equals(user.getPhoneNumber(), "") || Objects.equals(user.getReportsTo(), ""))
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please fill all the fields", null));
        if(!User.validateEmail(user.getEmail()))
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid email", null));
        if(!User.validatePhoneNumber(user.getPhoneNumber()))
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid phone number", null));
        if(!User.validateID(user.getReportsTo()))
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid Manager ID", null));
        try{
            user.setID(UUID.randomUUID().toString());
            employeeService.addEmployee(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response("Employee created successfully", user));
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("get-all-employees")
    public ResponseEntity<Response> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
            @RequestParam(defaultValue = "none") String sortBy
    ) {
        try {
            List<User> users = employeeService.getAllEmployees(page, size, sortBy);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response("Employees fetched successfully", users));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable String id) {
        try{
            String message = employeeService.deleteEmployee(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response(message, null));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get-nth-manager/{n}/{id}")
    public ResponseEntity<Response> getNthLevelManager(@PathVariable int n, @PathVariable String id) {
        try{
            User manager = employeeService.getNthLevelManager(n, id);
            String message = manager == null ? n + "th Level Manager does not exists for given User" : "Manager fetched successfully";
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response(message, manager));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateEmployee(@PathVariable String id, @RequestBody User user) {
        if(!Objects.equals(user.getEmail(), "") && !User.validateEmail(user.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid email", null));
        }
        if(!Objects.equals(user.getPhoneNumber(), "") && !User.validatePhoneNumber(user.getPhoneNumber())){
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid phone number", null));
        }
        if(!Objects.equals(user.getReportsTo(), "") && !User.validateID(user.getReportsTo()))
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid Manager ID", null));
        try{
            User user1 = employeeService.updateEmployee(id, user);
            String message = user1 == null ? "User with given ID does not exist" : "Employee updated successfully";
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response(message, user1));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
}
