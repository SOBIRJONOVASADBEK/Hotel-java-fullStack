package Enttiy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClickCompany clickCompany = new ClickCompany();

        User user1 = new User("JOhn");
        User user2 = new User("Smith");
        User user3 = new User("Abror");
        User user4 = new User("Mansur");
        clickCompany.addUser(user1);
        clickCompany.addUser(user2);
        clickCompany.addUser(user3);
        clickCompany.addUser(user4);
        Scanner scanner = new Scanner(System.in);

        while (true) {

            showMenu();

            int command = scanner.nextInt();

            switch (command) {
                case 0:{
                    System.exit(0);
                }
                case 1:{
                    clickCompany.showUser();
                    break;
                }
                case 2:{
                    showUsersCard(clickCompany);
                    break;
                }
                case 3:{
                    addCard(clickCompany);
                    break;

                }
                case 4:{
                    System.out.println(clickCompany.getBalance());
                    break;
                }
                default:{
                    System.out.println("Etiborli boling");
                }
            }
        }
    }


    public static int readValue() {
        return new Scanner(System.in).nextInt();
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("Tanlang: ");
        System.out.println(" 0. exit; \n 1. show Users; \n 2. show User's Cards; \n 3. Add cards; \n 4. click balance:" );
    }

    private static void addCard(ClickCompany clickCompany) {
        User user =chooseUser(clickCompany);

        if (user == null) {
            System.out.println("\uD83D\uDEAB User not found! \uD83D\uDEAB");
            return;
        }
        Card newCard = createCard();
        user.addCard(newCard);
    }

    private static Card createCard() {

        System.out.println("Enter card number");
        String num = getScaner().next();

        System.out.println("Enter Card expiry");
        String expiryDate = getScaner().next();

        System.out.println("Enter card pin");
        String pin = getScaner().next();

        System.out.println("Enter Card balance");
        Long balance = getScaner().nextLong();

        Card card = new Card(balance, num ,expiryDate, pin);

        return card;

    }

    private static User addUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter User's name");
        String name = scanner.next();
        User user = new User(name);

        return user;

    }


    private static void showUsersCard(ClickCompany clickCompany) {


        User user = chooseUser(clickCompany);
        if (user == null) {
            System.out.println("\uD83D\uDEAB User not found! \uD83D\uDEAB");
            return;
        }
        System.out.println();
        user.showCards();
    }

    private static User chooseUser(ClickCompany clickCompany) {
        System.out.println("Userning id sini tanlang");
        clickCompany.showUser();
        Long id = getScaner().nextLong();
       return clickCompany.finUserCardById(id);

    }

    private static Scanner getScaner() {
        return new Scanner(System.in);
    }

    private static void addBalance(ClickCompany clickCompany) {
    }


}