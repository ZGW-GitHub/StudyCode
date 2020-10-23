package p.ce.lue.speak;

/**
 * @author 愆凡
 */
public class CannotSpeak implements SpeakParent {

    @Override
    public void speak() {
        System.out.println("我不会说话！");
    }

}
