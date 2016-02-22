package com.weblibrary;

import com.weblibrary.model.Book;
import com.weblibrary.service.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

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

        /*@RequestMapping(value = "/findAll", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public @ResponseBody
    Book findAll(@RequestBody Book book) {
        System.out.println(book.getAuthor() + book.getTitleOfJournal());
        //HashSet<Book> books = bookManager.findAll(book);
        return book;
    }*/

    //ќбрабатывает GET-запросы к URL /books
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {

        // «аполнение модели
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookManager.listBooks());
        // ласс контроллера определ€ет реализацию представлени€ /WEB-INF/views/book.jsp,которое отобразит данные
        //метод listBooks() возвращает логическое им€ представлени€
        return "book";
    }

    //For add and update person both
    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("book") Book b){

        if(b.getId() == 0){
            //new person, add it
            bookManager.addBook(b);
        }else{
            //existing person, call update
            bookManager.updateBook(b);
        }
        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id){

        bookManager.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){

        model.addAttribute("book", bookManager.getBookById(id));
        model.addAttribute("listPersons", bookManager.listBooks());
        return "book";
    }
}