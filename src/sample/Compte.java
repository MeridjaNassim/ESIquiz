package sample;

public class Compte {
    /// <summary>
    /// Cette class représente un Compte dans l'application elle encapsule le login(username) , le mot de passe , et la personne propriétaire du compte
    /// qui peut etre soit un formateur soit un apprenant .
    ///</summary>

    ///Constants
    private static String DEFAULT = "NONE";
    ///
    private String login;
    private String password;
    private Personne proprietaire;
    public Compte(){
        this.login=DEFAULT;
        this.password =DEFAULT;
    }
    public Compte(String login,String password,Personne proprietaire) {
        this.login=login;
        this.password=password;
        this.proprietaire=proprietaire;
    }
    public void modifierLogin(String nouveauLogin , String password) {
        if(this.password.equals(DEFAULT)){
            this.login = nouveauLogin;
            return;
        }
        if(this.password.equals(password)) {
            this.login = nouveauLogin;
        }
    }
    public void modifierPassword(String nouveauPassword,String oldPassword){
        if(this.password.equals(DEFAULT)){
            this.password = nouveauPassword;
            return;
        }

        if(this.password.equals(oldPassword) ){
            this.password = nouveauPassword;
        }
    }
    public void supprimerCompte(){
        this.password = DEFAULT;
        this.login=DEFAULT;
        this.proprietaire = null;
    }

    public String getLogin() {
        return login;
    }

    public Personne getProprietaire() {
        return proprietaire;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Compte) {
            return (this.login.equals(((Compte) obj).login) && this.password.equals(((Compte) obj).password));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }
}