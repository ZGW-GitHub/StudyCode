package p.ce.lue.speak;

/**
 * 表现1：不会说话
 *
 * @author 愆凡
 */
public class CannotSpeak implements SpeakParent {

    @Override
    public void speak() {
        System.out.println("我不会说话！");
    }

}
