package x.strategy.speak;

public class YaSpeak implements SpeakParent {
    @Override
    public void speak() {
        System.out.println("嘎嘎嘎！");
    }
}
