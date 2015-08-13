package com.weblibrary.Servlet.ClienServlets;

import com.weblibrary.dao.*;
import com.weblibrary.dao.BookDAOHibernateImpl;
import com.weblibrary.service.BookFull;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/find")
public class ServletFinder extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String year = request.getParameter("year");
        String genre = request.getParameter("genre");
        String type = request.getParameter("type");

        BookDAO bookDao=new BookDAOHibernateImpl();

        if (title.equals("")) title = null;
        if (author.equals("")) author = null;
        if (year.equals("")) year = null;
        if (genre.equals("")) genre = null;

        try{
            System.out.println(title+author+year+genre);

            BookFull bookFull= bookDao.findAll(title,author,year,genre);

            System.out.println(bookFull.toString());

            session.setAttribute("bookFull", bookFull);
            session.setAttribute("type", type);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/select");
            requestDispatcher.forward(request, response);

        } catch (HibernateException e){
            e.printStackTrace();
            String error = "Error finding book!";
            request.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
