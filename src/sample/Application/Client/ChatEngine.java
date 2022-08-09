//package sample.Application.Client;
//
//import java.io.IOException;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class ChatEngine {
//    private final Scanner input;
//    private final PrintStream output;
//
//    public ChatEngine(String add, int port) throws IOException {
//        Socket socket = new Socket(add, port);
//        input = new Scanner(socket.getInputStream());
//        output = new PrintStream(socket.getOutputStream());
//    }
//
//    public void sendMessage(String msg){
//        synchronized (output){
//            output.println(msg);
//            output.flush();
//        }
//    }
//    public String readMessage(){
//        synchronized (input){
//            return input.nextLine();
//        }
//    }
//    public void close(){
//        output.close();
//        input.close();
//    }
//}
