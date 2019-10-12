package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Common.Notion;
import sample.quiz.Quiz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class QuizzesController {
    @FXML
    private ComboBox<String> formationCombo;
    @FXML
    private TextField nomQuiz;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRetirer;
    @FXML
    private TextField nbQuestions;
    @FXML
    private ComboBox<String> ajoutCombo;
    @FXML
    private ComboBox<String> retirerCombo;
    @FXML
    private Button generateQuiz;
    @FXML
    private Button btnSave;
    @FXML
    private Button consulterQuiz;
    private String selectedFormationName;
    public static Scene lastScene;
    private HashMap<Notion,Integer> notions = new HashMap<>();
    public static Quiz gener ;
    private List<String> notionsAdded = new ArrayList<>();
    private Parent root_to_Quiz ;
    private ConsulterQuizController consulterQuizController;
    @FXML
    public void initialize() throws IOException {

       initButtons();
       formationCombo.getItems().addAll(ESIQuiz.getFormationNames());
       formationCombo.setOnAction(e-> {
           selectedFormationName = formationCombo.getSelectionModel().getSelectedItem();
           setNotionsAjouter(selectedFormationName);
       });
    }
    private void initButtons(){
        btnAjouter.setOnMouseClicked(e->{

           String s = ajoutCombo.getSelectionModel().getSelectedItem();
            if(s!=null) {
                Notion n = ESIQuiz.getNotionByName(selectedFormationName, s);
                retirerCombo.getItems().add(s);
                ajoutCombo.getItems().remove(s);
                int nbQuestion = Integer.parseInt(nbQuestions.getText());
                notions.put(n, nbQuestion);
            }
        });
        btnRetirer.setOnMouseClicked(e->{
            String s = retirerCombo.getSelectionModel().getSelectedItem();
            if(s!=null){
                Notion n = ESIQuiz.getNotionByName(selectedFormationName,s);
                notions.remove(n);
                ajoutCombo.getItems().add(s);
                retirerCombo.getItems().remove(s);
            }

        });
        generateQuiz.setOnMouseClicked(e->{
            if(notions.size() != 0 ){
                LocalDate d = dateDebut.getValue();
                Instant  i = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
                Date dateDebutt =Date.from(i);
                d = dateFin.getValue();
                i=Instant.from(Instant.from(d.atStartOfDay(ZoneId.systemDefault())));
                Date dateFinn = Date.from(i);
                gener = ESIQuiz.generateQuiz(selectedFormationName,nomQuiz.getText(),dateDebutt,dateFinn,notions);
                notions.clear();
                ajoutCombo.getItems().clear();
                retirerCombo.getItems().clear();
                setNotionsAjouter(selectedFormationName);
                ESIQuiz.gp.afficheQuiz(gener);

            }
        });
        consulterQuiz.setOnMouseClicked(e->{
            FXMLLoader fxmlLoader = new FXMLLoader();
            try{
                root_to_Quiz = fxmlLoader.load(getClass().getResource("ConsulterQuiz.fxml").openStream());
                consulterQuizController = (ConsulterQuizController) fxmlLoader.getController();
                Scene s = new Scene(root_to_Quiz);
                lastScene = ESIQuiz.currentScene();
                consulterQuizController.setQuiz(gener);
                consulterQuizController.setLastScene(lastScene);
                consulterQuizController.buildPage();
                ESIQuiz.changeScene(s);
            }catch (Exception ee) {

            }

        });
        btnSave.setOnMouseClicked(e->{
            if(gener != null) {
                try(FileWriter fw = new FileWriter("./data/quizzes.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(gener.toString());
                    //more code
                } catch (IOException ee) {
                    //exception handling left as an exercise for the reader
                    System.out.println("error happened");
                }
            }
        });
    }
    private void setNotionsAjouter(String selectedFormationName){
        ajoutCombo.getItems().addAll(ESIQuiz.getNotionsNames(selectedFormationName));
    }
}
