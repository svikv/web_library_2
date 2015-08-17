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
        Book[] asArray = (Book[]) books.toArray();
        Book book = asArray[random.nextInt(asArray.length)];
        books.remove(book);
        return book;
    }

    public HashSet<Book> getAll(){
        return books;
    }
}