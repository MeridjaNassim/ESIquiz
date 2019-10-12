package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Common.Formation;
import sample.Common.Notion;
import sample.gestionnaires.GestionnaireFormation;
import sample.gestionnaires.GestionnaireQuiz;
import sample.quiz.*;
import sample.users.Compte;
import sample.users.Formateur;
import sample.utils.DateUtils;
import sample.utils.GenerateurCompte;
import sample.utils.GeneratorID;

import java.util.*;

public class ESIQuiz extends Application {
    static Formateur formateur;
    static Compte compte;
    public static HBox page;
    public static Pane quizzes ;
    public static Pane sidePanel;
    public static Stage stage1 ;

    public static final HashSet<Formation> formations = new HashSet<>();
    GestionnaireFormation gestForm1;
    public static  GestionnaireQuiz gp = new GestionnaireQuiz();
    public static Formateur getFormateur() {
        return formateur;
    }
    public static List<String> getFormationNames(){
        ArrayList<String> list = new ArrayList<>();
        for (Formation fo: formations
             ) {
            list.add(fo.getNom());
        }
        return list;
    }
    public static List<String> getNotionsNames(String formationName){
        ArrayList<String> list = new ArrayList<>();
        for (Formation formation: formations
                ) {

            if(formation.getNom().equals(formationName)){

                for (Notion n: formation.getNotions()
                     ) {
                    list.add(n.getName());
                }
                break;
            }
        }
        return list;
    }
    public static Notion getNotionByName(String formationName,String notionName){
        for (Formation formation: formations
                ) {

            if(formation.getNom().equals(formationName)){

                for (Notion n: formation.getNotions()
                        ) {
                    if(n.getName().equals(notionName)){
                        return n;
                    }
                }
                break;
            }
        }
        return null;
    }
    public static Quiz generateQuiz(String formationName,String nom ,Date debut , Date fin,HashMap<Notion,Integer> notionsUtilise){
        for (Formation forma: formations
             ) {
            if(forma.getNom().equals(formationName)){
                gp.setFormation(forma);
               Quiz c = gp.nouveauQuiz(notionsUtilise);
               c.setNomQuiz(nom);
               c.setOuvertureDate(debut);
               c.setExpirationDate(fin);
               return c;
            }
        }
        return null;
    }
    public static void changeScene(Scene scene){
        stage1.setScene(scene);
    }
    public static Scene currentScene(){
        return stage1.getScene();
    }
    private static final HashSet<Compte> comptes = new HashSet<>();
    public static Compte connecte;

    public static boolean authentifier(String username, String password) {
        Compte temp = new Compte(username, password, null);
        for (Compte compte : comptes) {
            if (compte.equals(temp)) {
                /// compte existe
                connecte = compte;
                return true;
            }
        }
        return false;
    }
    public static String userName;
    public static String pass;

    @Override
    public void start(Stage stage) throws Exception {
        formateur= new Formateur(GeneratorID.newPersonID(Formateur.class), "Meridja", "Nassim", DateUtils.getDate(1999,Calendar.JULY,21), "Alger", "Alger");
        compte = GenerateurCompte.generateCompte(formateur);
        System.out.println("login == " +compte.getLogin());
        System.out.println("password== "+compte.getPassword());
        comptes.add(compte);
        /// formation précréer ;
        gestForm1 = new GestionnaireFormation(formateur);

        Formation formation_1 = gestForm1.nouvelleFormation("Introduction au machine learning", new Date(), DateUtils.getDate(2020,Calendar.MAY,30));
        formation_1.setDescription("Introduction au machine learning");
        formations.add(formation_1);
        Formation formation_2 = gestForm1.nouvelleFormation("Programmation orientée objets", new Date(), DateUtils.getDate(2020,Calendar.MAY,30));
        formation_1.setDescription("Programmation orientée objets");
        formations.add(formation_2);
        Notion notion_1 = new Notion(GeneratorID.newNotionID(), "JavaFX");
        Notion notion_2 = new Notion(GeneratorID.newNotionID(), "Streams");
        Question q1 = new QCM(GeneratorID.newQuestionID(QCM.class), "Séléctionnez les réponses correctes ");
        q1.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), true, "JavaFX est un framework d'interface graphique"));
        q1.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), false, "JavaFX utilise que le code pour programmer"));
        notion_1.AjouterQuestion(q1);
        Question q2 = new QCU(GeneratorID.newQuestionID(QCU.class), "Programmation graphique est : ");
        q2.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), true, "evenementielle"));
        q2.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), false, "procédurale"));
        notion_1.AjouterQuestion(q2);
        Question q3 = new QCU(GeneratorID.newQuestionID(QCU.class), "Un Stream est : ");
        q3.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), true, "Une connexion vers un fichier"));
        q3.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), false, "Un fichier"));
        notion_2.AjouterQuestion(q3);
        Question q4 = new QO(GeneratorID.newQuestionID(QO.class), "Donner une classe permettat de lire d'un fichier");
        q4.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), true, "FileReader"));
        q4.ajouterPropositionAQuestion(new Proposition(GeneratorID.newPropositionID(), true, "FileInputStream"));
        notion_2.AjouterQuestion(q4);
        gestForm1.ajouterNotion(formation_2.getId(), notion_1);
        gestForm1.ajouterNotion(formation_2.getId(), notion_2);
        /// -------------------------

        stage.setTitle("Login");
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = (new Scene(login));
        stage.setScene(scene);
        stage1 =stage;
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
