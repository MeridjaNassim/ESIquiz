package sample;

import java.util.HashSet;
import java.util.List;

public class Apprenant extends Personne implements  Comparable {
   private HashSet evaluations=new HashSet<Quiz,Double>();
   private HashSet quizsEntames=new HashSet<Quiz, List<Reponse>>();
   private Double reussiteMoyenne;
    public  Apprenant(String id) {
        super(id);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
