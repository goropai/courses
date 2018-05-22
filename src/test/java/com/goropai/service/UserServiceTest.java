package com.goropai.service;

import com.goropai.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {

    @Test
    public void testFindUser() {
        UserService userService = UserService.getInstance();
        User user = userService.findLecturer("test", "1234");
        Assert.assertNotNull(user);
    }

    @Test
    public void testNotFindUser() {
        UserService userService = UserService.getInstance();
        User user = userService.findLecturer("", "");
        Assert.assertNull(user);
    }

    @Test
    public void testWrongInputLogin() {
        UserService userService = UserService.getInstance();
        String login = "1234";
        String password = "1234";
        String name = "Test";
        String surname = "Test";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertFalse(result);
    }

    @Test
    public void testWrongInputPassword() {
        UserService userService = UserService.getInstance();
        String login = "test";
        String password = "0";
        String name = "Test";
        String surname = "Test";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertFalse(result);
    }

    @Test
    public void testWrongInputName() {
        UserService userService = UserService.getInstance();
        String login = "test";
        String password = "1234";
        String name = "test";
        String surname = "Test";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertFalse(result);
    }

    @Test
    public void testWrongInputSurname() {
        UserService userService = UserService.getInstance();
        String login = "test";
        String password = "1234";
        String name = "Test";
        String surname = "test";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertFalse(result);
    }

}
