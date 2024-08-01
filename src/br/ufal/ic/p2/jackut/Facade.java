package br.ufal.ic.p2.jackut;

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
        sys.login(login, senha);
        return sys.current_user.getID();
    }

    public void encerrarSistema() {

    }

    public void editarPerfil(String id, String atributo, String valor) {

    }
}
