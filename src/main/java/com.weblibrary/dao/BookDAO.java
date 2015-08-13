package com.weblibrary.dao;

import com.weblibrary.service.BookFull;
import com.weblibrary.entity.Book;

public interface BookDAO {

    void addBook(String title,String author,String year, String type1, String type2);
    void delete(long isbn);
    Book findBook(long isbn);
    BookFull findAll(String title,String author,String year,String genre);
    Book update(long isbn,String author,String title,String year);
}