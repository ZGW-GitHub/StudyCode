/*
      Date:  2019-08-02 16:23
                                 */
package study.NIO.qq;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author NotUpToYou
 */
public class TestClient {

    @SuppressWarnings("all")
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
