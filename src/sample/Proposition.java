package sample;

public class Proposition {
    private final String id;
    private String proposition;
    private final Boolean propositionCorrect;
    public Proposition(String id, Boolean propositionCorrect , String proposition) {
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

    @Override
    public int hashCode() {
        if(id != null){
            return id.hashCode();
        }
        return 0; /// spécial case when using Proposition to answer QO questions
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


}
