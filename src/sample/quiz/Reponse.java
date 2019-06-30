package sample.quiz;

import java.util.HashSet;

public class Reponse {
    private final String id; /// id of the question that this Reponse answers
    private HashSet<Proposition> propositions ;
    public Reponse(String id) {
        this.id = id;
        this.propositions = new HashSet<>();
    }
    public void ajouterPropos(Proposition proposition)
    {
        propositions.add(proposition);

    }

    public String getId() {
        return id;
    }

    public HashSet<Proposition> getPropositions() {
        return propositions;
    }

    public void retirerPropos(String propositionId) {
        for (Proposition p : propositions) {
            if ((p.getId()).equals(propositionId)) {
                propositions.remove(p);
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Reponse) {
            return this.id.equals(((Reponse) obj).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
