/*
      Date:  2019-08-08 10:22    
                                 */
package a_create;

import java.io.IOException;

/**
 * 创建一个进程
 */
public class B_CreateQuestion {

    public static void main(String[] args) throws IOException {

        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd /k start http://www.google.com");
        process.exitValue();

    }

}
