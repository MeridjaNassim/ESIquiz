package sample;

import java.util.List;

public abstract class Question {
    protected final String id;
    protected String enonceQuestion;
    protected List<Proposition> propositions;
    protected int nbPropositions;
    protected Question(String id) {
        this.id = id;
    }
    public void AjouterPropositionAQuestion(Proposition proposition)
    {
        propositions.add(proposition);
        nbPropositions++;
    }
    public void RetirerPropositionDeQuestion(String propositionId)
    {
        for (Proposition p:propositions) {
            if ((p.id).equals(propositionId))
            {
                propositions.remove(p);
                nbPropositions--;
            }
        }
    }
}
