/*
      Date:  2019-08-06 7:10
                                 */
package uu.container;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * round : 四舍五入
 * ceiling : 向上取
 * floor : 向下取
 */
// TreeMap 的高并发实现
public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {

        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();

        map.put(1, "java");
        map.put(5, "C++");
        map.put(10, "C");

        System.out.println(map.ceilingKey(3)); // 5
        System.out.println(map.ceilingEntry(3).getValue()); // C++

        // merge ：合并
        String valueChange = map.merge(1, "Python", (oldv, v) -> {
            System.out.println(oldv); // java
            System.out.println(v); // Python
            return oldv + " " + v;
        });
        System.out.println(valueChange); // java Python
        System.out.println(map.get(1)); // java Python

        // compute ：计算、估算
        String compute = map.compute(5, (k, v) -> {
            System.out.println(k); // 5
            System.out.println(v); // C++
            return "hello";
        });
        System.out.println(compute); // hello
        System.out.println(map.get(5)); // hello

    }

}
