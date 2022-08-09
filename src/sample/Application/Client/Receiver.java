//package sample.Application.Client;
//
//import sample.Application.Client.ChatEngine;
//
//import java.io.IOException;
//import java.util.NoSuchElementException;
//
//public class Receiver extends Thread {
//    private ChatEngine engine;
//
//    public Receiver(ChatEngine engine) {
//        this.engine = engine;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                System.out.println(engine.readMessage());
//            }
//        } catch (NoSuchElementException e) {
//            System.out.println("Server Closed");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.exit(0);
//    }
//}
