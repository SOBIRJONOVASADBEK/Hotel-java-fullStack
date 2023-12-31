package Enttiy;


import java.util.Arrays;

public class User {

    public User(Card[] cards, String name) {
        this.id = next();
        this.cards = cards;
        this.name = name;
    }

    public User(String name) {
        this(new Card[0],name);
    }

   private Long id;

   private Card[] cards;


   private String name;

    public String totalAmount() {
        long sum = 0L;
        for (Card card : cards) {
            sum += card.getBalance();
        }
        return String.valueOf((double) sum / 100);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static Long idGenerator = 1L;

    public static Long next() {
        return idGenerator++;
    }

    public void addCard(Card card) {
        Card[] newCard = new Card[this.cards.length + 1];
        for (int i = 0; i < this.cards.length; i++) {
            newCard[i] = this.cards[i];
        }
        newCard[newCard.length - 1] = card;
        this.cards = newCard;
    }

    public void showCards() {
        for (int i = 0; i < cards.length; i++) {
            System.out.println((i + 1) + ", " + cards[i].toString());
        }
    }


    public Card getCardById(Long id) {
        for (Card card : cards) {
            if (card.getId().equals(id)) {
                return card;
            }
        }
        return null;

    }

    public void deleteCardById(Long id) {
        if (isExistCard(id)) {
            Card[] newCards = new Card[cards.length - 1];
            int w = 0 ;
            for (Card card : cards) {
                if (!card.getId().equals(id)) {
                    newCards[w++] = card;
                }
            }
            this.cards = newCards;
        }
    }


    public boolean isExistCard(String cardNumber) {
        for (Card card : this.cards) {
            if (cardNumber.equals(card.getCardNumber())) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistCard(Long id) {
        for (Card card : this.cards) {
            if (id.equals(card.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}






