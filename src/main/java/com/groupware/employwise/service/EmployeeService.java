package com.groupware.employwise.service;

import com.groupware.employwise.model.User;
import com.groupware.employwise.repository.CouchDbRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EmployeeService {
    public static User addEmployee(User user) {
        user.setID(UUID.randomUUID().toString());
        CouchDbRepository.getInstance().saveUser(user);
        return user;
    }

    public static List<User> getAllEmployees() {
        return CouchDbRepository.getInstance().getAllUser();
    }

    public static void deleteEmployee(String id) {
        User user = CouchDbRepository.getInstance().getUser(id);
        CouchDbRepository.getInstance().deleteUser(user);
    }

    public static User updateEmployee(String id, User user) {
        User user1 = CouchDbRepository.getInstance().getUser(id);
        if(user1 == null) {
            return null;
        }
        if (!Objects.equals(user.getEmail(), "")) user1.setName(user.getName());
        if (!Objects.equals(user.getPhoneNumber(), "")) user1.setPhoneNumber(user.getPhoneNumber());
        if (!Objects.equals(user.getEmail(), "")) user1.setEmail(user.getEmail());
        if (!Objects.equals(user.getReportsTo(), "")) user1.setReportsTo(user.getReportsTo());
        if (!Objects.equals(user.getProfileImage(), "")) user1.setProfileImage(user.getProfileImage());
        CouchDbRepository.getInstance().updateUser(user1);
        return user1;
    }

}
