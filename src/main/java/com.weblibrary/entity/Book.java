package com.weblibrary.entity;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="book")
public class Book {
    long isbn;
    String title;
    String author;
    String year;
    HashSet<Genre> genres=new HashSet<>();

    public Book(String title, String author, String year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "book_genre",joinColumns = {@JoinColumn(name = "book_isbn")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    public HashSet<Genre> getGenres() {return genres; }
    public void setGenres(HashSet<Genre> genres) {this.genres=genres; }

    @Id
    @GeneratedValue
    public long getIsbn(){return isbn;}
    public void setIsbn(long isbn){this.isbn=isbn;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title; }

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author=author; }

    public String getYear(){return year; }
    public void setYear(String year){this.year=year;}

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                ", genres=" + genres +
                '}';
    }
}

