package com.alexsid.lesson11;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Client {

    public final String ipAddr = "localhost";
    public final int port = 12543;

    private Socket socket;
    private BufferedReader socketIn;
    private BufferedWriter socketOut;
    private Scanner consoleScanner;
    private String userName;

    public Client() {
        try {
            this.socket = new Socket(ipAddr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        if (socket != null) {
            try {
                consoleScanner = new Scanner(System.in);
                socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.enterName();
                new ServerListener().start();
                new ConsoleMessageReaderSender().start();
            } catch (IOException e) {
                Client.this.closeAll();//if constructor failed
            }
        }
        // closeAll later in runtime
    }

    private void enterName() {
        System.out.print("Введите ваше имя: ");
        try {
            userName = consoleScanner.nextLine();
            socketOut.write(userName + "\n");
            socketOut.flush();
        } catch (IOException ignored) {
        }
    }

    private void closeAll() {
        try {
            if ((socket != null) || (!socket.isClosed())) {
                socket.close();
                socketIn.close();
                socketOut.close();
            }
        } catch (IOException ignored) {
        }
    }

    private class ServerListener extends Thread {
        @Override
        public void run() {
            String incoming;
            try {
                while (true) {
                    incoming = socketIn.readLine();
                    if (incoming == null) {
                        Client.this.closeAll();
                        break;
                    }
                    System.out.println(incoming);
                }
            } catch (IOException e) {
                Client.this.closeAll();
            }
        }
    }

    public class ConsoleMessageReaderSender extends Thread {
        @Override
        public void run() {
            while (true) {
                String inputMessage;
                try {
                    inputMessage = consoleScanner.nextLine();
                    if (inputMessage.equals("quit")) {
                        socketOut.write("quit" + "\n");//report server that client is going off
                        Client.this.closeAll();
                        break;
                    } else {
                        socketOut.write(inputMessage + "\n");
                    }
                    socketOut.flush();
                } catch (IOException e) {
                    Client.this.closeAll();
                }
            }
        }
    }
}