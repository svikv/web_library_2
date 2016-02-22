package com.weblibrary.service;

import com.weblibrary.dao.BookDAO;
import com.weblibrary.model.Book;
import com.weblibrary.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

//The service implementation is annotated with @Service which means that this will be a bean managed by Spring.
// BookDAO is annotated with @Autowired so it will be injected by the Spring container.
//Methods are annotated with @Transactional so the Spring Hibernate transaction manager creates the
// required transactions and the respective sessions.

@Service
public class BookManager {
    //Notice that spring declarative transaction management is applied by using @Transactional annotation
    @Autowired
    private BookDAO bookDAO;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Transactional
    public long addBook(Book book) {
        return bookDAO.addBook(book);
    }

    @Transactional
    public HashSet<Book> findAll(Book book) {
        return bookDAO.findAll(book);
    }

    @Transactional
    public List<Book> listBooks() {
        return bookDAO.listBooks();
    }

    @Transactional
    public boolean deleteBook (int id) {
        return bookDAO.deleteBook(id);
    }

    @Transactional
    public void updateBook (Book b) {
        bookDAO.updateBook(b);
    }

    @Transactional
    public boolean deleteGenre (int id){
        return bookDAO.deleteGenre(id);
    }

    @Transactional
    public boolean findBook (Book book){
        return bookDAO.findBook(book);
    }

    @Transactional
    public boolean findGenre (Genre genre) {
        return bookDAO.findGenre(genre);
    }

    @Transactional
    public long addGenre(String genre){
        return bookDAO.addGenre(genre);
    }

    @Transactional
    public List<Genre> findAllGenres(){
        return bookDAO.findAllGenres();
    }

    @Transactional
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Transactional
    public Genre getGenre(String type) {
        return bookDAO.getGenre(type);
    }
}
