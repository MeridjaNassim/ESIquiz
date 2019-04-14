package sample;

public class Compte {
    private String login;
    private String password;
    private Personne proprietaire;
    public Compte(String login,String password,Personne proprietaire) {
        this.login=login;
        this.password=password;
        this.proprietaire=proprietaire;
    }
}
