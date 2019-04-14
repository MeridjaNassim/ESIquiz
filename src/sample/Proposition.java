package sample;

public class Proposition {
    protected final String id;
    public String proposition;
    protected final boolean propositionCorrect;
    public Proposition(String id, boolean propositionCorrect) {
        this.id = id;
        this.propositionCorrect = propositionCorrect;
    }
}
