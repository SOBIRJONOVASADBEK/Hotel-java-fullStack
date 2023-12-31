package Book;

import Book.Service.LibraryService;
import Book.Service.Inpul.LibraryServiceImpl;
import Book.modul.Author;
import Book.modul.Book;
import Book.modul.Genre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

   private static Scanner scanner = new Scanner(System.in);
    private static LibraryService libraryService = new LibraryServiceImpl();

    public static void main(String[] args) {
        boolean s = true;
        while (s) {
            switch (firstMenu()) {
                case 0:
                    System.exit(-1);
                case 1:
                    search();
                    break;
                case 2:
                    addBookGenre();
                    break;
                case 3:
                    libraryService.showBooks();
                    break;
                case 4:
                    deleteBook();
                    break;
                default:
                    System.out.println("Restart!");
            }
            }
        }





    public static Integer firstMenu() {
        System.out.println("0 - chiqish;\n" +
                "1 - Search;\n" +
                "2 - Add Book;\n" +
                "3 - Show Books;\n" +
                "4 - Delete Book.");
        System.out.println();
        System.out.print("Tanlang: ");
        return scanner.nextInt();

    }

    public static void search() {
        System.out.println("1- Kitob nomi bo'yicha qidiruv: \n2- Author bo'yicha qidiruv: \n3- Janr Bo'yicha qidiruv");
        int temp = scanner.nextInt();
        switch (temp) {
            case 1:
                searchByName();
                break;
            case 2:
                searchByAuthor();
                break;
            case 3:
                searchByGenre();
                break;
            default:
                System.out.println("Error number");
                break;
        }
    }




    public static void searchByGenre() {
        System.out.println("1-Badiy: \n2-Ilmiy: \n3-Diniy\n");
        int a = scanner.nextInt();
        switch (a) {

            case 1:showByGenre("Badiy");break;
            case 2:showByGenre("Ilmiy");break;
            case 3:showByGenre("Diniy");break;
            default:searchByGenre();
        }
    }


    public static void addBookGenre() {
        System.out.println("1-Badiy: \n2-Ilmiy: \n3-Diniy: ");
        int a = scanner.nextInt();
        switch (a) {
            case 1: libraryService.addBook(giveBookInfo("Badiy"));
            break;
            case 2:libraryService.addBook(giveBookInfo("Ilmiy"));
            break;
            case 3: libraryService.addBook(giveBookInfo("Diniy"));
            break;
            default:
                addBookGenre();

        }
    }

    public static void searchByAuthor() {
        String name = scanner.next();
        System.out.println("Isim:");
        List<Book> bookList = libraryService.searchBooksByAuthor(name);
        if (bookList.isEmpty()) {
            System.out.println("Bu authorli kitob mavjud emas!");
        }
        for (Book book : bookList) {
            System.out.println(book);
        }
    }


    public static Book giveBookInfo(String genreName) {
        System.out.println("Author sonni kiriting = ");
        int n  = scanner.nextInt();
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Author nomini kiriting = ");
            String name = scanner.next();
            System.out.println("Author familyasini kiritng = ");
            String surname = scanner.next();
            System.out.println("Author tug'ulgan sanasini kiriting YYYY-MM-DD = ");
            String brithDate = scanner.next();
            DateTimeFormatter formatterAuthor = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate brithDateD = LocalDate.parse(brithDate, formatterAuthor);
            System.out.println("Jinsini kiriting = ");
            String gender = scanner.next();
            Author author = libraryService.searchAuthor(name, surname, brithDateD, gender);
            if (author == null) {
                author = new Author(name, surname, brithDateD, gender);
            }
               authors.add(author);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Kitob nomi =");
        String name = scanner.next();
        System.out.println("Kitob varoqlari =");
        int page = scanner.nextInt();
        System.out.println("Kitob sanasi YYYY-MM-DD :");
        String  detBook = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(detBook, formatter);
        System.out.println("KItob narxini kiriting =");
        double price = scanner.nextDouble();
        Genre genre = libraryService.searchByGenreByName(genreName);
        if (genre == null) {
            genre = new Genre(genreName);
            libraryService.addGenre(genre);
        }
        return new Book(name, genre, authors, page, localDate, price);


    }

    public static void searchByName() {
        String name = scanner.next();
        System.out.println("isim:");
        List<Book> bookList = libraryService.searchBooksByName(name);
        if (bookList.isEmpty()) {
            System.out.println("Bu nomdagi kitob mavjud emas!");
        }
        for (Book book : bookList) {
            System.out.println(book);
        }
    }


    public static void deleteBook() {
        System.out.println("Kitob nomi");
        String name = scanner.next();
        List<Book> bookList = libraryService.searchBooksByName(name);
        if (bookList.isEmpty()) {
            System.out.println("Bu nomdagi kitob mavjud emas");
        } else {
            for (Book book : bookList) {
                libraryService.removeBook(book);
            }
            System.out.println("Kitob ochirildi");
        }
    }




    public static void showByGenre(String genreName) {
        List<Book> bookList = libraryService.searchBooksByGenre(genreName);
        if (bookList.isEmpty()) {
            System.out.printf(" %s janirdagi kitoblar mavjud emas!",genreName);
        }
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
