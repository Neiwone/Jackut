package br.ufal.ic.p2.jackut.models;

import java.util.LinkedHashMap;
import java.util.UUID;

public class User {
    private String ID;
    private LinkedHashMap<String, String> user_info;

    public User() {
        this.user_info = new LinkedHashMap<>();
        this.ID = UUID.randomUUID().toString();
    }

    public String getID() {
        return ID;
    }

    public void addInfo(String info, String data) {
        user_info.put(info, data);
    }

    public String getNome() {
        return this.user_info.get("name");
    }

    public boolean checkPassword(String _password) {
        return user_info.get("password").equals(_password);
    }

    public String getLogin() {
        return this.user_info.get("login");
    }
}
