package com.weblibrary.dao;

import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import org.hibernate.HibernateException;
import java.util.HashSet;
import java.util.List;

public interface BookDAO {

    long addBook(String titleOfArticle, String titleOfJournal, String author, String year, String reference, String genre1, String genre2, String genre3, String genre4, String genre5);
    HashSet<Book> findAll(String titleOfArticle, String titleOfJournal, String author, String year, List<String> genreList);
    boolean delete (long id);
    boolean deleteGenre (String genre);
    boolean findBook (String titleOfArticle, String titleOfJournal, String author, String year);
    long addGenre(String genre);
    boolean findGenre (String genre);
    List<Genre> findAllGenres();
    Book findById(long id);
    Book update(long id, String author, String titleOfArticle, String titleOfJournal, String year, String reference, String genre1, String genre2, String genre3, String genre4, String genre5);
    Genre getGenre(String type);
}