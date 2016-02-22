package com.weblibrary.dao;

import com.weblibrary.model.Book;
import com.weblibrary.model.Genre;

import java.util.HashSet;
import java.util.List;

public interface BookDAO {

    int addBook(Book book);
    HashSet<Book> findAll(Book book);
    public List<Book> listBooks();
    boolean deleteBook (int id);
    boolean deleteGenre (int id);
    public void updateBook(Book b);
    boolean findBook (Book book);
    int addGenre(String genre);
    boolean findGenre (Genre genre);
    List<Genre> findAllGenres();
    Book getBookById(int id);
    //Book update(long id, String author, String titleOfArticle, String titleOfJournal, String year, String reference, String genre1, String genre2, String genre3, String genre4, String genre5);
    Genre getGenre(String type);
}