package sample;

public class Proposition {
    private final String id;
    private String proposition;
    private final boolean propositionCorrect;
    public Proposition(String id, boolean propositionCorrect , String proposition) {
        this.id = id;
        this.proposition = proposition;
        this.propositionCorrect = propositionCorrect;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Proposition){
            return this.id.equals(((Proposition) obj).id);
        }
        return false;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public String getProposition() {
        return proposition;
    }

    public boolean isPropositionCorrect() {
        return propositionCorrect;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
