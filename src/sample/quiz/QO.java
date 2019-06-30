package sample.quiz;

import sample.utils.Evaluator;

public class QO extends Question {

    public QO(String id, String enonceQuestion) {
        super(id, enonceQuestion);
        EVALUTOR = new Evaluator<Proposition>() {
            @Override
            public double evaluate(Proposition proposed, Proposition chosed) {
                if (proposed.getProposition().toLowerCase().equals(chosed.getProposition().toLowerCase())) {
                    return 1;
                }
                return 0;
            }
        };
    }

}
