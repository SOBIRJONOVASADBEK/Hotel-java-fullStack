package Book.Service;

import Book.modul.Author;
import Book.modul.Book;
import Book.modul.Genre;

import java.time.LocalDate;
import java.util.List;

public interface LibraryService {

    List<Book> searchBooksByName(String nane);

    List<Book> searchBooksByAuthor(String  authorName);

    List<Book> searchBooksByGenre(String genreName);

    Genre searchByGenreByName(String genreName);
    Author searchAuthor(String name, String surName, LocalDate brithDat, String gender);

  void showBooks();

    void addGenre(Genre genre);

    void addBook(Book book);

    void removeBook(Book book);

}
