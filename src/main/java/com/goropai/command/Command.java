package com.goropai.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    /**
     * Method returns jsp pages address, which will be forwarded by servlet
     * @param request gets http request from servlet
     * @param response gets http response from servlet
     * @return string which is an address of jsp page
     * @throws ServletException notifies us of what problem is in the servlet
     * @throws IOException notifies us of what problem is in the input/output stream
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
