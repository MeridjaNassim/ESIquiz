package sample;

public class Proposition {
    private final String id;
    public String proposition;
    private final boolean propositionCorrect;
    public Proposition(String id, boolean propositionCorrect) {
        this.id = id;
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
        return this.id.hashCode();
    }
}
