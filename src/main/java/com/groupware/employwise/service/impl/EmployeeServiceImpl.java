package com.groupware.employwise.service.impl;

import com.groupware.employwise.model.User;
import com.groupware.employwise.repository.EmployeeRepository;
import com.groupware.employwise.service.EmployeeService;
import com.groupware.employwise.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private MailService mailService;

    @Override
    @Async
    public CompletableFuture<User> addEmployee(User user) throws MessagingException {
//        sendEmailToManager(user);
        EmployeeRepository.getInstance().saveUser(user);
        return CompletableFuture.completedFuture(user);
    }

    @Override
    public List<User> getAllEmployees(int page, int size, String sortBy) {
        return EmployeeRepository.getInstance().getAllUser(page, size, sortBy);
    }

    @Override
    public void deleteEmployee(String id) {
        User user = EmployeeRepository.getInstance().getUser(id);
        EmployeeRepository.getInstance().deleteUser(user);
    }

    @Override
    public User updateEmployee(String id, User user) {
        User user1 = EmployeeRepository.getInstance().getUser(id);
        if(user1 == null) {
            return null;
        }
        if (!Objects.equals(user.getName(), "")) user1.setName(user.getName());
        if (!Objects.equals(user.getPhoneNumber(), "")) user1.setPhoneNumber(user.getPhoneNumber());
        if (!Objects.equals(user.getEmail(), "")) user1.setEmail(user.getEmail());
        if (!Objects.equals(user.getReportsTo(), "")) user1.setReportsTo(user.getReportsTo());
        if (!Objects.equals(user.getProfileImage(), "")) user1.setProfileImage(user.getProfileImage());
        EmployeeRepository.getInstance().updateUser(user1);
        return user1;
    }

    @Override
    public User getNthLevelManager(int n, String id) {
        if(n < 0) {
            return null;
        }
        User user = EmployeeRepository.getInstance().getUser(id);
        if(user == null) {
            return null;
        }
        if(n == 0) {
            return user;
        }
        return getNthLevelManager(n-1, user.getReportsTo());
    }

    @Override
    public void sendEmailToManager(User user)  {
        User manager = EmployeeRepository.getInstance().getUser(user.getReportsTo());
        if(manager == null) {
            return;
        }
        String body = """
                <p>A new employee has been added to your team.
                Meet <strong>%s</strong>. His phone number is %s and email is <a href="mailto:%s?subject=Welcome Aboard&body=Dear %s, Welcome to the team. I am your manager %s">%s</a>.
                </p>""";
        body = String.format(body, user.getName(), user.getPhoneNumber(), user.getEmail(), user.getName(), manager.getName(), user.getEmail());
        Context context = new Context();
        context.setVariable("message", body);
        mailService.sendEmail(manager.getEmail(), "New Employee joined your team",  body, "email-template", context);
    }

}
