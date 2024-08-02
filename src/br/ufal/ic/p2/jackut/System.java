package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class System {
    private final Data database;
    public HashMap<String, User> current_users;

    public System() {
        this.database = Data.getInstance();
        this.current_users = new HashMap<>();
    }

    public void clear() {
        database.removeAllUsers();
    }

    public void createUser_and_Add(String login, String senha, String nome) throws Exception {
        if(login == null) throw new Exception("Login inv?lido.");
        if(senha == null) throw new Exception("Senha inv?lida.");
        if(nome == null) throw new Exception("Nome inv?lido.");
        if(!database.isAvailable(login)) throw new Exception("Conta com esse nome j? existe.");

        User new_user = new User();
        new_user.addInfo("login", login);
        new_user.addInfo("senha", senha);
        new_user.addInfo("nome", nome);

        database.addUser(new_user);
    }

    public String getAttribute(String login, String attribute) throws Exception {
        User user = database.getUser(login);
        if(user == null || attribute == null) throw new Exception("Usu?rio n?o cadastrado.");

        return user.getInfo(attribute);
    }

    public String login(String login, String senha) throws Exception {
        User user = database.getUser(login);
        if(user == null || senha == null) throw new Exception("Login ou senha inv?lidos.");

        // if the password is correct, open a section with that user
        if(user.checkPassword(senha)) {
            String user_token = UUID.randomUUID().toString();
            current_users.put(user_token, user);
            return user_token;
        }
        else throw new Exception("Login ou senha inv?lidos.");
    }

    public void changeUserInfo(String user_token, String info, String data) throws Exception {
        if(user_token.isEmpty()) throw new Exception("Usu?rio n?o cadastrado.");

        User user = this.current_users.get(user_token);
        user.changeInfo(info, data);
    }

    public boolean isFriendOf(String user1_login, String user2_login) {
        return database.getUser(user1_login).isFriend(user2_login);
    }

    public void setFriend(String user1_token, String user2_login) throws Exception {
        User user1 = this.current_users.get(user1_token);
        User user2 = this.database.getUser(user2_login);
        if(user2 == null || user1 == null) throw new Exception("Usu?rio n?o cadastrado.");
        if(user1 == user2) throw new Exception("Usu?rio n?o pode adicionar a si mesmo como amigo.");
        if(user1.isFriend(user2_login)) throw new Exception("Usu?rio j? est? adicionado como amigo.");
        if(user2.wasRequested(user1.getInfo("login"))) throw new Exception("Usu?rio j? est? adicionado como amigo, esperando aceita??o do convite.");

        if (user1.wasRequested(user2_login)) {
            user1.removeRequest(user2_login);
            user1.addFriend(user2_login);
            user2.addFriend(user1.getInfo("login"));
        }
        else {
            user2.addRequest(user1.getInfo("login"));
        }

    }

    public Set<String> getFriendsFromUser(String login) {
        return this.database.getUser(login).getFriends();
    }

    public void sendMessage(String user1_token, String user2_login, String message) throws Exception {
        User user1 = current_users.get(user1_token);
        User user2 = database.getUser(user2_login);

        if(user1 == null || user2 == null) throw new Exception("Usu?rio n?o cadastrado.");
        if(user1 == user2) throw new Exception("Usu?rio n?o pode enviar recado para si mesmo.");

        Mail m = new Mail(user1.getInfo("login"), message);
        user2.pushMail(m);
    }

    public Mail readMessage(String user_token) throws Exception {
        return current_users.get(user_token).popMail();
    }
}
