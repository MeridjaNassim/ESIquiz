package sample.gestionnaires;

import sample.Formation;

public abstract class Gestionnaire {

    private Formation formation;

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
