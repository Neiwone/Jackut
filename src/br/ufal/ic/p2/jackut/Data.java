package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.models.User;

import java.util.LinkedHashMap;

public class Data {
    private static Data instance;
    private LinkedHashMap<String, User> users;

    private Data() {
        this.users = new LinkedHashMap<>();
    }

    public static Data getInstance() {
        if(instance == null)
            instance = new Data();

        return instance;
    }

    public User getUser(String login) {
        return users.get(login);
    }

    public void addUser(User user) {
        users.put(user.getLogin(), user);
    }

    public void removeAllUsers() {
        users.clear();
    }

    public boolean isAvailable(String login) {
        return !users.containsKey(login);
    }
}
