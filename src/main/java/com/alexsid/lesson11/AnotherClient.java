package main.java.com.alexsid.lesson11;

import java.io.IOException;

public class AnotherClient {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }
}
