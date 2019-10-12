package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class LoginController {
    @FXML
    private Button btnFormateur;
    @FXML
    private Button btnApprenant;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField passwordText;
    @FXML
    public void initialize(){

        btnFormateur.setOnMouseClicked(e->{
            boolean ok = ESIQuiz.authentifier(userNameText.getText(),passwordText.getText());
            if(ok){
                try{
                    loginToApp();
                }catch (IOException ee){
                    System.out.println("could not load");
                }
            }
        });
    }
    private void loginToApp() throws IOException {
       Parent quizzes = FXMLLoader.load(getClass().getResource("Quizzes.fxml"));

       Parent sidePanel = FXMLLoader.load(getClass().getResource("SidePanel.fxml"));
        HBox page = new HBox();
        ArrayList<Integer> ints = new ArrayList<>();

        page.getChildren().addAll(sidePanel,quizzes);
        Scene scene = new Scene(page,1200,400);
        ESIQuiz.stage1.setTitle("Formateur "+ ESIQuiz.getFormateur().getName());
        ESIQuiz.changeScene(scene);

    }
}
