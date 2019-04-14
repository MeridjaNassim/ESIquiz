package sample;

import java.util.Date;
import java.util.Set;

public class Quiz {
    protected final String id;
    protected Set<Question> questions;
    protected String nomQuiz;
    protected Date ouvertureDate;
    protected Date expirationDate;
    protected boolean quizAcheve;
    public Quiz(String id) {
        this.id = id;
    }

}
