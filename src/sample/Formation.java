package sample;

import java.util.Date;
import java.util.List;

public class Formation {
    private final String id;
    private List<Apprenant> apprenants;
    private String nom;
    private List<Notion> notions;
    private Date ouvertureFormationDate;
    private Date expirationFormationDate;
    private List<Quiz> quizzes;
    private String description;

    public Formation(String id) {
        this.id=id;
    }
    
}
