package Enttiy;


import java.time.LocalDate;

public class ClickCompany {



    private Long balance = 0L;
    private User[] users;

    public ClickCompany(User[] users) {
        this.users = users;
    }

    public ClickCompany() {
        this(new User[0]);
    }

    public void transfer(Card senderCard, Card receiver, long amount, String pin) {
        if (checkPin(senderCard, pin)) {
            System.out.println(" pinCode error!");
            return;
        }
        if (senderCard.getCardNumber().equals(receiver.getCardNumber())) {
            System.out.println(" Card lar bir xil bolmasligi kere!");
            return;
        }
        if (checkExpiry(senderCard.getExpiryDate())) {
            System.out.println("⚠️ Yuboruvchi cardni mudati tugagan");
        }
        if (checkExpiry(senderCard.getExpiryDate())) {
            System.out.println("⚠️ Qabul qiluvch cardni mudati tugagan");
        }

        long commission = (amount / 100);
        if (senderCard.getBalance() >= amount + commission) {
            senderCard.yechibol(amount + commission);
            receiver.tuldir(amount);
            tuldir(commission);
            System.out.println("o'tkazildi");
        } else {
            System.out.println("Balance yetarli emas!");
        }
    }

    public void transfer(Card senderCard, String receiverCardNumber, long amount, String pin) {
        Card receiverCard = finCard(senderCard.getCardNumber());
        if (receiverCard != null) {
            transfer(senderCard, receiverCard, amount, pin);
        } else {
            System.out.println("Card not found! ");
        }
    }

    private Card finCard(String cardNumber) {
        for (User user : this.users) {
            for (Card card : user.getCards()) {
                if (cardNumber.equals(card.getCardNumber())) {
                    return card;
                }
            }
        }
        return null;
    }

    public User finUserCardById(Long id) {
        for (User user : this.users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }


    public void tuldir(long amount) {
        this.balance += amount;

    }


    public void addCard(Card card, User user) {

        if (checkExpiry(card.getExpiryDate())) {
            if (!isCardExist(card)) {
                user.addCard(card);
            } else {
                System.out.println("⚠️bu card alaqachon qowilgan");
            }
        } else {
            System.out.println("⚠️bu card ni mudati tugagan ");
        }
    }

    private boolean checkExpiry(String expiry) {
        if (expiry.length() != 4) {
            return false;
        }
        String first = expiry.substring(0, 2);
        String second = expiry.substring(2);


        int month = Math.min(Integer.parseInt(first), Integer.parseInt(second));
        int year = Math.max(Integer.parseInt(first), Integer.parseInt(second));

        int nowYear = LocalDate.now().getYear();
        int nowMonth = LocalDate.now().getMonthValue();

        return (year >= nowYear) && (nowMonth >= month);
    }

    private boolean isCardExist(Card newCard) {
        for (User user : users) {
            if (user.isExistCard(newCard.getCardNumber())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPin(Card card, String pin) {
        if (card.getPinCode() == null) {
            return false;
        }
        return card.getPinCode().equals(pin);
    }

    public void showUser() {
        for (int i = 0; i < users.length; i++) {
            System.out.println((i + 1) + " , " + this.users[i].toString());
        }

    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void addUser(User user) {
        User[] newUsers = new User[this.users.length + 1];
        System.arraycopy(this.users, 0, newUsers, 0, this.users.length);
        newUsers[newUsers.length - 1] = user;
        this.users = newUsers;

    }




    }



