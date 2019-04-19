package sample;

import java.util.HashSet;
import java.util.Set;

public class Notion {
    private final String id;
    private String name ;
    private String description;
    private Set<Question> questionsSet;
    public Notion(String id,String name) {
        this.id = id;
        this.name = name;
        questionsSet = new HashSet<>();
    }
    public void AjouterQuestion(Question question)
    {
        questionsSet.add(question);
    }
    public void RetirerQuestionDeNotion(String questionId)
    {
        for (Question q:questionsSet) {
            if ((q.getId()).equals(questionId))
            {
                questionsSet.remove(q);
            }

        }
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<Question> getQuestionsSet() {
        return questionsSet;
    }
}
