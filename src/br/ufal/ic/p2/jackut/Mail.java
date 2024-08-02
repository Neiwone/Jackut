package br.ufal.ic.p2.jackut;

import java.util.LinkedList;
import java.util.Queue;

public class Mail {
    private String sender;
    private String message;

    public Mail(String _sender, String _message) {
        this.sender = _sender;
        this.message = _message;
    }

    public Mail() {

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
