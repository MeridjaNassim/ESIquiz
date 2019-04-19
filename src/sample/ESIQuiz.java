package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gestionnaires.GestionnaireApprenant;
import sample.gestionnaires.GestionnaireFormation;
import sample.utils.GenerateurCompte;
import sample.utils.GeneratorID;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

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
            System.out.println("Connecté au compte :"+connecte.getLogin()+"avec succés");
        }
        else
        {
            System.out.println("Echèc de connexion ,essayez a nouveau");
            return;
        }
        //la creation d'une nouvelle formation
        GestionnaireFormation gestForm1=new GestionnaireFormation();
        gestForm1.init();
        //
        GestionnaireApprenant gestApprenant=new GestionnaireApprenant();
        //
        Apprenant apprenant_1=new Apprenant(GeneratorID.newPersonID(Apprenant.class),"apprenant_1","Numero1",dateDeNaissance,"khenchela");
        Apprenant apprenant_2=new Apprenant(GeneratorID.newPersonID(Apprenant.class),"apprenant_2","Numero2",dateDeNaissance,"khenchela");
        Apprenant apprenant_3=new Apprenant(GeneratorID.newPersonID(Apprenant.class),"apprenant_3","Numero3",dateDeNaissance,"khenchela");
        //
        Compte compte_2=GenerateurCompte.generateCompte((Personne)apprenant_1);
        Compte compte_3=GenerateurCompte.generateCompte((Personne)apprenant_2);
        Compte compte_4=GenerateurCompte.generateCompte((Personne)apprenant_3);
        //
        comptes.add(compte_2);
        comptes.add(compte_3);
        comptes.add(compte_4);
        //l'ajout des apprenants a la formationq en cours(gestForm1)



        Formateur formateur1 = new Formateur(GeneratorID.newPersonID(Formateur.class),"nassim",null,null,null);
        Personne apprenant1 = new Apprenant(GeneratorID.newPersonID(Apprenant.class),"meridja","nassim",new Date(),null);
        comptes.add(new Compte("hello","world",null));
        System.out.println("connexion ... " + authentifier("hello","world"));
        System.out.println(formateur1.id);
        System.out.println(apprenant1.id);
        Question question = new QCM(GeneratorID.newQuestionID(QCM.class),"What time is it");
        System.out.println(question.getId());
        System.out.println("Compte connecté " + connecte.getLogin());
        GestionnaireApprenant gestionnaireApprenant = new GestionnaireApprenant();
        Compte c1 = gestionnaireApprenant.nouveauCompteApprenant(apprenant1);
        System.out.println(c1.getPassword());
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
