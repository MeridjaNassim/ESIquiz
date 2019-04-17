package sample;

import sample.utils.Evaluator;

import java.util.*;
import java.util.function.Predicate;

public class QCM extends Question {
    public QCM(String id, String enonceQuestion) {
        super(id, enonceQuestion);
        EVALUTOR = new Evaluator<Proposition>() {
            @Override
            public double evaluate(Proposition proposed, Proposition chosed) {
                if(proposed.isPropositionCorrect() && proposed.equals(chosed)){
                    return +1;
                }if(!proposed.isPropositionCorrect()&& proposed.equals(chosed)){
                    return -1;
                }if(proposed.isPropositionCorrect()&& !proposed.equals(chosed)){
                    return -1;
                }if(proposed.isPropositionCorrect()&& !proposed.equals(chosed)){
                    return +1;
                }
                return  0;
            }
        };

    }

    public double evaluer(Set<Proposition> propositionsChoisi){
        int size = this.getPropositions().size(); // le nombre de propositions
        if( size != 0) {
            HashSet<Proposition> correct = partitionPropositions((x) -> x.isPropositionCorrect(),this.getPropositions()); // séparer les proposition correct
            HashSet<Proposition> incorrect = partitionPropositions((x)->!x.isPropositionCorrect(),this.getPropositions());// séparer les proposition incorrect
            int grade = 0;
            // pour chaque proposition correct si elle existe dans choisi +1 sinon -1
            for (Proposition propo: correct
                 ) {
                if(propositionsChoisi.contains(propo)) {
                    grade++;
                }else {
                    grade --;
                }
            }
            // pour chaque proposition incorrect si elle existe dans choisi -1 sinon +1
            for (Proposition propo: incorrect
                    ) {
                if(propositionsChoisi.contains(propo)) {
                    grade--;
                }else {
                    grade ++;
                }
            }
            // si la valeur est négative la note est zéro
            return (grade <0) ? 0 : grade/size;
        }
        return 0;
    }
    private HashSet<Proposition> partitionPropositions(Predicate<Proposition> predicate,List<Proposition> propositions){
        HashSet<Proposition> set = new HashSet<>();
        for (Proposition proposition: propositions
             ) {
            if(predicate.test(proposition)){
                set.add(proposition);
            }
        }
        return set;
    }
}
