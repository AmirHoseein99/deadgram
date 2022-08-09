package sample.Server;

import sample.Application.Client.MessageType;
import sample.Application.Controllers.MainPageController;
import sample.Application.Moudels.Message;
import sample.Application.Moudels.UsedUserNameException;

import sample.Application.Moudels.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;


public class Server {
    static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket( 8080 )) {
            while (true) {

                new Thread( new Handler( serverSocket.accept() ) ).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Server.users = users;
    }
}

class Handler implements Runnable {
    private MainPageController mainPageController;
    private Socket socket;


    public Handler instance;
    private static ArrayList<Message> messages = new ArrayList<>();
    private ObjectInputStream input;
    private OutputStream serverOutputStream;
    private ObjectOutputStream output;
    private InputStream serverInputStream;
    Message inputMessage;
    static Set<Socket> members = new HashSet<>();
    static Map<ObjectOutputStream, User> objectUserMap = new HashMap<>();


    static Set<ObjectOutputStream> writers = new HashSet<>();
    static Set<ObjectInputStream> readers = new HashSet<>();

    public Handler(Socket socket) throws IOException {
        this.socket = socket;
        mainPageController = MainPageController.getInstance();
        instance = this;
    }

    @Override
    public void run() {

        try {
            serverInputStream = socket.getInputStream();
            serverOutputStream = socket.getOutputStream();

            output = new ObjectOutputStream( serverOutputStream );
            input = new ObjectInputStream( serverInputStream );

            readers.add( input );
            writers.add( output );
            members.add( socket );

            inputMessage = (Message) input.readObject();
            objectUserMap.put( output, inputMessage.getSender() );

            if (Server.users.size() == 0) {
                Server.getUsers().add( inputMessage.getSender() );
                inputMessage.setUsers( Server.getUsers() );
            } else {
                checkUsedUsername( inputMessage );
                inputMessage.setUsers( Server.getUsers() );
            }
            System.out.println( Server.getUsers().size() );

            writeJoinedMessage();

            while (socket.isConnected()) {
                inputMessage = (Message) input.readObject();
                if (inputMessage != null) {
                    switch (inputMessage.getType()) {
                        case PRIVATECHAT:
                            privateChatWrite( inputMessage );
                            break;
                        case JOINED:
                            writeJoinedMessage();
                            break;
                        case CHANGEUSERPIC:
                            changeUsersProfilePic( inputMessage.getSender() );
                            break;
                        case CHANGEUSERNAME:
                            changeUsersName( inputMessage.getSender() );
                            break;

                    }
                }
            }
        } catch (SocketException socketException) {

        } catch (Exception e) {

        } finally {

        }
    }

    private void checkUsedUsername(Message msg) throws UsedUserNameException {
        for (User user: Server.getUsers()) {
            if (user.getUserName().equals( msg.getSender().getUserName() )) {
                throw new UsedUserNameException( msg.getSender() + " is already connected" );

            } else {
                Server.getUsers().add( inputMessage.getSender() );
                break;
            }
        }
    }

    public void write(Message message) throws IOException {
        for (ObjectOutputStream writer: writers) {
            writer.writeObject( message );
            writer.reset();
        }

    }

    public void privateChatWrite(Message message) throws IOException {
        for (ObjectOutputStream o: objectUserMap.keySet()) {
            if (objectUserMap.get( o ).getUserName().equals( message.getReceiver().getUserName() )) {
                o.writeObject( message );
                o.flush();
                o.reset();

            }
        }
    }

    public void writeJoinedMessage() throws IOException {
        Message msg = new Message();
        msg.setType( MessageType.JOINED );
        msg.setUsers( Server.getUsers() );
        write( msg );

    }

    public void changeUsersProfilePic(User user) throws IOException {
        for (User u: Server.getUsers()) {
            if (u.getUserName().equals( user.getUserName() )) {
                u.setUserProfilePic( user.getUserProfilePic() );
                writeJoinedMessage();
            }
        }

    }

    public void changeUsersName(User user)  throws IOException {
        for (User u: Server.getUsers()) {
            if (u.getUserProfilePic().equals( user.userProfilePic )) {
                u.setUserName( user.getUserName() );
                writeJoinedMessage();
            }
        }

    }
}

