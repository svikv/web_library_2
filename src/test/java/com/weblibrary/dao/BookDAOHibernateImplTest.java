package com.weblibrary.dao;
import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

public class BookDAOHibernateImplTest {
    BookDAO bookDAO = new BookDAOHibernateImpl();
    String genre1 = "Travel";
    //String genre2 = "Horror";

    @Test
    public void testAddBook()throws HibernateException {

        Book b1=bookDAO.findByIsbn(3);
        System.out.println("1");
        bookDAO.addBook("t", "a", "y", "Horror", "", "");
        System.out.println("2");
        Book book = new Book("t", "a", "y");
        book.getGenres().add(Genre.getGenre(genre1));

        Session session= HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Book.class);
        ArrayList<Book> list= (ArrayList<Book>) c1.list();
        HibernateUtil.commitTransaction();

        assertTrue(list.contains(book));
    }
}
