package com.a_snow.a_atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterTest {

    public static void main(String[] args) {

        AtomicReferenceFieldUpdater<User, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(User.class, Integer.class, "i");

        User user = new User();

        updater.set(user, 4);

        Integer get = updater.getAndSet(user, 3);

        System.out.println(get);
        System.out.println(user.toString());

    }

}

class User {

    // 必须 volatile ，不能 private
    volatile Integer i;

    @Override
    public String toString() {
        return "User{" +
                "i=" + i +
                '}';
    }
}
