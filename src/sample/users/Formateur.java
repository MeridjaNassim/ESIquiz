package sample.users;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formateur extends Personne {
    private List<String> formationIds;

    public Formateur(String id, String name, String prenom, Date dateDeNaissance, String lieuDeNaissance, String adresse) {
        super(id, name, prenom, dateDeNaissance, lieuDeNaissance, adresse);
        formationIds = new ArrayList<>();
    }

    public Formateur(String id, String name, String prenom, Date dateDeNaissance, String lieuDeNaissance) {
        super(id, name, prenom, dateDeNaissance, lieuDeNaissance);
        this.formationIds = new ArrayList<>();
    }
    public void addFormation(String formationId){
        if(!hasFormation(formationId)){
            this.formationIds.add(formationId);
        }
    }
    public boolean hasFormation(String formationId){
        return this.formationIds.contains(formationId);
    }
    public void removeFormation( String formationId){
        if(this.hasFormation(formationId)){
            formationIds.remove(formationId);
        }
    }
}
