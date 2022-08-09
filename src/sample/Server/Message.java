package sample.Application.Moudels;

import javafx.animation.Animation;
import javafx.scene.media.MediaPlayer;
import sample.Application.Client.MessageType;
import sample.Application.Client.Status;

import java.awt.*;
import java.io.ObjectInputFilter;
import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    private String message;
    private int onlineContacts;
    private MessageType type;
    private Status status;
    private User receiver;
    private User sender;
    private ArrayList<User> users = new ArrayList<>();

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User reciver) {
        this.receiver = reciver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOnlineContacts() {
        return onlineContacts;
    }

    public void setOnlineContacts(int onlineContacs) {
        this.onlineContacts = onlineContacs;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


}
