package O2;

public class ThreadV1 {
    public static void main(String[] args) {
        ShowV1 show01 = new ShowV1("1");
        ShowV1 show02 = new ShowV1("2");
        ShowV1 show03 = new ShowV1("3");
        show01.start();
        show02.start();
        show03.start();
    }
}
