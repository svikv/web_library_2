package com.weblibrary.Servlet.AdminServlets;

import com.google.gson.Gson;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.model.Genre;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findGenres")
public class ServletFinderGenre extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BookDAO bookDAO = context.getBean(BookDAO.class);

        List<Genre> list = bookDAO.findAllGenres();
        List<String> listString = new ArrayList<>();
        for(Genre genre: list) listString.add(genre.getGenre());

        try {
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(listString));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}