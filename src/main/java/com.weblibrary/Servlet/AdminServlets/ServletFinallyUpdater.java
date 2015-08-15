package com.weblibrary.Servlet.AdminServlets;
/*
import com.weblibrary.Service.Book;
import com.weblibrary.Service.BookManager;
import com.weblibrary.Service.DBManager;

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
        String title = request.getParameter("title");
        String isbn = request.getParameter("isbn");
        String year = request.getParameter("year");

        DBManager dbManager = (DBManager) getServletContext().getAttribute("DBManager");
        BookManager bookManager = new BookManager(dbManager);
        Book book = bookManager.updateBook(title, author, isbn, year);

        request.setAttribute("book", book);
        request.setAttribute("msg", "Updated book!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view.jsp");
        requestDispatcher.forward(request, response);
    }
}
*/