package com.weblibrary;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.model.Book;
import com.weblibrary.model.Genre;
import com.weblibrary.service.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class BookController {

    private BookManager bookManager;

    //@Autowired and @Qualifier annotations are used for injecting BookManager
    @Autowired(required = true)
    @Qualifier(value = "bookManager")
    public void setBookManager(BookManager bookManager){
        this.bookManager = bookManager;
    }

    //Method parameter book is marked with @RequestBody annotation. Thanks to this annotation,
    //Spring will try to bind the request body [which can be JSON/XML/Other] to book object[ Means crating a
    //new book object with the details found in the request body like book title,year etc..], based on Content-Type
    //header in Http request
    //this method response to POST request /findAll
    // receives json data sent by client --> map it to Book object return Book object as json

    @RequestMapping(value = "/findAll", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public @ResponseBody Book findAll(@RequestBody Book book) {
        System.out.println("11111111111");

        HashSet<Book> books = bookManager.findAll(book);
        System.out.println(book.getAuthor() + ", Employees count: " + book.getTitleOfJournal());
        return book;
    }

    /*
    @RequestMapping(value = urlPattern , method = RequestMethod.POST)
public @ResponseBody Person save(@RequestBody Person jsonString) {

   Person person=personService.savedata(jsonString);
   return person;
}

    //For add and update person both
    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public String addBook (@ModelAttribute("book") Book book){

        if(book.getId() == 0){
            //new person, add it
            this.bookManager.addBook(book);
        }
        return "redirect:/persons";
    }

    @RequestMapping("/delete/{id}")
    public boolean deleteBook(@PathVariable("id") int id){

        this.bookManager.deleteBook(id);
        return "redirect:/persons";
    }
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }*/
}