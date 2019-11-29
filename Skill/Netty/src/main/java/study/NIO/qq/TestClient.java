/*
      Date:  2019-08-02 16:23    
                                 */
package com.snow.study.NIO.qq;

import java.io.IOException;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Client client = new Client("小明");

        new Thread(()->{
            while (true) {
                try {
                    client.getMsg();
                    Thread.sleep(2_000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            client.setMsg(msg);
        }

    }

}
