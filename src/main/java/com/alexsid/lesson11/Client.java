package main.java.com.alexsid.lesson11;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket = new Socket("localhost", 12543);
    private final InputStreamReader serverInput;
    private final OutputStreamWriter serverOutput;

    public Client() throws IOException {
        this.serverInput = new InputStreamReader(socket.getInputStream());
        this.serverOutput = new OutputStreamWriter(socket.getOutputStream());
    }

    public void start() throws IOException {
        String message;
        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(serverInput);
        while ((message = scanner.nextLine()) != ""){
            BufferedWriter bufferedWriter = new BufferedWriter(serverOutput);
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            System.out.println("Server response: " + bufferedReader.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

}
