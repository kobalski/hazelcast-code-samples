package com.hazelcast.HazelcastSessionReplication;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HazelcastSessionReplication extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("action").equals("get")) {
            String key = request.getParameter("key");
            ServletOutputStream output = response.getOutputStream();
            String resp = session.getAttribute(key) + session.toString();
            output.write(resp.getBytes(resp));
        }


        if (request.getParameter("action").equals("set")) {
            String key = request.getParameter("key");
            String value = request.getParameter("value");
            ServletOutputStream output = response.getOutputStream();
            String resp = "Key " + key + " Value " + value +" is set.";
            session.setAttribute(key, value);
            output.write(resp.getBytes());
        }

        if (request.getParameter("action").equals("remove")) {
            String key = request.getParameter("key");
            ServletOutputStream output = response.getOutputStream();
            String resp = "Key " + key +" is removed.";
            session.removeAttribute(key);
            output.write(resp.getBytes());
        }
    }
}
