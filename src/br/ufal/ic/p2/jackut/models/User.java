package br.ufal.ic.p2.jackut.models;

import java.util.*;

public class User {
    private LinkedHashMap<String, String> user_info;
    private Set<String> friend_list;
    private Set<String> friend_request;

    public User() {
        this.user_info = new LinkedHashMap<>();
        this.friend_list = new HashSet<>();
        this.friend_request = new HashSet<>();
    }

    public String getInfo(String info) throws Exception {
        if(this.user_info.containsKey(info))
            return this.user_info.get(info);
        else
            throw new Exception("Atributo n?o preenchido.");
    }

    public void addInfo(String info, String data) {
        user_info.put(info, data);
    }

    public boolean checkPassword(String _password) {
        return user_info.get("senha").equals(_password);
    }

    public void changeInfo(String info, String data) throws Exception {
        if(info.isEmpty()) throw new Exception("Atributo n?o existe.");
        this.user_info.put(info, data);
    }

    public void addFriend(String friend_login) throws Exception {
        if(friend_list.contains(friend_login)) throw new Exception("Usu?rio j? est? adicionado como amigo.");

        friend_list.add(friend_login);
    }

    public void removeFriend(String friend_login) {
        friend_list.remove(friend_login);
    }

    public boolean isFriend(String friend_login) {
        return friend_list.contains(friend_login);
    }

    public List<String> getFriends() {
        return friend_list.stream().toList();
    }

    public boolean wasRequested(String friend_login) {
        return friend_request.contains(friend_login);
    }

    public void removeRequest(String friend_login) {
        friend_request.remove(friend_login);
    }

    public void addRequest(String friend_login) {
        friend_request.add(friend_login);
    }
}
