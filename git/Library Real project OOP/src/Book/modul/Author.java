package Book.modul;

import java.time.LocalDate;
import java.util.List;

public class Author {

    private int id;
   private String name;
   private String surName;
   private LocalDate brithDat;
   private String gender;
    private List<Book> books;

    private static int count =1;
    {
        id = count++;
    }

    public Author(String name, String surName, LocalDate brithDat, String gender) {
        this.name = name;
        this.surName = surName;
        this.brithDat = brithDat;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "modulS.Author{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", brithDat=" + brithDat +
                ", gender=" + gender +
                ", books=" + books +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getBrithDat() {
        return brithDat;
    }

    public void setBrithDat(LocalDate brithDat) {
        this.brithDat = brithDat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
