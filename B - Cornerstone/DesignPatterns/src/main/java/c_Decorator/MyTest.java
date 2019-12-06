package c_Decorator;

public class MyTest {

    public static void main(String[] args) {

        Beverage beverage = new Espresso();//浓缩咖啡
        System.out.println(beverage.getDescription() + beverage.cost());


        Beverage beverage1 = new HouseBlend();//综合咖啡
        beverage1 = new Mocha(beverage1);//摩卡
        beverage1 = new Mocha(beverage1);//摩卡
        beverage1 = new Whip(beverage1);//奶泡
        System.out.println(beverage1.getDescription() + beverage1.cost());

    }

}
