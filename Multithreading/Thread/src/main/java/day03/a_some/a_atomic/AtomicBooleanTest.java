package com.a_snow.a_atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {

    public static void main(String[] args) {

        AtomicBoolean boo = new AtomicBoolean(true);

        boo.lazySet(false);

        System.out.println(boo);
        System.out.println(boo);

    }

}
