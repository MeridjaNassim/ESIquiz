package sample;

import sample.utils.Evaluator;

import java.util.Comparator;

public class QCU extends Question {
    public QCU(String id, String enonceQuestion) {
        super(id, enonceQuestion);
        EVALUTOR = new Evaluator<Proposition>() {
            @Override
            public double evaluate(Proposition proposed, Proposition chosed) {
                if(proposed.isPropositionCorrect() && proposed.equals(chosed)){
                    return 1;
                }
                return 0;
            }
        };


    }
}
