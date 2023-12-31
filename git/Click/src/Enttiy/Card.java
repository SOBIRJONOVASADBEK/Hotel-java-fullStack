package Enttiy;

public class Card {


   private Long id;

   private Long balance;

   private String cardNumber;

   private String expiryDate;

   private String pinCode;

    public Card(Long id, Long balance, String cardNumber, String expiryDate, String pinCode) {
        this.id = next();
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.pinCode = pinCode;
    }

    public Card(Long balance, String cardNumber, String expiryDate, String pinCode) {
        this.id = next();
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.pinCode = getPinCode();
    }

    private static long idGenerator = 1L;

    public static Long next() {
        return idGenerator++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void yechibol(long amount) {
        this.balance -= amount;
    }



    public void tuldir(long amount) {
        this.balance += amount;

    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", balance=" + balance +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }

}

