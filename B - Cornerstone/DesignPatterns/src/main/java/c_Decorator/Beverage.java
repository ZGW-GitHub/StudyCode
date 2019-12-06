package c_Decorator;

/**
 * 超父类
 */
public abstract class Beverage {

    String description = "表示咖啡";

    public String getDescription(){
        return description;
    }

    public abstract double cost();

}
