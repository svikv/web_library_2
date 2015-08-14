package com.weblibrary.service;
import com.weblibrary.entity.Book;

import java.util.Collections;
import java.util.List;

public class BookFull {
    private List<Book> books;

    @Override
    public String toString() {
        return "BookFull{" +
                "books=" + books +
                '}';
    }

    public BookFull(List<Book> books){
        this.books=books;
    }


    public Book getRandom(){
        Collections.shuffle(books);
        Book book = books.get(0);
        books.remove(0);
        return book;
    }

    public List<Book> getAll(){
        return books;
    }
}