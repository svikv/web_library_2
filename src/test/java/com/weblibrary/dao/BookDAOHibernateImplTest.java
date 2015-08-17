package com.weblibrary.dao;

import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/**
 * Created by vlad on 17.08.15.
 */
public class BookDAOHibernateImplTest {
    BookDAO bookDAO = new BookDAOHibernateImpl();
    String genre1 = "Travel";
    //String genre2 = "Horror";

    @Test
    public void testAddBook(){
        bookDAO.addBook("t", "a", "y", "Horror", "", "");

        Book book = new Book("t", "a", "y");
        book.getGenres().add(Genre.getGenre(genre1));

        Session session= HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Book.class);
        ArrayList<Book> list= (ArrayList<Book>) c1.list();
        HibernateUtil.commitTransaction();

        assertTrue(list.contains(book));
    }
}
