package com.groupware.employwise.service;

import com.groupware.employwise.model.User;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {
    CompletableFuture<User> addEmployee(User user) throws MessagingException;
    List<User> getAllEmployees(int page, int size, String sortBy);
    void deleteEmployee(String id);
    User updateEmployee(String id, User user);
    User getNthLevelManager(int n, String id);
    void sendEmailToManager(User user) throws MessagingException;

}
