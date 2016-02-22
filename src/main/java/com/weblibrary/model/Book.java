package com.weblibrary.model;
import javax.persistence.*;
import java.util.*;

//Entity bean with JPA annotations. Hibernate provides JPA implementation
@Entity
@Table(name = "book")
public class Book {
    int id;
    private String titleOfArticle;
    private String titleOfJournal;
    private String author;
    private String year;
    private String reference;
    private List<Genre> genres = new ArrayList<Genre>();

    public Book(String titleOfArticle, String titleOfJournal, String author, String year, String reference, List<Genre> genres){
        this.titleOfArticle = titleOfArticle;
        this.titleOfJournal = titleOfJournal;
        this.author = author;
        this.year = year;
        this.reference = reference;
        this.genres = genres;
    }

    public Book() {
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genre",joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})

    public List<Genre> getGenres() {return genres; }
    public void setGenres(List<Genre> genres) {this.genres = genres; }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getTitleOfArticle(){return titleOfArticle;}
    public void setTitleOfArticle(String titleOfArticle){this.titleOfArticle = titleOfArticle; }

    public String getTitleOfJournal(){return titleOfJournal;}
    public void setTitleOfJournal(String titleOfJournal){this.titleOfJournal = titleOfJournal; }

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author; }

    public String getYear(){return year; }
    public void setYear(String year){this.year = year;}

    public String getReference(){return reference; }
    public void setReference(String reference){this.reference = reference;}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", titleOfArticle='" + titleOfArticle + '\'' +
                ", titleOfJournal='" + titleOfJournal + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                ", reference='" + reference + '\'' +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (titleOfArticle != null ? !titleOfArticle.equals(book.titleOfArticle) : book.titleOfArticle != null)
            return false;
        if (titleOfJournal != null ? !titleOfJournal.equals(book.titleOfJournal) : book.titleOfJournal != null)
            return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (year != null ? !year.equals(book.year) : book.year != null) return false;
        if (reference != null ? !reference.equals(book.reference) : book.reference != null) return false;
        return !(genres != null ? !genres.equals(book.genres) : book.genres != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (titleOfArticle != null ? titleOfArticle.hashCode() : 0);
        result = 31 * result + (titleOfJournal != null ? titleOfJournal.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        return result;
    }
}

