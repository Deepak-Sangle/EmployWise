package com.groupware.employwise.service;

import com.groupware.employwise.model.User;
import com.groupware.employwise.repository.CouchDb;

public class EmployeeService {
    public static User addEmployee(User user) {
        CouchDb.getInstance().saveUser(user);
        return user;
    }

}
