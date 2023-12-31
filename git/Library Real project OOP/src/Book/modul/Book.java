package Book.modul;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private int id;
    private String name;
    private Genre genre;
    private List<Author> authors;
    private int page;
    private LocalDate date;
    private double price;

    private static int count = 1;
    {
        id = count++;
    }
    public Book(String name, Genre genre, List<Author> authors, int page, LocalDate date, double price) {
        this.name = name;
        this.genre = genre;
        this.authors = authors;
        this.page = page;
        this.date = date;
        this.price = price;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", authors=" + authors +
                ", page=" + page +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
