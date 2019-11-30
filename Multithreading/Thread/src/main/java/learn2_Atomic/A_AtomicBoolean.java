package learn2_Atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class A_AtomicBoolean {

    public static void main(String[] args) {

        AtomicBoolean boo = new AtomicBoolean(true);

        boo.lazySet(false);

        System.out.println(boo);
        System.out.println(boo);

    }

}
