package com.company;

public class Card {
    private int id;
    private double credit;

    public Card(int id, double credit) {
        this.id = id;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void addCredit(double credit) {
        this.credit+=credit;
    }

    public void sell(Double price) {
       this.credit-=price;
    }
}
