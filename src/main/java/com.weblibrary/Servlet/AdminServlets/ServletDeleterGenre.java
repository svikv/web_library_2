package com.weblibrary.Servlet.AdminServlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/deleterGenre")
public class ServletDeleterGenre extends HttpServlet {
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
        String string = json.toString();

        Gson gson = new Gson();
        JsonObject input = gson.fromJson(string, JsonElement.class).getAsJsonObject();
        String  genre = input.get("genre").getAsString();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BookDAO bookDAO = context.getBean(BookDAO.class);
        System.out.println(genre);

        String str;
        if (bookDAO.deleteGenre(genre)) {
            str = "Keyword " + genre + " has deleted";
        }
        else {
            str = "Error deleting keyword! Keyword " + genre +" doesn`t exist!";
        }

        try {
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(str));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}