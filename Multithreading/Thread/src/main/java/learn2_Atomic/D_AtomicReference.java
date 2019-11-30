package learn2_Atomic;

import java.util.concurrent.atomic.AtomicReference;

public class D_AtomicReference {

    public static void main(String[] args) {

        Stu s1 = new Stu("A",1);
        Stu s2 = new Stu("A",2);

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
