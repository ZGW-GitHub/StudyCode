package com.code.thread.ss.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author NotUpToYou
 */
public class AtomicReferenceFieldUpdaterTest {

    public static void main(String[] args) {

        AtomicReferenceFieldUpdater<User, Integer> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class, Integer.class, "i");

        User user = new User();

        atomicReferenceFieldUpdater.set(user, 4);

        Integer get = atomicReferenceFieldUpdater.getAndSet(user, 3);

        System.out.println(get);
        System.out.println(user.toString());

    }

    private static class User {

        // 必须 volatile ，不能 private
        volatile Integer i;

        @Override
        public String toString() {
            return "User{" +
                    "i=" + i +
                    '}';
        }
    }

}
