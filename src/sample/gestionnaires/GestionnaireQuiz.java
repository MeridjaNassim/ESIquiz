package sample.gestionnaires;

import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;
import sample.Common.Notion;
import sample.quiz.*;
import sample.users.Apprenant;
import sample.utils.GeneratorID;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GestionnaireQuiz extends Gestionnaire {

    public Quiz nouveauQuiz(HashMap<Notion, Integer> notions) {
        /// génére un quiz contenant des question telle que pour chaque notion on prend un nombre donnée de question
        Quiz quiz = new Quiz(GeneratorID.newQuizID());
        buildQuestions(notions, quiz);
        this.getFormation().getQuizzes().add(quiz); /// ajout du quiz à la formation courante
        return quiz;
    }

    public void modifierTitreQuiz(String quizId, String newTitle) {
        for (Quiz qz : getFormation().getQuizzes()
        ) {
            if (qz.getId().equals(quizId)) {
                qz.setNomQuiz(newTitle);
                return;
            }
        }
    }

    public void modifierDateOuverture(String quizId, Date newDate) {
        for (Quiz qz : getFormation().getQuizzes()) {
            if (qz.getId().equals(quizId)) {
                qz.setOuvertureDate(newDate);
            }
        }
    }

    public void modifierDateFermeture(String quizId, Date newDate) {
        for (Quiz qz : getFormation().getQuizzes()) {
            if (qz.getId().equals(quizId)) {
                qz.setExpirationDate(newDate);
            }
        }
    }

    public void supprimerQuestion(String quizId, String questionId) {
        /// supprime une question donnée du quiz
        for (Quiz qz : getFormation().getQuizzes()) {
            if (qz.getId().equals(quizId)) {
                qz.supprimerQuestion(questionId);
            }
        }
    }

    public void ajouterQuestion(String quizId, String notionID) {
        /// ajoute une question aléatoire au quiz
        for (Quiz qz : getFormation().getQuizzes()
        ) {
            if (qz.getId().equals(quizId)) {
                /// while the question already exists in quiz change it randomly
                Question question = randomQuastion(notionID);
                while (qz.getQuestions().contains(question)) {
                    question = randomQuastion(notionID);
                }
                qz.ajouterQuestion(question);
            }
        }
    }

    public void changerQuestion(String quizId, String questionId, String newQsNotionId) {
        /// permet de changer une question donnée par une autre aléatoire d'une notion spécifié (celle ci n'est pas la meme que celle changé )
        for (Quiz quiz : getFormation().getQuizzes()
        ) {
            if (quiz.getId().equals(quizId)) {
                Question question = randomQuastion(newQsNotionId);
                while (question.getId().equals(questionId) && quiz.getQuestions().contains(question)) {
                    question = randomQuastion(newQsNotionId);
                }
                quiz.remplacerQuestion(questionId, question);
            }
        }
    }

    public void sauvegarder(String quizId) {
        /// sauvegarde le quiz
    }

    public boolean isQuizOuvert(String quizId) {
        for (Quiz qz : getFormation().getQuizzes()
        ) {
            if (qz.getId().equals(quizId)) {
                return qz.getOuvertureDate().before(new Date());
            }
        }
        return false;
    }

    public boolean isQuizOuvert(Quiz quiz) {
        /// retourne vrai si ce quiz est ouvert (ie : sa date de début est dépassée)
        return quiz.getOuvertureDate().before(new Date());
    }

    public void afficherQuizs() {
        for (Quiz quiz : getFormation().getQuizzes()
        ) {
            afficheQuiz(quiz);
        }
    }

    public void afficherQuizs(boolean quizOuvert) {
        if (!quizOuvert) {
            afficherQuizs();
            return;
        }
        for (Quiz quiz : getFormation().getQuizzes()
        ) {
            if (isQuizOuvert(quiz)) {
                afficheQuiz(quiz);
            }
        }
    }

    public void afficheQuiz(Quiz quiz) {
        System.out.println("Quiz ID : " + quiz.getId());
        System.out.println("Quiz Name :" + quiz.getNomQuiz());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        System.out.println("Quiz Ouverture :" + dateFormat.format(quiz.getOuvertureDate()));
        System.out.println("Quiz Fermeture :" + dateFormat.format(quiz.getExpirationDate()));
        System.out.println("--Les Questions du quiz--");
        for (Question question : quiz.getQuestions()
        ) {
            afficheQuestion(question);
        }
    }

    ///private methods
    private void afficheQuestion(Question question) {
        System.out.println("Question ID :" + question.getId());
        System.out.println("Question Type : " + question.getClass().getSimpleName());
        System.out.println("Question ? : " + question.getEnonceQuestion());
        System.out.println("--Les propositions--");
        for (Proposition props : question.getPropositions()
        ) {
            System.out.println(props.getId() + "-" + props.getProposition());
        }

    }

    private void buildQuestions(HashMap<Notion, Integer> notions, Quiz quiz) {
        /// Construit un ensemble de questions pour le quiz appartir des notions choisi avec leur nombre de question
        for (Notion notion : notions.keySet()) {
            int nbquestion = notions.get(notion); // nombre de questions par notion
            for (int i = 0; i < nbquestion; i++) {
                Question question = randomQuastion(notion.getId());
                while (quiz.getQuestions().contains(question)) {
                    question = randomQuastion(notion.getId());
                }
                quiz.getQuestions().add(question);
            }
        }
    }

    private Question randomQuastion(String notionID) {
        /// génére une question aléatoire de la notion d'id notionID si elle existe dans la formation
        for (Notion notion : getFormation().getNotions()
        ) {
            if (notion.getId().equals(notionID)) {
                Random rand = new Random();
                int index = rand.nextInt(notion.getQuestionsSet().size());
                Object[] questions = notion.getQuestionsSet().toArray();
                return (Question) questions[index];
            }
        }
        return null;
    }

    public Quiz getQuizByID(String id) {
        for (Quiz quiz : getFormation().getQuizzes()) {
            if (quiz.getId().equals(id)) {
                return quiz;
            }
        }
        return null;
    }

    public Question getQuestionByID(Quiz quiz, String id) {
        ///récuprer la question (id) depuis le quiz
        for (Question question : quiz.getQuestions()
        ) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        return null;
    }

    public Question getQuestionByID(String id) {
        /// recupérer une question de la formation par l'id
        for (Quiz quiz : getFormation().getQuizzes()
        ) {
            Question question = getQuestionByID(quiz, id);
            if (question != null) {
                return question;
            }
        }
        return null;
    }

    public Pair<Quiz, List<Reponse>> ouvrirQuiz(String quizID, Apprenant app, boolean newTrial) {
        /// Ouvre un quiz dont l'id est quizId si il existe dans les quiz entamé de l'apprenant app , si newTrial == true le quiz serra ouvert sans reponses (les anciens serront effacé)
        /// si le quiz ne figure pas dans la formation il null serra retourné (rien n'est ouvert )
        Quiz quiz = getQuizByID(quizID);
        if (quiz != null) {
            if (newTrial) {
                app.addQuizEntame(quiz, new ArrayList<>());
                return new Pair<>(quiz, app.getQuizsEntames().get(quiz));
            }
            if (app.getQuizsEntames().containsKey(quiz)) {
                return new Pair<>(quiz, app.getQuizsEntames().get(quiz));
            } else {
                app.addQuizEntame(quiz, new ArrayList<>());
                return new Pair<>(quiz, app.getQuizsEntames().get(quiz));
            }
        }
        return null;
    }

    public void repondre(@NotNull Apprenant app, Quiz quiz, List<Reponse> reponses) {
        /// permet de répondre à une question donnée
        app.addQuizEntame(quiz, reponses);
    }

    public Reponse buildReponse(String questionID, List<Proposition> propositions) {
        /// Construit une reponse à partir de l'id de la question et les prorposition choisi
        Reponse rep = new Reponse(questionID);
        for (Proposition prop : propositions
        ) {
            rep.ajouterPropos(prop);
        }
        return rep;
    }

    public Reponse modifierReponse(String questionId, Apprenant apprenant, List<Proposition> propositions) {
        /// Modifie une reponse d'une question donné dans quiz commencé par l'apprenant par une list de proposition rechoisi
        Question temp = new QO(questionId, "");
        Reponse temp2 = new Reponse(questionId);
        for (Quiz quiz : apprenant.getQuizsEntames().keySet()
        ) {
            if (quiz.getQuestions().contains(temp)) {
                List<Reponse> rep = apprenant.getQuizsEntames().get(quiz);

                for (Reponse re : rep) {
                    if (re.equals(temp2)) {
                        re = buildReponse(questionId, propositions);
                        return re;
                    }
                }
            }
        }
        return null;
    }

    public void supprimerReponse(String questionID, Apprenant apprenant) {
        /// Supprime une Reponse d'une question qu'un apprenant à commencer
        Question temp = new QO(questionID, "");
        Reponse temp2 = new Reponse(questionID);
        for (Quiz quiz : apprenant.getQuizsEntames().keySet()
        ) {
            if (quiz.getQuestions().contains(temp)) {
                List<Reponse> rep = apprenant.getQuizsEntames().get(quiz);
                for (Reponse re : rep) {
                    if (re.equals(temp2)) {
                        rep.remove(re);
                    }
                }
            }
        }
    }

    public Proposition getPropositionByID(Question question, String id) {
        /// Recupérer une proposition d'une question à partir de son ID
        for (Proposition propo : question.getPropositions()
        ) {
            if (propo.getId().equals(id)) {
                return propo;
            }
        }
        return null;
    }

}
