package com.weblibrary.dao;

import com.weblibrary.model.Book;
import com.weblibrary.model.Genre;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class BookDAOHibernateImpl implements BookDAO {

    private static final Logger logger = LoggerFactory.getLogger(BookDAOHibernateImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addBook(Book book){
        List<Genre> list=book.getGenres();
        List<String> listStr=new ArrayList<String>();
        for(Genre genre:list) listStr.add(genre.getGenre());

        if (!"".equals(listStr.get(0))) book.getGenres().add(getGenre(listStr.get(0)));
        if (!"".equals(listStr.get(1))) book.getGenres().add(getGenre(listStr.get(1)));
        if (!"".equals(listStr.get(2))) book.getGenres().add(getGenre(listStr.get(2)));
        if (!"".equals(listStr.get(3))) book.getGenres().add(getGenre(listStr.get(3)));
        if (!"".equals(listStr.get(4))) book.getGenres().add(getGenre(listStr.get(4)));
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book saved successfully, book details="+book);
        return book.getId();
    }

    @Override
    public HashSet<Book> findAll(Book book){
        List<Genre> list=book.getGenres();
        List<String> listStr=new ArrayList<String>();
        for(Genre genre:list) listStr.add(genre.getGenre());

        Session session = this.sessionFactory.getCurrentSession();
        Criteria c1 = session.createCriteria(Book.class);
        if ((!"".equals(book.getTitleOfArticle())) && (!book.getTitleOfArticle().equals(null))) c1.
                add(Restrictions.like("titleOfArticle", book.getTitleOfArticle(), MatchMode.ANYWHERE).ignoreCase());
        if ((!"".equals(book.getTitleOfJournal())) && (!book.getTitleOfJournal().equals(null))) c1.
                add(Restrictions.like("titleOfJournal", book.getTitleOfJournal(), MatchMode.ANYWHERE).ignoreCase());
        if ((!"".equals(book.getAuthor())) && (!book.getAuthor().equals(null))) c1.add(Restrictions.eq("author", book.getAuthor()));
        if ((!"".equals(book.getYear())) && (!book.getYear().equals(null))) c1.add(Restrictions.eq("year", book.getYear()));
        if (!listStr.isEmpty()) c1.createCriteria("genres").add(Restrictions.in("genre", listStr));

        List<Book> books = (ArrayList<Book>) c1.list();
        HashSet<Book> hash = new HashSet<>(books);
        for(Book b: hash) {
            System.out.println(b.getGenres());
            logger.info("Book List::"+b);
        }
        return hash;
    }

    @Override
    public boolean deleteBook (int id) {

        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book)session.load(Book.class, new Integer(id));
        if(null != book){
            session.delete(book);
            logger.info("Book deleted successfully, book details="+book);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteGenre (int id) {

        Session session = this.sessionFactory.getCurrentSession();
        Genre genre = (Genre)session.load(Genre.class, new Integer(id));
        if(null != genre){
            session.delete(genre);
            logger.info("Genre deleted successfully, genre details="+genre);
            return true;
        }
        else return false;
    }

    @Override
    public boolean findBook (Book book) {

        Session session = this.sessionFactory.getCurrentSession();
        Criteria c1 = session.createCriteria(Book.class);
        c1.add(Restrictions.eq("titleOfArticle", book.getTitleOfArticle())).add(Restrictions.eq("titleOfJournal", book.getTitleOfJournal())).
                add(Restrictions.eq("author", book.getAuthor())).add(Restrictions.eq("year", book.getYear()));
        ArrayList<Book> books = (ArrayList<Book>) c1.list();
        return (books.isEmpty());
    }

    @Override
    public int addGenre(String genre) {

        Genre g = new Genre(genre);
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(g);
        logger.info("Genre saved successfully, genre details="+genre);
        return g.getId();
    }

    @Override
    public boolean findGenre (Genre genre) {

        Session session = this.sessionFactory.getCurrentSession();
        Criteria c1 = session.createCriteria(Genre.class);
        c1.add(Restrictions.eq("genre", genre.getGenre()));
        List<Genre> genres = (ArrayList<Genre>) c1.list();
        return (genres.isEmpty());
    }

    @Override
    public List<Genre> findAllGenres(){

        Session session = this.sessionFactory.getCurrentSession();
        Criteria c1 = session.createCriteria(Genre.class);
        List<Genre> genres = (ArrayList<Genre>) c1.list();
        for(Genre g : genres){
            logger.info("Genre List::"+g);
        }
        return genres;
    }

    @Override
    public Book getBookById(int id){

        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        System.out.println(book.getGenres());
        logger.info("Book loaded successfully, book details="+book);
        return book;
    }

    /*public Book update(long id, String author, String titleOfArticle, String titleOfJournal, String year, String reference, String genre1, String genre2, String genre3, String genre4, String genre5) {

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
    }*/

    @Override
    public Genre getGenre(String type){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "select g.id from Genre g where g.genre = :type";
        org.hibernate.Query query  = session.createQuery(hql);
        query.setString("type", type);
        int id = (Integer)query.uniqueResult();
        tx.commit();
        return new Genre(id,type);
    }
}
