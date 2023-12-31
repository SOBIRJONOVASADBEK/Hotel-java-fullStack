package Book.Data;

import Book.modul.Author;
import Book.modul.Book;
import Book.modul.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLibrary {
    private static List<Book> books = new ArrayList<>();
    private static List<Author> authors = new ArrayList<>();

    private static List<Genre> genres = new ArrayList<>();



    public static List <Author> findAuthorByName(String name) {
        List<Author> authorList = new ArrayList<>();
        for (Author author : authors) {
            if (author.getName().equals(name)) {
                authorList.add(author);
            }
        }
        return authorList;
    }

    public static Genre finGenreByName(String name) {
        for (Genre genre : genres) {
            if (genre.getName().equals(name)) {
                return genre;
            }
        }
        return null;
    }

    public static List<Book> findByName(String name) {
        List<Book> bookList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getName().equals(name)) {
                bookList.add(book);
            }
        }
        return bookList;
    }



    public static List<Book> getAllBooks() {
        return books;
    }

    public static void addBook(Book book) {
        books.add(book);
    }

    public static List<Book> findByGenre(int genreId) {
        List<Book> bookList = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().getId() == genreId) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    public static List<Book> findByAuthor(int authorId) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            for (Author author : authors) {
                if (author.getId() == authorId) {
                    authorBooks.add(book);
                }
            }
        }
        return authorBooks;
    }

    public static Book deleteBook(int bookId) {
        Book bookDelete = null;
        for (Book book : books) {
            if (book.getId() == bookId) {
                bookDelete = book;
                break;
            }
        }
        if (bookDelete != null) {
            books.remove(bookDelete);
        }
        return bookDelete;
    }

    public static void addGenre(Genre genre) {
        genres.add(genre);
    }


    public static Author findAuthor(String name, String surName, LocalDate brithDate, String gender) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getSurName().equals(surName) && author.getBrithDat().equals(brithDate) && author.getGender().equals(gender)) {
                return author;
            }
        }
        return null;
    }
}
