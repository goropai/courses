package com.goropai.command.impl;

import com.goropai.command.Command;
import com.goropai.command.CommandHelper;
import com.goropai.entity.User;
import com.goropai.manager.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String language = request.getParameter("languageKey");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            CommandHelper commandHelper = CommandHelper.getInstance();
            request.getSession().setAttribute("language", language);
            page = commandHelper.checkUser(request.getSession(), user);
        } else {
            request.getSession().setAttribute("language", language);
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
