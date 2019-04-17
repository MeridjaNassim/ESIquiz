package sample.utils;

import sample.Compte;
import sample.Personne;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class GenerateurCompte {

    private GenerateurCompte(){

    }
    public static Compte generateCompte(Personne personne){
        String login = personne.getPrenom().substring(0,personne.getPrenom().length()/2).concat("_").concat(personne.getName()).toLowerCase();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String password = personne.getName().concat(format.format(personne.getDateDeNaissance()));
        Compte compte = new Compte(login,password,personne);
        return compte;
    }

}
