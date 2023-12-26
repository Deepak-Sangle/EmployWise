package com.groupware.employwise.service;

import com.groupware.employwise.model.User;

import java.util.List;

public interface EmployeeService {
    User addEmployee(User user);
    List<User> getAllEmployees(int page, int size, String sortBy);
    void deleteEmployee(String id);
    User updateEmployee(String id, User user);
    User getNthLevelManager(int n, String id);

}
