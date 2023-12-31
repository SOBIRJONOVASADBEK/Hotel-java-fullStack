package Book.Service.Inpul;

import Book.Data.DataLibrary;
import Book.Service.LibraryService;
import Book.modul.Author;
import Book.modul.Book;
import Book.modul.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    @Override
    public List<Book> searchBooksByName(String name) {
        return DataLibrary.findByName(name);
    }

    @Override
    public List<Book> searchBooksByAuthor(String authorName) {
        List<Author> authors = DataLibrary.findAuthorByName(authorName);
        List<Book> bookList = new ArrayList<>();
        for (Author author : authors) {
            bookList.addAll(author.getBooks());
        }
        return bookList;
    }


    @Override
    public List<Book> searchBooksByGenre(String genreName) {
        Genre genre = DataLibrary.finGenreByName(genreName);
        if (genre == null) {
            return new ArrayList<>();
        }
        return DataLibrary.findByGenre(genre.getId());
    }

    @Override
    public Genre searchByGenreByName(String genreName) {
        return DataLibrary.finGenreByName(genreName);
    }

    @Override
    public Author searchAuthor(String name , String surName, LocalDate brithDate,String gender ) {
        return DataLibrary.findAuthor(name, surName, brithDate, gender);
    }

    @Override
    public void showBooks() {
        for (Book book : DataLibrary.getAllBooks()) {
            System.out.println(book);
        }
    }

    @Override
    public void addGenre(Genre genre) {
        DataLibrary.addGenre(genre);
    }

    @Override
    public void addBook(Book book) {
        DataLibrary.addBook(book);
    }

    @Override
    public void removeBook(Book book) {
        DataLibrary.deleteBook(book.getId());
    }


}
