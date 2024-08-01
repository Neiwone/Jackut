package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.models.User;

public class System {
    private final Data database;
    public User current_user;

    public System() {
        this.database = Data.getInstance();
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
        new_user.addInfo("password", senha);
        new_user.addInfo("name", nome);

        database.addUser(new_user);
    }

    public String getAttribute(String login, String attribute) throws Exception {
        User user = database.getUser(login);
        if(user == null || attribute == null) throw new Exception("Usu?rio n?o cadastrado.");

        return switch (attribute) {
            case "nome" -> user.getNome();
            case null, default -> throw new IllegalStateException("Unexpected value: " + attribute);
        };
    }

    public void login(String login, String senha) throws Exception {
        User user = database.getUser(login);
        if(user == null || senha == null) throw new Exception("Login ou senha inv?lidos.");

        if(user.checkPassword(senha)) current_user = user;
        else throw new Exception("Login ou senha inv?lidos.");
    }

}
