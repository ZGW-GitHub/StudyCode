package O2;

public class ShowV1 extends Thread {

    private String name;
    private static final int MAX = 500;
    // 这种情况下 index 必须为 static 才能保证多线程操作时 index 只实例化一次
    private static int index = 1;

    public ShowV1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(name + "号柜台：叫了" + (index++) + "号");
        }
    }
}
