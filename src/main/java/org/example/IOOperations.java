package org.example;

import java.io.*;

public class IOOperations {

    public static void transfer(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[2048];

        while (true) {
            int bytesRead = in.read(buffer);

            if (bytesRead < 0) {
                break;
            }

            out.write(buffer, 0, bytesRead);
        }
    }
}
