package sample.Common;


import sample.quiz.Quiz;
import sample.users.Apprenant;
import sample.users.Formateur;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Formation {
    private final String id;
    private HashMap<String,Apprenant> apprenants;
    private String nom;
    private List<Notion> notions;
    private Date ouvertureFormationDate;
    private Date expirationFormationDate;
    private List<Quiz> quizzes;
    private String description;
    private Formateur formateur;
    public Formation(String id, String nom, Date ouvertureFormationDate, Date expirationFormationDate) {
        this.id = id;
        this.nom = nom;
        this.ouvertureFormationDate = ouvertureFormationDate;
        this.expirationFormationDate = expirationFormationDate;
        apprenants = new HashMap<>();
        notions = new ArrayList<>();
        quizzes = new ArrayList<>();

    }

    public Formation(String id, String nom, Date ouvertureFormationDate, Date expirationFormationDate, Formateur formateur) {
        this.id = id;
        this.nom = nom;
        this.ouvertureFormationDate = ouvertureFormationDate;
        this.expirationFormationDate = expirationFormationDate;
        this.formateur = formateur;
        quizzes = new ArrayList<>();
        apprenants = new HashMap<>();
        notions = new ArrayList<>();
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public String getId() {
        return id;
    }

    public HashMap<String,Apprenant> getApprenants() {
        return apprenants;
    }

    public String getNom() {
        return nom;
    }

    public Date getExpirationFormationDate() {
        return expirationFormationDate;
    }

    public Date getOuvertureFormationDate() {
        return ouvertureFormationDate;
    }

    public List<Notion> getNotions() {
        return notions;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public String getDescription() {
        return description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addApprenant(Apprenant apprenant){
        apprenants.put(apprenant.getId(),apprenant);
    }
    public  void removeApprenant(String id){
        this.apprenants.remove(id);
    }

    public void addNotion(Notion notion){
        if(!this.notions.contains(notion)){
            this.notions.add(notion);
        }
    }
    public void removeNotion(String id){
        for (Notion noti: notions
             ) {
            if (noti.getId().equals(id)){
                quizzes.remove(noti);
                return;
            }
        }
    }

    public void addQuiz(Quiz quiz){
        if(!this.quizzes.contains(quiz)){
            this.quizzes.add(quiz);
        }
    }
    public void removeQuiz(String id){
        for (Quiz quize: quizzes
        ) {
            if (quize.getId().equals(id)){
                quizzes.remove(quize);
                return;
            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if( obj instanceof Formation){
            return  this.id.equals(((Formation) obj).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void modifierExpiration(Date newDate , String idFormateur) throws DateTimeException{
     if(this.formateur.getId().equals(idFormateur)) {
         if(newDate.after(ouvertureFormationDate)){
             this.expirationFormationDate = newDate;
         }else
         {
            throw new  DateTimeException("This new date is before or equal to ouverture date");
         }
     }
    }
    public void modifierOuverture(Date newDate , String idFormateur){
        if(this.formateur.getId().equals(idFormateur)){
                this.ouvertureFormationDate = newDate;
        }

    }

}
