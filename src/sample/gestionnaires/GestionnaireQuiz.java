package sample.gestionnaires;

import com.sun.javafx.image.impl.General;
import sample.*;
import sample.utils.GeneratorID;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class GestionnaireQuiz extends Gestionnaire{

    public Quiz nouveauQuiz(HashMap<Notion,Integer> notions){
        /// génére un quiz contenant des question telle que pour chaque notion on prend un nombre donnée de question
        Quiz quiz = new Quiz(GeneratorID.newQuizID());
        buildQuestions(notions,quiz);
        this.getFormation().getQuizzes().add(quiz); /// ajout du quiz à la formation courante
        return quiz;
    }
    public void modifierTitreQuiz(String quizId ,String newTitle) {
        for (Quiz qz :getFormation().getQuizzes()
             ) {
            if(qz.getId().equals(quizId)) {
                qz.setNomQuiz(newTitle);
                return;
            }
        }
    }
    public void modifierDateOuverture(String quizId, Date newDate){
        for (Quiz qz : getFormation().getQuizzes()){
            if(qz.getId().equals(quizId)){
                qz.setOuvertureDate(newDate);
            }
        }
    }
    public void modifierDateFermeture(String quizId, Date newDate){
        for (Quiz qz : getFormation().getQuizzes()){
            if(qz.getId().equals(quizId)){
                qz.setExpirationDate(newDate);
            }
        }
    }
    public void supprimerQuestion(String quizId, String questionId){
        for (Quiz qz : getFormation().getQuizzes()){
            if(qz.getId().equals(quizId)){
                qz.supprimerQuestion(questionId);
            }
        }
    }
    public void ajouterQuestion(String quizId , String notionID){
        for (Quiz qz: getFormation().getQuizzes()
             ) {
            if(qz.getId().equals(quizId)){
                /// while the question already exists in quiz change it randomly
                Question question =  randomQuastion(notionID);
                while (qz.getQuestions().contains(question)){
                    question = randomQuastion(notionID);
                }
                qz.ajouterQuestion(question);
            }
        }
    }
    public  void changerQuestion(String quizId,String questionId , String newQsNotionId){
        for (Quiz quiz : getFormation().getQuizzes()
             ) {
            if(quiz.getId().equals(quizId)){
                Question question = randomQuastion(newQsNotionId);
                while (question.getId().equals(questionId)){
                    question = randomQuastion(newQsNotionId);
                }
                quiz.remplacerQuestion(questionId,question);
            }
        }
    }
    public void sauvegarder(String quizId){

    }

    public boolean isQuizOuvert(String quizId){
        for (Quiz qz: getFormation().getQuizzes()
             ) {
            if( qz.getId().equals(quizId)){
                return qz.getOuvertureDate().before(new Date());
            }
        }
        return false;
    }
    public boolean isQuizOuvert(Quiz quiz){

        return quiz.getOuvertureDate().before(new Date());
    }

    public void afficherQuizs(){
        for (Quiz quiz: getFormation().getQuizzes()
             ) {
            afficheQuiz(quiz);
        }
    }
    public void afficherQuizs(boolean quizOuvert){
        if(!quizOuvert) {
            afficherQuizs();
            return;
        }
        for (Quiz quiz: getFormation().getQuizzes()
        ) {
            if (isQuizOuvert(quiz)) {
                afficheQuiz(quiz);
            }
        }
    }
    public void afficheQuiz(Quiz quiz){
        System.out.println("Quiz ID : " + quiz.getId());
        System.out.println("Quiz Name :"+ quiz.getNomQuiz());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        System.out.println("Quiz Ouverture :"+ dateFormat.format(quiz.getOuvertureDate()));
        System.out.println("Quiz Fermeture :" +dateFormat.format(quiz.getExpirationDate()));
        System.out.println("--Les Questions du quiz--");
        for (Question question: quiz.getQuestions()
             ) {
            afficheQuestion(question);
        }
    }
    ///private methods
    private void afficheQuestion(Question question){
        System.out.println("Question ID :" +question.getId());
        System.out.println("Question Type : " +question.getClass().getSimpleName());
        System.out.println("Question ? : " +question.getEnonceQuestion());
        System.out.println("--Les propositions--");
        for (Proposition props : question.getPropositions()
             ) {
            System.out.println(props.getId()+"-"+props.getProposition());
        }

    }
    private void buildQuestions(HashMap<Notion,Integer> notions , Quiz quiz){
        for (Notion notion  :   notions.keySet()) {
            int nbquestion = notions.get(notion); // nombre de questions par notion
            for (int i = 0 ; i<nbquestion ; i++ ){
                Question question = randomQuastion(notion.getId());
                while (quiz.getQuestions().contains(question)){
                    question = randomQuastion(notion.getId());
                }
                quiz.getQuestions().add(question);
            }
        }
    }
    private Question randomQuastion(String notionID){
        for (Notion notion: getFormation().getNotions()
             ) {
            if(notion.getId().equals(notionID)){
                Random rand = new Random();
                int index = rand.nextInt(notion.getQuestionsSet().size());
                Object[] questions = notion.getQuestionsSet().toArray();
                return (Question) questions[index];
            }
        }
        return null;
    }

}
