package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    public static void writeJsonToFile(String data, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
            System.out.println("Dados salvos no arquivo " + fileName);
        }
    }
}
