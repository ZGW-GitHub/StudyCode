/*
      Date:  2019-08-08 10:18    
                                 */
package a_create;

public class A_CreateThread {
    public static void main(String[] args) {

        new Thread(()->{
            System.out.println("线程创建了。。。");
        }).start();

    }
}
