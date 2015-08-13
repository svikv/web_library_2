package com.weblibrary.Listener;

import com.weblibrary.service.DBManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext sc = servletContextEvent.getServletContext();
        String user = sc.getInitParameter("DBUSER");
        String password = sc.getInitParameter("DBPASSWORD");
        String url = sc.getInitParameter("DBURL");

        System.out.println("contextInitialized...");

        DBManager dbm = new DBManager(user, password, url);
        sc.setAttribute("DBManager", dbm);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent){
        System.out.println("contextDestroyed...");
        ServletContext sc = servletContextEvent.getServletContext();
        DBManager dbm = (DBManager) sc.getAttribute("DBManager");
        dbm.closeConnection();
    }
}
