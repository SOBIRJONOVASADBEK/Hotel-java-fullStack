package com.company;

import java.util.LinkedList;
import java.util.List;

public class VendingMachine {
    private List<Beverage> beverages;
    private List<Card> cards;
    private List<MachineColumn> machineColumns;
    public VendingMachine() {
        beverages=new LinkedList<>();
        cards=new LinkedList<>();
        machineColumns=new LinkedList<>();
    }
    public void addBeverage(String name,Double price){
       Beverage beverage=new Beverage(name,price);
       beverages.add(beverage);


    }

    public double getNarxi(String name){
        for (Beverage beverage : beverages){
            if (beverage.getName().equals(name)){
                return beverage.getPrice();
            }
        }
        return -1;
    }
    public void rechargeCard(int cardId, double credit){
        for (Card card:cards){
            if (card.getId()==cardId){
                card.addCredit(credit);
            }
        }
        Card card=new Card(cardId,credit);
        cards.add(card);
    }
    public double getCredit(int cardId){
        for (Card card:cards){
            if (card.getId()==cardId){
             return card.getCredit();
            }
        }
        return -1;
    }
    public void refillColumn(int column,String beverageName,int cans){
       Beverage beverage=getBeverage(beverageName);
       if (beverage != null){
           MachineColumn machineColumn=new MachineColumn();
           machineColumn.setBeverage(beverage);
           machineColumn.setColumn(column);
           machineColumn.setCans(cans);
           machineColumns.add(machineColumn);
       }
    }
    public int availableCans(String beverageName){
        int count=0;
      for (MachineColumn machineColumn: machineColumns){
          if (machineColumn.getBeverage().getName().equals(beverageName)){
                  count+=machineColumn.getCans();
          }
      }
      return count;
    }
    public int sell(String beverageName,int cardId){
        int count=1;
        MachineColumn machineColumn=getMachineColumn(beverageName);
        Card card=getCard(cardId);
        if (machineColumn!=null && card!=null && machineColumn.getCans()>0 && card.getCredit()>=machineColumn.getBeverage().getPrice()){
               Beverage beverage=machineColumn.getBeverage();
               card.sell(beverage.getPrice());
               machineColumn.sell();
               return machineColumn.getColumn();
        }
        return -1;
    }

    public Beverage getBeverage(String beverageName){
        for (Beverage beverage: beverages){
            if (beverage.getName().equals(beverageName)){
                return beverage;
            }
        }
        return null;
    }
    private MachineColumn getMachineColumn(String beverageName){
        for (MachineColumn machineColumn: machineColumns){
            if (machineColumn.getBeverage().getName().equals(beverageName)){
                return machineColumn;
            }
        }
        return null;
    }
    private Card getCard(int cardId){
        for (Card card: cards){
            if (card.getId()==cardId){
                return card;
            }
        }
        return null;
    }
}
