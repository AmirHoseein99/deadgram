package sample.Application.Client;

import sample.Application.Controllers.MainPageController;
import sample.Application.Moudels.Group;
import sample.Application.Moudels.Message;
import sample.Application.Moudels.User;

import java.io.*;
import java.net.Socket;
import java.nio.channels.GatheringByteChannel;
import java.util.Scanner;

public class Chat implements Runnable {
    static String userName;
    static String pic;
    static String password;
    private InputStream input;
    private OutputStream output;
    private ObjectInputStream ois;
    private static ObjectOutputStream oos;
    public MainPageController mainPageController;
    public User user;
    public Group group;
    public static Chat instance;

    public static Chat getInstance() {
        return instance;
    }


    public Chat(User user, MainPageController mainPageController) {
        this.user = user;
        userName = user.getUserName();
        pic = user.getUserProfilePic();
        password = user.getPassword();
        this.mainPageController = mainPageController;
        instance = this;
    }

    public Chat(Group group, MainPageController mainPageController) {
        this.group = group;
        this.mainPageController = mainPageController;
        instance = this;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket( "localhost", 8080 )) {

            input = socket.getInputStream();
            output = socket.getOutputStream();
            oos = new ObjectOutputStream( output );
            ois = new ObjectInputStream( input );
            if (group == null)
                sendJoinedMessage();
            else
                createGroupMessage();
            try {
                while (socket.isConnected()) {
                    Message message = null;
                    message = (Message) ois.readObject();
                    if (message != null) {

                        switch (message.getType()) {
                            case JOINED:
                                mainPageController.addToUsersList( message );
                                break;
                            case PRIVATECHAT:
                                mainPageController.addMessageToChat( message );
                                break;
                            case GROUPCHAT:
                                mainPageController.addToGroupChat( message );
                                break;

                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                mainPageController.logoutScene();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createGroupMessage() {
    }

    public static void send(String msg, String sender, String receiver) throws IOException {

        Message newMessage = new Message();
        newMessage.setReceiver( new User( receiver ) );
        newMessage.setType( MessageType.PRIVATECHAT );
        newMessage.setSender( new User( sender ) );
        newMessage.setMessage( msg );
        oos.writeObject( newMessage );
        oos.flush();
    }

    public void sendJoinedMessage() throws IOException {
        Message newMessage = new Message();
        newMessage.setSender( user );
        newMessage.setType( MessageType.JOINED );
        newMessage.setStatus( Status.AWAY );
        newMessage.setMessage( null );
        oos.writeObject( newMessage );
        oos.flush();
    }

    public static void changeUserPic(User user) throws IOException {
        Message message = new Message();
        message.setType( MessageType.CHANGEUSERPIC );
        message.setSender( user );
        oos.writeObject( message );
        oos.flush();
    }
    public static void changeUserName(User user) throws IOException {
        Message message = new Message();
        message.setType( MessageType.CHANGEUSERNAME );
        message.setSender( user );
        oos.writeObject( message );
        oos.flush();
    }

}
