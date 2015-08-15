package com.weblibrary.entity;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.*;

import javax.persistence.*;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name="genre")
public class Genre {
    long id;
    String genre;
    List<Book> books=new Vector<Book>();

    public Genre(){}
    public Genre(long id,String genre){
        this.id=id;
        this.genre=genre;
    }

    @ManyToMany
    @JoinTable(name = "book_genre",joinColumns = {@JoinColumn(name = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_isbn")})
    public List<Book> getBooks() {return books; }
    public void setBooks(List<Book> books) {this.books=books; }

    @Id
    @GeneratedValue
    public long getId(){ return id;}
    public void setId(long id){ this.id=id; }

    public String getGenre(){return genre; }
    public void setGenre(String genre){this.genre=genre;}

    public static Genre getGenre(String type){
        Session session= HibernateUtil.beginTransaction();
        String hql="select g.id from Genre g where g.genre=:type";
        org.hibernate.Query query  = session.createQuery(hql);
        query.setString("type", type);

        long id = (Long)query.uniqueResult();
        HibernateUtil.commitTransaction();
        return new Genre(id,type);
    }
}