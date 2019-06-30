package sample.quiz;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashSet;

public class Quiz {
    private final String id;
    private HashSet<Question> questions;
    private String nomQuiz;
    private Date ouvertureDate;
    private Date expirationDate;
    public Quiz(String id, String nomQuiz, Date ouvertureDate, Date expirationDate) {
        this.id = id;
        this.questions = new HashSet<>();
        this.nomQuiz = nomQuiz;
        this.ouvertureDate = ouvertureDate;
        this.expirationDate = expirationDate;
    }
    public Quiz(String id){
        this.id = id;
        questions = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setQuestions(HashSet<Question> questions) {
        this.questions = questions;
    }

    public HashSet<Question> getQuestions() {
        return questions;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getOuvertureDate() {
        return ouvertureDate;
    }

    public String getNomQuiz() {
        return nomQuiz;
    }

    public void setNomQuiz(String nomQuiz) {
        this.nomQuiz = nomQuiz;
    }


    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setOuvertureDate(Date ouvertureDate) {
        this.ouvertureDate = ouvertureDate;
    }
    public void remplacerQuestion(@NotNull String id , Question newQuestion){
       Question temp = new QO(id,null);
       this.getQuestions().remove(temp);
       this.getQuestions().add(newQuestion);
    }
    public void remplacerQuestion(Question q1,Question newQuestion){
        q1=newQuestion;
    }
    public void supprimerQuestion(@NotNull String id ){
        for (Question qs: questions) {
            if(qs.getId().equals(id)){
                questions.remove(qs);
                return;
            }
        }
    }
    public void ajouterQuestion(Question question){
            questions.add(question);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((Quiz) obj).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
