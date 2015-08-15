package com.weblibrary.Servlet.AdminServlets;
/*
import com.weblibrary.entity.Book;
import com.weblibrary.ervice.BookManag;
import com.weblibrary.Service.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/findforupdate")
public class ServletUpdater extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        Book book;
        DBManager dbManager = (DBManager) getServletContext().getAttribute("DBManager");
        BookManager bookManager = new BookManager(dbManager);
        try{
            book = bookManager.findBook(isbn);
            request.setAttribute("book", book);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/update.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
            String error = "Error updating book!";
            request.setAttribute("error", error);
            request.setAttribute("forwardTo", "admin/admin.html");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
*/