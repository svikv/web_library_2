package com.weblibrary.dao;

import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.BookFull;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashSet;

public class BookDAOHibernateImpl implements BookDAO {

    public void addBook(String title,String author,String year, String genre1, String genre2,String genre3) throws HibernateException{

        Book book=new Book(title, author, year);

        if(!"".equals(genre1)) book.getGenres().add(Genre.getGenre(genre1));
        if(!"".equals(genre2)) book.getGenres().add(Genre.getGenre(genre2));
        if(!"".equals(genre3)) book.getGenres().add(Genre.getGenre(genre3));

        Session session = HibernateUtil.beginTransaction();
        session.saveOrUpdate(book);
        HibernateUtil.commitTransaction();
        System.out.println("Transaction successful!!!");
    }

    public BookFull findAll(String title,String author,String year, String genre) throws HibernateException{

        Session session = HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Book.class);

        if (!title.equals("")) c1.add(Restrictions.eq("title", title));
        if (!author.equals("")) c1.add(Restrictions.eq("author", author));
        if (!year.equals("")) c1.add(Restrictions.eq("year", year));
        if (!genre.equals("")) c1.add(Restrictions.eq("genre", genre));

        ArrayList<Book> books= (ArrayList<Book>) c1.list();
        HashSet<Book> hash=new HashSet<>();
        for(Book book:books)  hash.add(book);

        HibernateUtil.commitTransaction();
        return new BookFull(hash);
    }

    public void delete(long isbn)  throws HibernateException{

        Session session=HibernateUtil.beginTransaction();
        String hql = "delete Book where isbn= :number";
        Query query  = session.createQuery(hql);
        query.setLong("number", isbn);
        int row =query.executeUpdate();
        System.out.println(row + " row were deleted");
        HibernateUtil.commitTransaction();
    }

    public Book findBook(String title,String author,String year) throws HibernateException{

        Session session=HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Book.class);
        c1.add(Restrictions.eq("title", title)).add(Restrictions.eq("author", author)).add(Restrictions.eq("year", year));
        ArrayList<Book> list= (ArrayList<Book>) c1.list();
        HibernateUtil.commitTransaction();
        return list.get(0);
    }

    public Book findByIsbn(long isbn) throws HibernateException{
        Session session=HibernateUtil.beginTransaction();
        Book book = (Book) session.get(Book.class, isbn);
        HibernateUtil.commitTransaction();
        System.out.println(isbn + " " + book.toString());
        return book;
    }

    @Override
    public Book update(long isbn, String author, String title, String year, String genre1, String genre2, String genre3) throws HibernateException {
        Book book = findByIsbn(isbn);

        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        HashSet<Genre> list = new HashSet<>();
        book.setGenres(list);
        if(!"".equals(genre1)) book.getGenres().add(Genre.getGenre(genre1));
        if(!"".equals(genre2)) book.getGenres().add(Genre.getGenre(genre2));
        if(!"".equals(genre3)) book.getGenres().add(Genre.getGenre(genre3));

        Session session = HibernateUtil.beginTransaction();
        session.saveOrUpdate(book);
        HibernateUtil.commitTransaction();
        return book;
    }
}
