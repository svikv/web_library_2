package com.weblibrary.Servlet.AdminServlets;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.model.Book;
import com.weblibrary.model.Genre;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adder")
public class ServletAdder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        StringBuffer json = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null){
                json.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String string=json.toString();
        System.out.println("INPUT DATA: " + string);

        Gson gson = new Gson();
        JsonObject input = gson.fromJson(string, JsonElement.class).getAsJsonObject();
        String titleOfArticle = input.get("titleOfArticle").getAsString();
        String titleOfJournal = input.get("titleOfJournal").getAsString();
        String author = input.get("author").getAsString();
        String year = input.get("year").getAsString();
        String reference = input.get("reference").getAsString();
        String genre1 = input.get("genre1").getAsString();
        String genre2 = input.get("genre2").getAsString();
        String genre3 = input.get("genre3").getAsString();
        String genre4 = input.get("genre4").getAsString();
        String genre5 = input.get("genre5").getAsString();


        System.out.println("Our Book:"+titleOfArticle + ", " + titleOfJournal + ", " + author + ", " + year +
                ", " + genre1+ ", " + genre2+ ", " + genre3 + ", " + genre4 + ", " + genre5);
        List<Genre> list = new ArrayList<>();
        list.add(new Genre(genre1));
        list.add(new Genre(genre2));
        list.add(new Genre(genre3));
        list.add(new Genre(genre4));
        list.add(new Genre(genre5));

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BookDAO bookDAO = context.getBean(BookDAO.class);
        Book book = new Book(titleOfArticle,titleOfJournal, author, year, reference, list);

        String str;
        if (bookDAO.findBook(book)) {
            bookDAO.addBook(book);
            str = "Book has added";
        }
        else {
            str = "Error adding book! This book already exists";
        }

        try {
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(str));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}