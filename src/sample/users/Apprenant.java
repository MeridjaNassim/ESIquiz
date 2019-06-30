package sample.users;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.*;
import sample.quiz.Quiz;
import sample.quiz.Reponse;

public class Apprenant extends Personne implements  Comparable<Apprenant> {
   ///<summary>
   ///Cette class représente un simple Apprenant utilisatant l'application à travers son compte elle dérive de la class personne
    /// qui contient les champs : nom , prénom , ...Etc
    /// contient de plus des champ propre au activité de l'apprenant comme les quiz entamé , les evalutions de cette etudiant
   ///</summary>
   private HashMap<Quiz,Double> evaluations;
   private HashMap<Quiz,List<Reponse>> quizsEntames;
   private double reussiteMoyenne;

    public Apprenant(String id, String name, String prenom, Date dateDeNaissance, String lieuDeNaissance, String adresse) {
        super(id, name, prenom, dateDeNaissance, lieuDeNaissance, adresse);
        evaluations=new HashMap<>();
        quizsEntames=new HashMap<>();
    }

    public Apprenant(String id, String name, String prenom, Date dateDeNaissance, String lieuDeNaissance) {
        super(id, name, prenom, dateDeNaissance, lieuDeNaissance);
        evaluations=new HashMap<>();
        quizsEntames=new HashMap<>();
    }

    @Override
    public int compareTo(@NotNull Apprenant o) {
       return Double.compare(this.reussiteMoyenne,o.reussiteMoyenne);
    }

    public double getReussiteMoyenne() {
        return reussiteMoyenne;
    }

    public HashMap<Quiz, Double> getEvaluations() {
        return evaluations;
    }

    public HashMap<Quiz, List<Reponse>> getQuizsEntames() {
        return quizsEntames;
    }
    public void addEvalution(Quiz quiz , double eval){
        /// ajoute une evalution d'un quiz
        try{
           this.evaluations.put(quiz,eval);
        }catch (IllegalArgumentException e ){

            System.out.println("Could not add quiz : " +quiz.getId() +" evalution to this student evaluations");
            throw new  IllegalArgumentException(e);
        }
    }
    public void addQuizEntame(Quiz quiz ,List<Reponse> reponses){
        /// Ajoute un quiz entamé par l'apprenant avec ses reponses
        try{
            if(quiz == null ){
                throw new NullPointerException("quiz is null");
            }
            if(evaluations.containsKey(quiz)) {
                throw new IllegalArgumentException("This quiz is already done by this Apprenant");
            } else  {
                if(reponses!= null) {
                    this.quizsEntames.put(quiz,reponses);
                }else {
                    throw new NullPointerException("Reponses cannot be null in order to add them to started quizes");
                }
            }
        }catch (IllegalArgumentException e ){
            e.printStackTrace();
        }
    }

    public void setReussiteMoyenne(double reussiteMoyenne) {
        this.reussiteMoyenne = reussiteMoyenne;
    }
    public void calculReussiteMoyenne(){
        double sum =0 ;
        for (Quiz quiz : this.getEvaluations().keySet()
        ) {
            sum += this.getEvaluations().get(quiz);
        }
        this.setReussiteMoyenne(sum/this.getEvaluations().size());
    }
    @Override
    public String toString() {
        return "ID : "+super.getId()+"\n"+
                "Name : "+super.getName()+"\n"+
                "moyenne : "+reussiteMoyenne+"\n";
    }
    /// COMPARATORS



}
