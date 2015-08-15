package com.weblibrary.dao;

import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.BookFull;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BookDAOHibernateImpl implements BookDAO {

    public void addBook(String title,String author,String year, String type1, String type2,String type3){

        Book book=new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);

        if(!type1.equals("")) book.getGenres().add(Genre.getGenre(type1));
        if(!type2.equals("")) book.getGenres().add(Genre.getGenre(type2));
        if(!type3.equals("")) book.getGenres().add(Genre.getGenre(type3));

        Session session = HibernateUtil.beginTransaction();
        session.save(book);
        HibernateUtil.commitTransaction();
        System.out.println("Transaction successful!!!");
    }

    public BookFull findAll(String title,String author,String year, String genre){
        /*criteria = session.createCriteria(Employee.class, "employee");
        criteria.setFetchMode("employee.address", FetchMode.JOIN);
        criteria.createAlias("employee.address", "address"); // inner join by default*/


        Session session = HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Book.class);

                //add(Restrictions.naturalId());
                ;//.createAlias("a.b", "b");
                //.setFetchMode("book_genre", FetchMode.JOIN).createAlias("book_genre","genre");


        if (!title.equals("")) c1.add(Restrictions.eq("title", title));
        if (!author.equals("")) c1.add(Restrictions.eq("author", author));
        if (!year.equals("")) c1.add(Restrictions.eq("year", year));
        if (!genre.equals("")) c1.add(Restrictions.eq("genre", genre));

        List<Book> books=c1.list();
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
