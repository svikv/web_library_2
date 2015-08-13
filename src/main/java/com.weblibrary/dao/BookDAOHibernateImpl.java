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
        List<Book> books=null;
        List<Genre> genres=null;
        Criteria c1=session.createCriteria(Book.class);
        Criteria c2=session.createCriteria(Genre.class);

        if  (title!=null && author!=null && year!=null && genre!=null) {
            c1.add(Restrictions.eq("title", title)).add(Restrictions.eq("author", author))
                    .add(Restrictions.eq("year", year)).createCriteria("genres").add(Restrictions.eq("type", genre));
        }

        else if(author!=null && year!=null && genre!=null) {
            c1.add(Restrictions.eq("author", author)).add(Restrictions.eq("year", year)).
                    createCriteria("genres").add(Restrictions.eq("type", genre));
        }
        else if(title!=null && year!=null && genre!=null) {
            c1.add(Restrictions.eq("title", title)).add(Restrictions.eq("year", year)).
                    createCriteria("genres").add(Restrictions.eq("type", genre));
        }

        else if(title!=null && author!=null && genre!=null) {
            c1.add(Restrictions.eq("title", title)).add(Restrictions.eq("author", author)).
                    createCriteria("genres").add(Restrictions.eq("type", genre));
        }

        else if(author!=null && genre!=null) {
            c1.add(Restrictions.eq("author", author)).createCriteria("genres").add(Restrictions.eq("type", genre));
        }

        else if(year!=null && genre!=null) {
            c1.add(Restrictions.eq("year", year)).createCriteria("genres").add(Restrictions.eq("type", genre));
        }

        else if(title!=null && genre!=null) {
            c1.add(Restrictions.eq("title", title)).createCriteria("genres").add(Restrictions.eq("type", genre));
        }

        else if (genre!=null){
            c1.createCriteria("genres").add(Restrictions.eq("type", genre));
        }

        else if (title!=null) {
            c1.add(Restrictions.eq("title", title));
            c2.createCriteria("books").add(Restrictions.eq("title", title));
            Genre g=(Genre)c2.list().get(0);
            genre=g.getGenre();
        }

        else if(author!=null) {
            c1.add(Restrictions.eq("author", author));
            c2.createCriteria("books").add(Restrictions.eq("author", author));
            Genre g=(Genre)c2.list().get(0);
            genre=g.getGenre();
        }

        else if(year!=null) {
            c1.add(Restrictions.eq("year", year));
            c2.createCriteria("books").add(Restrictions.eq("year", year));
            Genre g=(Genre)c2.list().get(0);
            genre=g.getGenre();
        }

        else if(title!=null && author!=null) {
            c1.add(Restrictions.eq("title", title)).add(Restrictions.eq("author", author));
            c2.createCriteria("books").add(Restrictions.eq("title", title)).add(Restrictions.eq("title", title));
            Genre g=(Genre)c2.list().get(0);
            genre=g.getGenre();
        }

        else if(title!=null && year!=null) {
            c1.add(Restrictions.eq("title", title)).add(Restrictions.eq("year", year));
            c2.createCriteria("books").add(Restrictions.eq("title", title)).add(Restrictions.eq("year", year));
            Genre g=(Genre)c2.list().get(0);
            genre=g.getGenre();
        }

        else if(author!=null && year!=null) {
            c1.add(Restrictions.eq("author", author)).add(Restrictions.eq("year", year));
            c2.createCriteria("books").add(Restrictions.eq("author", author)).add(Restrictions.eq("year", year));
            Genre g=(Genre)c2.list().get(0);
            genre=g.getGenre();
        }

        else if(title!=null && author!=null && year!=null){
            c1.add(Restrictions.eq("title", title)).add(Restrictions.eq("author", author)).add(Restrictions.eq("year", year));
            c2.createCriteria("books").add(Restrictions.eq("author", author)).add(Restrictions.eq("year", year)).
                    add(Restrictions.eq("year", year));
            Genre g=(Genre)c2.list().get(0);
            genre=g.getGenre();
        }

        books=c1.list();
        HibernateUtil.commitTransaction();
        return new BookFull(books,genre);
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
