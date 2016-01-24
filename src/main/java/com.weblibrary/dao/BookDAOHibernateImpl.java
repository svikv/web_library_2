package com.weblibrary.dao;

import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookDAOHibernateImpl implements BookDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public long addBook(String titleOfArticle, String titleOfJournal, String author, String year, String reference, String genre1, String genre2, String genre3, String genre4, String genre5){

        Book book = new Book(titleOfArticle, titleOfJournal, author, year, reference);
        if (!"".equals(genre1)) book.getGenres().add(getGenre(genre1));
        if (!"".equals(genre2)) book.getGenres().add(getGenre(genre2));
        if (!"".equals(genre3)) book.getGenres().add(getGenre(genre3));
        if (!"".equals(genre4)) book.getGenres().add(getGenre(genre4));
        if (!"".equals(genre5)) book.getGenres().add(getGenre(genre5));

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(book);
        tx.commit();
        return book.getId();
    }

    public HashSet<Book> findAll(String titleOfArticle, String titleOfJournal, String author, String year, List<String> genreList){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria c1 = session.createCriteria(Book.class);
        if ((!"".equals(titleOfArticle)) && (!titleOfArticle.equals(null))) c1.
                add(Restrictions.like("titleOfArticle", titleOfArticle, MatchMode.ANYWHERE).ignoreCase());
        if ((!"".equals(titleOfJournal)) && (!titleOfJournal.equals(null))) c1.
                add(Restrictions.like("titleOfJournal", titleOfJournal, MatchMode.ANYWHERE).ignoreCase());
        if ((!"".equals(author)) && (!author.equals(null))) c1.add(Restrictions.eq("author", author));
        if ((!"".equals(year)) && (!year.equals(null))) c1.add(Restrictions.eq("year", year));
        if (!genreList.isEmpty()) c1.createCriteria("genres").add(Restrictions.in("genre", genreList));

        List<Book> books = (ArrayList<Book>) c1.list();
        HashSet<Book> hash = new HashSet<>(books);
        for(Book book: hash) System.out.println(book.getGenres());
        tx.commit();
        return hash;
    }

    public boolean delete (long id) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete Book where id = :number";
        Query query  = session.createQuery(hql);
        query.setLong("number", id);
        int row = query.executeUpdate();
        System.out.println(row + " row were deleted");
        tx.commit();
        if (row == 0) return false;
        else return true;
    }

    public boolean deleteGenre (String genre) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete Genre where genre = :genre";
        Query query  = session.createQuery(hql);
        query.setString("genre", genre);
        int row = query.executeUpdate();
        System.out.println(row + " row were deleted");
        tx.commit();
        if (row == 0) return false;
        else return true;
    }

    public boolean findBook (String titleOfArticle, String titleOfJournal, String author, String year) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria c1 = session.createCriteria(Book.class);
        c1.add(Restrictions.eq("titleOfArticle", titleOfArticle)).add(Restrictions.eq("titleOfJournal", titleOfJournal)).
                add(Restrictions.eq("author", author)).add(Restrictions.eq("year", year));
        ArrayList<Book> books = (ArrayList<Book>) c1.list();
        tx.commit();
        return (books.isEmpty());
    }


    public long addGenre(String genre) {

        Genre g = new Genre(genre);
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(g);
        tx.commit();
        return g.getId();
    }

    public boolean findGenre (String genre) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria c1 = session.createCriteria(Genre.class);
        c1.add(Restrictions.eq("genre", genre));
        List<Genre> genres = (ArrayList<Genre>) c1.list();
        tx.commit();
        return (genres.isEmpty());
    }

    public List<Genre> findAllGenres(){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria c1 = session.createCriteria(Genre.class);
        List<Genre> genres = (ArrayList<Genre>) c1.list();
        tx.commit();
        return genres;
    }

    public Book findById(long id){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        System.out.println(book.getGenres());
        tx.commit();
        return book;
    }

    @Override
    public Book update(long id, String author, String titleOfArticle, String titleOfJournal, String year, String reference, String genre1, String genre2, String genre3, String genre4, String genre5) {

        Book book = findById(id);
        book.setAuthor(author);
        book.setTitleOfArticle(titleOfArticle);
        book.setTitleOfJournal(titleOfJournal);
        book.setYear(year);
        if (!"".equals(genre1)) book.getGenres().add(getGenre(genre1));
        if (!"".equals(genre2)) book.getGenres().add(getGenre(genre2));
        if (!"".equals(genre3)) book.getGenres().add(getGenre(genre3));
        if (!"".equals(genre4)) book.getGenres().add(getGenre(genre4));
        if (!"".equals(genre5)) book.getGenres().add(getGenre(genre5));
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(book);
        tx.commit();
        return book;
    }

    public Genre getGenre(String type){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "select g.id from Genre g where g.genre = :type";
        org.hibernate.Query query  = session.createQuery(hql);
        query.setString("type", type);
        long id = (Long)query.uniqueResult();
        tx.commit();
        return new Genre(id,type);
    }
}
