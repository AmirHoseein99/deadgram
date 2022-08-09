import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class Server implements ServerInterface {
    private ServerSocket serverSocket;

    @Override
    public void startServer() {
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                Socket s = serverSocket.accept();
                new Thread(new ServerImp(s)).start();
                ServerSender.addClient(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restartServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            startServer();
        }
    }
}

class ServerImp implements Runnable {
    private Socket socket;
    private String name;

    private void initName() {
        try {
            name = new Scanner(socket.getInputStream()).nextLine();
            ServerSender.sendMessage(socket, name + " Joined");
        } catch (IOException e) {
            System.out.println("Exception in initName method");
        }
    }

    ServerImp(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        initName();
        Scanner input = null;
        try {
            input = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Exception in ServerImp");
        }
        while (true) {
            try {
                assert input != null;
                String msg = input.nextLine();
                ServerSender.sendMessage(socket, name + ": " + msg);
            } catch (NoSuchElementException e) {
                ServerSender.removeClient(socket);
                ServerSender.sendMessage(null, name + " Left");
                break;
            }
        }
    }
}

class ServerSender {
    private static Queue<Socket> clients = new ConcurrentLinkedQueue<>();

    private ServerSender() {
    }

    static void addClient(Socket s) {
        clients.add(s);
    }

    static void removeClient(Socket s) {
        clients.remove(s);
    }

    static void sendMessage(Socket from, String msg) {
        for (Socket s : clients) {
            if (s != from) {
                try {
                    new PrintStream(s.getOutputStream(), true).println(msg);
                } catch (IOException e) {
                    System.out.println("Exception in send message");
                }
            }
        }
    }
}