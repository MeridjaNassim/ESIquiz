package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.gestionnaires.GestionnaireApprenant;
import sample.utils.GenerateurCompte;
import sample.utils.GeneratorID;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

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
        //launch(args);
        GeneratorID.initIDs();
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
