package sample;

import java.util.Set;

public class Notion {
    private final String id;
    private Set<Question> questionsSet;
    public int nbQuestion=0;
    public Notion(String id) {
        this.id = id;
    }
    public void AjouterQuestion(Question question)
    {
        questionsSet.add(question);
        nbQuestion++;
    }
    public void RetirerQuestionDeNotion(String questionId)
    {
        for (Question q:questionsSet) {
            if ((q.id).equals(questionId))
            {
                questionsSet.remove(q);
                nbQuestion--;
            }

        }
    }

}
