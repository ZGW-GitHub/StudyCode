package p.ce.lue.speak;

/**
 * 表现2：会说话
 *
 * @author 愆凡
 */
public class CanSpeak implements SpeakParent {

    @Override
    public void speak() {
        System.out.println("嘎嘎嘎！");
    }

}
