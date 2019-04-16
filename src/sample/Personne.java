package sample;

import java.util.Date;

public abstract class Personne {
    protected final String id;
    protected String name;
    protected String prenom;
    protected Date dateDeNaissance;
    protected String lieuDeNaissance;
    protected String adresse;

    public Personne(String id, String name, String prenom, Date dateDeNaissance, String lieuDeNaissance, String adresse) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.adresse = adresse;
    }

    public Personne(String id, String name, String prenom, Date dateDeNaissance, String lieuDeNaissance) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getId() {
        return id;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof Personne) {
            return this.id.equals(((Personne) obj).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }
}
