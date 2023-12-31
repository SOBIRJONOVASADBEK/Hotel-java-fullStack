package com.company;

public class MachineColumn {
   private int column;
   private Beverage beverage;
   private int cans;

    public MachineColumn() {
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public int getCans() {
        return cans;
    }

    public void setCans(int cans) {
        this.cans = cans;
    }

    public void sell() {
        this.cans-=1;
    }
}
