package com.visnevskis;

abstract class District {

    protected String name;
    protected int residents;
    protected double averagePrice;

    abstract void getName();
    abstract void getResidents();
    abstract void getAveragePrice();


 

    public double getSaleProfit(){
        return (residents/10) * averagePrice;
    }

}
