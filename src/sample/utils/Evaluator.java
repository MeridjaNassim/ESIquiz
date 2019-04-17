package sample.utils;

import sample.Proposition;

public interface Evaluator<T> {

     double evaluate(T proposed ,T chosed);
}
