package com.weblibrary.Servlet.ClientServlets;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet("/findAll")
public class ServletAllFinder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        String genre1 = input.get("genre1").getAsString();
        String genre2 = input.get("genre2").getAsString();
        String genre3 = input.get("genre3").getAsString();
        String genre4 = input.get("genre4").getAsString();
        String genre5 = input.get("genre5").getAsString();

        System.out.println(titleOfArticle + ", " + titleOfJournal + ", " + author + ", "
                + year + ", " + genre1 + ", " + genre2 + ", " + genre3 + ", " + genre4 + ", " + genre5);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BookDAO bookDAO = context.getBean(BookDAO.class);

        List<String> list = new ArrayList<>();
        if (((!"".equals(genre1)) && (!genre1.equals(null)))) list.add(genre1);
        if (((!"".equals(genre2)) && (!genre1.equals(null)))) list.add(genre2);
        if (((!"".equals(genre3)) && (!genre1.equals(null)))) list.add(genre3);
        if (((!"".equals(genre4)) && (!genre1.equals(null)))) list.add(genre4);
        if (((!"".equals(genre5)) && (!genre1.equals(null)))) list.add(genre5);
        HashSet<Book> books = bookDAO.findAll(titleOfArticle, titleOfJournal, author, year, list);

        try {
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(books));
            System.out.println("Before sending JSON to JavaScript");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
