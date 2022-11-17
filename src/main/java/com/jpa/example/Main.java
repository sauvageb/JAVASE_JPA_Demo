package com.jpa.example;

import com.jpa.example.dao.BookJpaCrudDao;
import com.jpa.example.dao.CrudDAO;
import com.jpa.example.entity.Author;
import com.jpa.example.entity.Book;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World !");
        Book book = new Book(
                "Les quatre accords Toltèques.",
                "Découvrez ou redécouvrez Les quatre accords toltèques, et prenez comme des millions de lecteurs en France et à travers le monde, la voie de la liberté personnelle. Dans ce livre, Don Miguel révèle la source des croyances limitatrices qui nous privent de joie et créent des souffrances inutiles.",
                8.9f,
                3,
                LocalDate.now(),
                "https://m.media-amazon.com/images/I/71ZqQ7rSupL.jpg"
        );

        // CREATE
        CrudDAO<Book> bookCrudDAO = new BookJpaCrudDao();
        book.setAuthorList(Arrays.asList(new Author("Miguel", "Ruiz")));
        Book createdBook = bookCrudDAO.create(book);

        // READ
        Optional<Book> book1 = bookCrudDAO.findById(createdBook.getId());
        System.out.println(book1.get().getName());

        // UPDATED
        createdBook.setName("UPDATED");
        bookCrudDAO.update(createdBook);

        // DELETE
        bookCrudDAO.delete(createdBook.getId());

        // FIND ALL => no display.
        bookCrudDAO.findAll().forEach(b -> System.out.println(b));
    }
}
