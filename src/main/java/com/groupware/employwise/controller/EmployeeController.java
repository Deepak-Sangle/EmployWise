package com.groupware.employwise.controller;

import com.groupware.employwise.model.Response;
import com.groupware.employwise.model.User;
import com.groupware.employwise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
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
