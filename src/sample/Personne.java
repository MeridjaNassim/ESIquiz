package sample;

import java.util.Date;

public abstract class Personne {
    protected final String id;
    protected String name;
    protected String prenom;
    protected Date dateDeNaissance;
    protected String lieuDeNaissance;
    protected String adresse;
    public Personne(String id) {
        this.id = id;
    }
}
