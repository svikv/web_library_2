package com.weblibrary.service;
import com.weblibrary.entity.Book;

import java.util.Collections;
import java.util.List;

public class BookFull {
    private List<Book> books;
    private String genre;

    @Override
    public String toString() {
        return "BookFull{" +
                "books=" + books +
                ", genre='" + genre + '\'' +
                '}';
    }

    public BookFull(List<Book> books,String genre){
        this.books=books;
        this.genre=genre;
    }

    public String getGenre(){
        return genre;
    }

    public Book getRandom(){
        Collections.shuffle(books);
        Book book = books.get(0);
        books.remove(0);
        return book;
    }

    public Book getNext(){
        Book book = books.get(0);
        books.remove(0);
        return book;
    }

    public List<Book> getAll(){
        return books;
    }
}