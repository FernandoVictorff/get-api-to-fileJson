package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("A aplicação necessita dos parametros {apiUrl} {userApi} {passwordApi} {fileName}");
            System.exit(1);
        }

        final String API_URL = args[0];
        final String USERNAME = args[1];
        final String PASSWORD = args[2];
        final String FILE_NAME = args[3];

        try {
            String jsonResponse = performGetRequest(API_URL, USERNAME, PASSWORD);
            JsonWriter.writeJsonToFile(jsonResponse, FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String performGetRequest(String apiUrl, String username, String password) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String auth = username + ":" + password;
        String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOOperations.transfer(inputStream, out);
            return out.toString();
        } else {
            throw new IOException("Failed to perform GET request: " + responseCode);
        }
    }
}

