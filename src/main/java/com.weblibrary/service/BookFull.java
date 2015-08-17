package com.weblibrary.service;
import com.weblibrary.entity.Book;

import java.util.*;

public class BookFull {
    private HashSet<Book> books;

    @Override
    public String toString() {
        return "BookFull{" +
                "books=" + books +
                '}';
    }

    public BookFull(HashSet<Book> books){
        this.books=books;
    }


    public Book getRandom(){
        Random random = new Random();
        int number = random.nextInt(books.size());
        Book book = books.get(number);
        books.remove(0);
        return book;
    }

    public HashSet<Book> getAll(){
        return books;
    }
}