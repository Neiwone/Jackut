package br.ufal.ic.p2.jackut;

import java.util.List;
import java.util.Set;

public class Facade {
    private System sys;

    public Facade() {
        sys = new System();
    }

    public void zerarSistema() {
        sys.clear();
    }

    public void criarUsuario(String login, String senha, String nome) throws Exception {
        sys.createUser_and_Add(login, senha, nome);
    }

    public String getAtributoUsuario(String login, String atributo) throws Exception {
        return sys.getAttribute(login, atributo);
    }

    public String abrirSessao(String login, String senha) throws Exception {
        return sys.login(login, senha);
    }

    public void encerrarSistema() {

    }

    public void editarPerfil(String id, String atributo, String valor) throws Exception {
        sys.changeUserInfo(id, atributo, valor);
    }

    public boolean ehAmigo(String login, String amigo) {
        return sys.isFriendOf(login, amigo);
    }

    public void adicionarAmigo(String id, String amigo) throws Exception {
        sys.setFriend(id, amigo);
    }

    public Set<String> getAmigos(String login) {
        return sys.getFriendsFromUser(login);
    }

    public void enviarRecado(String id, String destinatario, String recado) throws Exception {
        sys.sendMessage(id, destinatario, recado);
    }

    public String lerRecado(String id) throws Exception {
        return sys.readMessage(id).getMessage();
    }
}
