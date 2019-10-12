package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class SidePanelController {

    @FXML
    private ImageView avatar;
    @FXML
    private Label formateurName;
    @FXML
    private Pane sidePanelPane ;
    @FXML
    private Button btnQuizzes;
    @FXML
    private Button btnDeconnecte;
    @FXML
    public void initialize() throws IOException {
        // set a clip to apply rounded border to the original image.
        Circle clip = new Circle(50,50,25);
        clip.setRadius(50);
        avatar.setClip(clip);
        // getting formateur name ;
        formateurName.setText(ESIQuiz.getFormateur().getName()+" "+ESIQuiz.getFormateur().getPrenom());
        sidePanelPane.setBackground(new Background(new BackgroundFill(Color.web("#" + "307fa1"), CornerRadii.EMPTY, Insets.EMPTY)));
        initButtons();
    }
    private void initButtons() throws IOException {
        btnDeconnecte.setOnMouseClicked(e->{
          try{
             ESIQuiz.stage1.setTitle("Login");
              Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
              Scene scene = (new Scene(login));
              ESIQuiz.changeScene(scene);
          }catch (IOException ee){
              System.out.println("could not load");
          }
        });
    }
}
