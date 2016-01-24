package com.weblibrary.Servlet.AdminServlets;

import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;
import org.hibernate.HibernateException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 17.08.15.
 */

@WebServlet("/finder")
public class ServletFind extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String ISBN = request.getParameter("isbn");
        long isbn = Integer.parseInt(ISBN);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BookDAO bookDAO = context.getBean(BookDAO.class);

        try {
            Book book = bookDAO.findById(isbn);
            request.setAttribute("book", book);
            request.setAttribute("msg", "Book found!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/view.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
            String error = "Error finding book!";
            request.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}