package com.weblibrary.dao;

import com.weblibrary.service.BookFull;
import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BookDAOHibernateImpl implements BookDAO {

    public void addBook(String title,String author,String year, String type1, String type2){

        //Session session=HibernateUtil.getSession();
        //session.beginTransaction();
        Session session = HibernateUtil.beginTransaction();

        Book book=new Book();
        Genre genre1=new Genre();
        Genre genre2=new Genre();

        book.getGenres().add(genre1);
        book.getGenres().add(genre2);
        genre1.getBooks().add(book);
        genre2.getBooks().add(book);

        session.save(book);


        HibernateUtil.commitTransaction();
        //session.close();
        System.out.println("Transaction successful!!!");
    }

    public BookFull findAll(String title,String author,String year, String genre){

        Session session = HibernateUtil.beginTransaction();
        List<Book> books;
        Criteria c1=session.createCriteria(Book.class);

        if (!title.equals("")) c1.add(Restrictions.eq("title", title));
        if (!author.equals("")) c1.add(Restrictions.eq("author", author));
        if (!year.equals("")) c1.add(Restrictions.eq("year", year));
        if (!genre.equals("")) c1.add(Restrictions.eq("genre", genre));

        books=c1.list();
        HibernateUtil.commitTransaction();
        return new BookFull(books);
    }

    public void delete(long isbn){

        Session session=HibernateUtil.beginTransaction();
        String hql = "delete Book where isbn= :number";
        Query query  = session.createQuery(hql);
        query.setLong("number", isbn);
        int row =query.executeUpdate();
        System.out.println(row+ " row were deleted");
        HibernateUtil.commitTransaction();
    }

    public Book update(long isbn,String author,String title,String year){

        Session session=HibernateUtil.beginTransaction();
        Book book=(Book)session.get(Book.class,isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        HibernateUtil.commitTransaction();
        return book;
    }

    public Book findBook(long isbn){

        Session session=HibernateUtil.beginTransaction();
        Book book=(Book)session.get(Book.class,isbn);
        HibernateUtil.commitTransaction();
        return book;
    }
}
