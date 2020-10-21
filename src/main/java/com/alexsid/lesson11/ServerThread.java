package com.alexsid.lesson11;

import java.io.*;
import java.net.Socket;

class ServerThread extends Thread {

    private final Socket clientSocket;
    private final BufferedReader in;
    private final BufferedWriter out;

    public String getUserName() {
        return userName;
    }

    private String userName;

    public ServerThread(Socket socket) throws IOException {
        this.clientSocket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String message;
        try {
            message = in.readLine();//processing nickname
            userName = message;
            out.write("Hello " + userName + "\n");//processing nickname
            sendToAll("User " + userName + " joined\n");
            out.flush();//processing nickname
            while (true) {
                message = in.readLine();
                if (message == null) {
                    this.downUserThread();
                    break;
                }
                if (message.isEmpty()) continue;
                System.out.println("Incoming Message: " + userName + ": " + message);//server inner logging
                if (message.startsWith("@")) {// start with "@username " sends message to Username
                    System.out.println("personal message " + message);
                    String userName = getUserNameOfString(message);
                    Server.getClients().stream()
                            .filter(c -> c.getUserName().toLowerCase().equals(userName))
                            .findFirst()
                            .get()
                            .send("Personal from " + this.userName + ": " + message);
                    this.send("Personal to " + message);
                    continue;
                }
                sendToAll(userName + ": " + message);
            }
        } catch (IOException e) {
            this.downUserThread();
        }
    }

    private String getUserNameOfString(String message) {
        String s = message.split(" ")[0].substring(1).toLowerCase();
        System.out.println(s);
        return s;
    }

    private void sendToAll(String message) {
        Server.getClients().forEach(c -> c.send(message)); //send message to all users
    }

    public void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    private void downUserThread() {
        try {
            if (!clientSocket.isClosed()) {
                sendToAll("user "+this.userName+" leaving the chat");
                clientSocket.close();
                in.close();
                out.close();
                this.interrupt();
            }
        } catch (IOException ignored) {
        }finally {
            Server.getClients().remove(this);
            System.out.println("user "+this.userName+" left the chat");
        }
    }
}
