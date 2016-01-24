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
import java.io.IOException;

@WebServlet("/update")
public class ServletFinallyUpdater extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String author = request.getParameter("author");
        String titleOfArticle = request.getParameter("titleOfArticle");
        String titleOfJournal = request.getParameter("titleOfJournal");
        String ISBN = request.getParameter("isbn");
        String year = request.getParameter("year");
        String reference = request.getParameter("reference");
        String genre1 = request.getParameter("genre1");
        String genre2 = request.getParameter("genre2");
        String genre3 = request.getParameter("genre3");
        String genre4 = request.getParameter("genre4");
        String genre5 = request.getParameter("genre5");

        long id = Integer.parseInt(ISBN);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BookDAO bookDAO = context.getBean(BookDAO.class);
        Book book = bookDAO.update(id, author, titleOfArticle, titleOfJournal, year, reference, genre1, genre2, genre3, genre4, genre5);

        request.setAttribute("book", book);
        request.setAttribute("msg", "Updated book!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/view.jsp");
        requestDispatcher.forward(request, response);
    }
}
