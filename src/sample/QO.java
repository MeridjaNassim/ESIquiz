package sample;

import sample.utils.Evaluator;

import java.util.Comparator;

public class QO extends Question {

    public QO(String id, String enonceQuestion) {
        super(id, enonceQuestion);
        EVALUTOR = new Evaluator<Proposition>() {
            @Override
            public double evaluate(Proposition proposed, Proposition chosed) {
                if (proposed.getProposition().equals(chosed.getProposition())) {
                    return 1;
                }
                return 0;
            }
        };
    }

}
