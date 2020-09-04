package main.java.com.alexsid.lesson11;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws IOException {
        final int PORT = 12543;
        ServerSocket serverSocket;

        serverSocket = new ServerSocket(PORT);
        System.out.println("server started \n waiting for connection");
        Socket clientSocket = serverSocket.accept();
        System.out.println("connection done");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        String message = null;
        while ((message = in.readLine()) != null){
            System.out.println(message);
            out.write("received message " + message + "\n");
            out.flush();
        }


    }
}

