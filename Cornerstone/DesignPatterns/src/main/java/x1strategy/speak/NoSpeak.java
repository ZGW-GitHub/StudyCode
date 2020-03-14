package x1strategy.speak;

public class NoSpeak implements SpeakParent {
    @Override
    public void speak() {
        System.out.println("我不会说话！");
    }
}
