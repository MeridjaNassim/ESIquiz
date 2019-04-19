package sample.gestionnaires;

import sample.*;
import sample.utils.GeneratorID;

import java.util.Date;
import java.util.List;

public class GestionnaireFormation {

    private Formateur formateur ;
    public GestionnaireFormation(){
        init();
    }

    public Formation nouvelleFormation(String nom , Date debut , Date fin) {

        Formation formation = new Formation(GeneratorID.newFormationID(),nom,debut,fin,formateur);
        formateur.addFormation(formation.getId());
        return formation;
    }
    public void ajouterNotion(String formationID , Notion notion){
        for (Formation formation: ESIQuiz.getFormations()
             ) {
            if(formation.getId().equals(formationID) && formation.getFormateur().equals(formateur)) {
                formation.addNotion(notion);
            }
        }
    }
    public void ajouterNotions(String formationID , List<Notion> notions){
        for (Formation formation: ESIQuiz.getFormations()
                ) {
            if(formation.getId().equals(formationID) && formation.getFormateur().equals(formateur)) {
                for (Notion notion: notions
                     ) {
                    formation.addNotion(notion);
                }
            }
        }
    }
    public void retirerNotion(String formationID , String notionID){
        for (Formation formation: ESIQuiz.getFormations()
                ) {
            if(formation.getId().equals(formationID) && formation.getFormateur().equals(formateur)) {
                formation.removeNotion(notionID);
            }
        }
    }
    public void ajouterApprenant(String formationID , Apprenant apprenant) {
        for (Formation formation: ESIQuiz.getFormations()
             ) {
            if(formation.getId().equals(formationID) && formation.getFormateur().equals(formateur)){
                formation.addApprenant(apprenant);
            }
        }
    }
    public void retirerApprenant(String formationID , String apprenantID) {
        for (Formation formation: ESIQuiz.getFormations()
                ) {
            if(formation.getId().equals(formationID) && formation.getFormateur().equals(formateur)){
                formation.removeApprenant(apprenantID);
            }
        }
    }
    public void afficherApprenants(String formationID){
      Formation formation =getFormationByID(formationID);
        for (Apprenant ap: formation.getApprenants()
             ) {
            System.out.println(ap.toString());
        }

    }
    public Formation getFormationByID(String id){
       if(formateur.hasFormation(id)){
           for (Formation formation: ESIQuiz.getFormations()
                   ) {
               if(formation.getId().equals(id)){
                   return formation;
               }
           }
       }
        return null;
    }
    public void init(){
         formateur = (Formateur) ESIQuiz.getConnecte().getProprietaire();
    }
}
