package com.goropai.command.impl;

import com.goropai.command.Command;
import com.goropai.command.CommandHelper;
import com.goropai.entity.User;
import com.goropai.manager.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MissingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            CommandHelper commandHelper = CommandHelper.getInstance();
            page = commandHelper.checkUser(request.getSession(), user);
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
