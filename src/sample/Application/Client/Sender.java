//package sample.Application.Client;
//
//import sample.Application.Client.ChatEngine;
//
//import java.util.Scanner;
//
//public class Sender extends Thread {
//    private ChatEngine engine;
//    private Scanner consoleInput;
//
//    @Override
//    public void run() {
//        try {
//            while(true){
//                String msg = consoleInput.nextLine();
////                if(msg.equals("EXIT")){
////                    engine.close();
////                    System.exit(0);
//                }
//                engine.sendMessage(msg);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Sender(ChatEngine engine,Scanner cin) {
//        this.engine = engine;
//        this.consoleInput = cin;
//    }
//
//}
