package sample.utils;

import sample.quiz.QCM;
import sample.quiz.QCU;
import sample.quiz.QO;
import sample.quiz.Question;
import sample.users.Apprenant;
import sample.users.Formateur;
import sample.users.Personne;

public final class GeneratorID {

    private GeneratorID() {
    }

    private static int personID;
    private static int apprenantID;
    private static int formateurID;
    private static int formationID;
    private static int notionID;
    private static int questionID;
    private static int quizID;
    private static int propositionID;

    public static void initIDs() {
        // load() from a file
        personID = -1;
        formateurID = -1;
        apprenantID = -1;
        formationID = -1;
        notionID = -1;
        questionID = -1;
        quizID = -1;
        propositionID = -1;
    }

    public static String newPersonID(Class<? extends Personne> clas) {
        if (clas == Formateur.class) {
            personID++;
            formateurID++;
            return "PR" + Integer.toHexString(personID) + "-FOR" + Integer.toHexString(formateurID);
        } else if (clas == Apprenant.class) {
            personID++;
            apprenantID++;
            return "PR" + Integer.toHexString(personID) + "-APR" + Integer.toHexString(apprenantID);
        } else {
            personID++;
            return "PR" + Integer.toHexString(personID);
        }
    }

    public static String newFormationID() {
        formationID++;
        return "FORMA-" + Integer.toHexString(formationID);
    }

    public static String newQuestionID(Class<? extends Question> clas) {
        if (clas == QCM.class) {
            questionID++;
            return "Q" + Integer.toHexString(questionID) + "QCM";
        }
        if (clas == QO.class) {
            questionID++;
            return "Q" + Integer.toHexString(questionID) + "QO";
        }
        if (clas == QCU.class) {
            questionID++;
            return "Q" + Integer.toHexString(questionID) + "QCU";
        }
        questionID++;
        return "Q" + Integer.toHexString(questionID);

    }

    public static String newNotionID() {
        notionID++;
        return "NOT-" + Integer.toHexString(notionID);
    }

    public static String newQuizID() {
        quizID++;
        return "QUIZ-" + Integer.toHexString(quizID);
    }

    public static String newPropositionID() {
        propositionID++;
        return "PROPO-" + Integer.toHexString(propositionID);
    }

    /// method used to load ids from file
    public void load(String path) {

    }
}
