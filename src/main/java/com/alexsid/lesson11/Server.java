package com.alexsid.lesson11;

/**
 * 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу.
 * После чего начинает отправлять ему сообщения. Каждое сообщение сервер подписываем именем клиента и рассылает всем клиентам.
 * <p>
 * 2. Усовершенствовать задание 1:
 * a. добавить возможность отправки личных сообщений.
 * b. добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

    public static final int PORT = 12543;
    private static List<ServerThread> clients = new LinkedList<>(); //list of server threads related with each user

    public static List<ServerThread> getClients() {
        return clients;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = server.accept();//waiting for new user connected
                try {
                    clients.add(new ServerThread(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
