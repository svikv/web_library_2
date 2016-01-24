package com.weblibrary.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {
    long id;
    String genre;
    transient List<Book> books = new ArrayList<>();

    public Genre(){}
    public Genre(long id, String genre){
        this.id = id;
        this.genre = genre;
    }

    public Genre(String genre){
        this.genre = genre;
    }

    @ManyToMany
    @JoinTable(name = "book_genre",joinColumns = {@JoinColumn(name = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})

    public List<Book> getBooks() {return books; }
    public void setBooks(List<Book> books) {this.books = books; }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId(){ return id;}
    public void setId(long id){ this.id = id; }

    public String getGenre(){return genre; }
    public void setGenre(String genre){this.genre = genre;}

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", genre='" + genre + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre1 = (Genre) o;
        if (getId() != genre1.getId()) return false;
        return !(getGenre() != null ? !getGenre().equals(genre1.getGenre()) : genre1.getGenre() != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        return result;
    }
}