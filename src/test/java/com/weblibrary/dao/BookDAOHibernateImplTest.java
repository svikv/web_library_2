package com.weblibrary.dao;
import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class BookDAOHibernateImplTest {
    BookDAO bookDAO = new BookDAOHibernateImpl();
    String genre1 = "Travel";
    String genre2 = "Horror";

    @Test
    public void testAddBook1()throws HibernateException {

        long i = bookDAO.addBook("t", "a", "1920", "Horror", "", "");

        Book expectedBook = new Book("t", "a", "1920");
        expectedBook.setIsbn(i);
        expectedBook.getGenres().add(Genre.getGenre(genre2));

        Session session=HibernateUtil.beginTransaction();
        Book resultBook = (Book) session.get(Book.class, i);
        HibernateUtil.commitTransaction();

        assertEquals(expectedBook, resultBook);

    }

    @Test
    public void testAddBook2()throws HibernateException {

        long i = bookDAO.addBook("tl", "au", "1920", "Horror", "", "");

        Book expectedBook = new Book("t", "a", "1920");
        expectedBook.setIsbn(i);
        expectedBook.getGenres().add(Genre.getGenre(genre2));

        Session session=HibernateUtil.beginTransaction();
        Book resultBook = (Book) session.get(Book.class, i);
        HibernateUtil.commitTransaction();

        assertFalse(expectedBook.equals(resultBook));
    }
}
