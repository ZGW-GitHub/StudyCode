package com.code.thread.ss.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 愆凡
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {

        Stu s1 = new Stu("Aa",1);
        Stu s2 = new Stu("Aa",2);

        AtomicReference<Stu> re = new AtomicReference<Stu>(s1);

        re.compareAndSet(s1, s2);

        System.out.println(re.get().toString());

    }

    private static class Stu {

        private String name;
        private Integer age;

        public Stu(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Stu{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
