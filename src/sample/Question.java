package sample;

import sample.utils.Evaluator;

import java.util.ArrayList;
import java.util.List;

public abstract class Question {
    private final String id;
    private String enonceQuestion;
    private List<Proposition> propositions;
    protected Evaluator<Proposition> EVALUTOR;
    public Question(String id,String enonceQuestion) {
        this.id = id;
        this.enonceQuestion = enonceQuestion;
        propositions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getEnonceQuestion() {
        return enonceQuestion;
    }

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setEnonceQuestion(String enonceQuestion) {
        this.enonceQuestion = enonceQuestion;
    }

    public void ajouterPropositionAQuestion(Proposition proposition)
    {
        propositions.add(proposition);

    }
    public void retirerPropositionDeQuestion(String propositionId)
    {
        for (Proposition p:propositions) {
            if ((p.getId()).equals(propositionId))
            {
                propositions.remove(p);

            }
        }
    }
    public void afficherPropositions()
    {
        for (Proposition p:propositions) {
            System.out.println(p.getProposition());
        }

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Question) {
            return this.id.equals(((Question) obj).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    public double evaluer(Proposition propose , Proposition choisi){
        return EVALUTOR.evaluate(propose,choisi);
    }
}
