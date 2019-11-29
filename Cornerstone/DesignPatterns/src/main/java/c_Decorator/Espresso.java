package com.snow.sjms.c_Decorator;

/**
 * 浓缩咖啡
 */
public class Espresso extends Beverage {

    public Espresso(){
        description = "浓缩咖啡！";
    }

    @Override
    public double cost() {
        return 1.99;
    }

}
