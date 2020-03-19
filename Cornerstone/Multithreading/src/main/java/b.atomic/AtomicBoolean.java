package learn2_Atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBoolean {

    public static void main(String[] args) {

        java.util.concurrent.atomic.AtomicBoolean boo = new java.util.concurrent.atomic.AtomicBoolean(true);

        boo.lazySet(false);

        System.out.println(boo);
        System.out.println(boo);

    }

}
