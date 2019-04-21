package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gestionnaires.GestionnaireApprenant;
import sample.gestionnaires.GestionnaireFormation;
import sample.gestionnaires.GestionnaireQuiz;
import sample.utils.GenerateurCompte;
import sample.utils.GeneratorID;

import java.util.*;

public class ESIQuiz extends Application {

    private static final HashSet<Compte> comptes =new HashSet<>();
    private static final HashSet<Formation> formations = new HashSet<>();
    /// Gestionnaires ;
    private static Compte connecte ;

    public static Compte getConnecte() {
        return connecte;
    }

    public static HashSet<Formation> getFormations() {
        return formations;
    }

    public static HashSet<Compte> getComptes() {
        return comptes;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        Date dateDeNaissance = new GregorianCalendar(1999, Calendar.NOVEMBER, 13).getTime();
        Date dateDeFin = new GregorianCalendar(2020, Calendar.NOVEMBER, 13).getTime();
        //La generation des id des differents elements de l'application: formateurs ,apprenenats,.....
        GeneratorID.initIDs();
        //la creation d'un formateur
        Formateur formateur_1=new Formateur(GeneratorID.newPersonID(Formateur.class),"Benantar","Abdennour",dateDeNaissance,"Khenchela","Khenchela");
        // la creation du compte relatif au foramteur (formateur_1)
        Compte compte_1= GenerateurCompte.generateCompte((Personne)formateur_1);
        //on rajoute le compte crée a l'ensemble des comptes de l'application
        comptes.add(compte_1);
        //l'authentification de ce compte
        if(authentifier(compte_1.getLogin(),compte_1.getPassword()))
        {
            System.out.println("Connecté au compte :"+connecte.getLogin()+" avec succés");
        }
        else
        {
            System.out.println("Echèc de connexion ,essayez a nouveau");
            return;
        }
        //la creation d'une nouvelle formation
        GestionnaireFormation gestForm1=new GestionnaireFormation();
        gestForm1.init();
        Formation formation_1 = gestForm1.nouvelleFormation("formation_1", new Date(), dateDeFin);
        formation_1.setDescription("la 1ere formation de l'application");
        formations.add(formation_1);
        //
        GestionnaireApprenant gestApprenant=new GestionnaireApprenant();
        //
        Apprenant apprenant_1=new Apprenant(GeneratorID.newPersonID(Apprenant.class),"apprenant_1","Numero1",dateDeNaissance,"khenchela");
        Apprenant apprenant_2=new Apprenant(GeneratorID.newPersonID(Apprenant.class),"apprenant_2","Numero2",dateDeNaissance,"khenchela");
        Apprenant apprenant_3=new Apprenant(GeneratorID.newPersonID(Apprenant.class),"apprenant_3","Numero3",dateDeNaissance,"khenchela");
        Apprenant apprenant_4=new Apprenant(GeneratorID.newPersonID(Apprenant.class),"apprenant_4","Numero4",dateDeNaissance,"khenchela");
        //
        Compte compte_2=GenerateurCompte.generateCompte((Personne)apprenant_1);
        Compte compte_3=GenerateurCompte.generateCompte((Personne)apprenant_2);
        Compte compte_4=GenerateurCompte.generateCompte((Personne)apprenant_3);
        Compte compte_5=GenerateurCompte.generateCompte((Personne)apprenant_4);
        //
        comptes.add(compte_2);
        comptes.add(compte_3);
        comptes.add(compte_4);
        comptes.add(compte_5);
        //l'ajout des apprenants a la formations en cours(gestForm1)
        gestForm1.ajouterApprenant(formation_1.getId(),apprenant_1);
        gestForm1.ajouterApprenant(formation_1.getId(),apprenant_2);
        gestForm1.ajouterApprenant(formation_1.getId(),apprenant_3);
        gestForm1.ajouterApprenant(formation_1.getId(),apprenant_4);
        System.out.println("------------ Affichage des apprenants ---------------");
        gestForm1.afficherApprenants(formation_1.getId());
        //La suppression de l'apprenaant (apprenant_3)
        gestForm1.retirerApprenant(formation_1.getId(),apprenant_3.id);
        System.out.println("------------ Affichage des apprenants apres suppression de (apprenant_3) ---------------");
        gestForm1.afficherApprenants(formation_1.getId());
        //La Mise A jour des coordonés de l'apprenant (apprenant_2)
        System.out.println("------------ Affichage des apprenants la modification du nom de (apprenant_2) ---------------");
        apprenant_2.setName("apprenant_2_nouveau_nom");
        gestForm1.afficherApprenants(formation_1.getId());
        //Creations Des Notions
        Notion notion_1=new Notion(GeneratorID.newNotionID(),"Notion_1");
        Notion notion_2=new Notion(GeneratorID.newNotionID(),"notion_2");
        //l'ajout des notions a la formation
        gestForm1.ajouterNotion(formation_1.getId(),notion_1);
        gestForm1.ajouterNotion(formation_1.getId(),notion_2);
        //Creation des questions + l'ajout des propositions
        Question q1=new QCM(GeneratorID.newQuestionID(QCM.class),"Enoncé de la question 1");
        q1.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),true,"proposition 1 question 1"));
        q1.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),false,"proposition 2 question 1"));
        notion_1.AjouterQuestion(q1);
        Question q2=new QCU(GeneratorID.newQuestionID(QCU.class),"Enoncé de la question 2");
        q2.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),true,"proposition 1 question 2"));
        q2.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),false,"proposition 2 question 2"));
        notion_1.AjouterQuestion(q2);
        Question q3=new QCU(GeneratorID.newQuestionID(QCU.class),"Enoncé de la question 3");
        q3.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),true,"proposition 1 question 3"));
        q3.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),false,"proposition 2 question 3"));
        notion_2.AjouterQuestion(q3);
        Question q4=new QO(GeneratorID.newQuestionID(QO.class),"Enoncé de la question 4");
        q4.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),true,"proposition 1 question 4"));
        q4.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(),false,"proposition 2 question 4"));
        notion_2.AjouterQuestion(q4);
        //l'ajout des notions au gestionnaire de formations
        gestForm1.ajouterNotion(formation_1.getId(),notion_1);
        gestForm1.ajouterNotion(formation_1.getId(),notion_2);
        //
        HashMap<Notion,Integer> notions = new HashMap<>();
        notions.put(notion_1,2);
        notions.put(notion_2,2);
        GestionnaireQuiz gestquiz=new GestionnaireQuiz();
        gestquiz.setFormation(formation_1);
        Quiz quiz1=gestquiz.nouveauQuiz(notions);
        quiz1.setExpirationDate(dateDeFin);
        quiz1.setNomQuiz("quiz1");
        quiz1.setOuvertureDate(new Date());
        System.out.println("-------------- Affichage du quiz ---------------");
        gestquiz.afficheQuiz(quiz1);
        return;
    }
     static boolean authentifier(String username , String password){
        Compte temp = new Compte(username,password,null);
        for (Compte compte: comptes) {
            if(compte.equals(temp)){
                /// compte existe
                connecte = compte;
                return true ;
            }
        }
        return false;
    }
    static void listFormations(){
        for (Formation formation: formations
             ) {
            System.out.println("Formation ID : " + formation.getId());
            System.out.println("Formation Name : " + formation.getNom());
            System.out.println("Formation Formateur name : " + formation.getFormateur().getName());
            System.out.println("Formation description : " +formation.getDescription());
        }
    }
}
