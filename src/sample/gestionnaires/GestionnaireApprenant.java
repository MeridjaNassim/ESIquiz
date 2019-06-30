package sample.gestionnaires;

import sample.Common.Formation;
import sample.quiz.*;
import sample.users.Apprenant;
import sample.users.Compte;
import sample.users.Personne;
import sample.utils.GenerateurCompte;
import sample.utils.GeneratorID;

import java.util.*;

public class GestionnaireApprenant extends Gestionnaire {
/*
* Cette class permet de gérer les apprenants a travers les fonctionnalitées tel que : l'évaluation des apprenants , le classement ...
* */

    public void evaluerApprenants(boolean notUseDate){
        for (Apprenant apprenant: getFormation().getApprenants().values()
             ) {
            evaluerApprenant(apprenant,notUseDate);
            }
    }
    public void evaluerApprenant(Apprenant apprenant,boolean notUseDate){
        /// Evalue tous les quiz de l'apprenant
        HashMap<Quiz,List<Reponse>> quizzes = apprenant.getQuizsEntames();
        for (Quiz quiz: quizzes.keySet() ) {
            evaluerApprenant(apprenant,quiz,notUseDate);
        }
    }
    public void evaluerApprenant(Apprenant apprenant,Quiz quiz,boolean notUseDate){
        ///evalue le quiz concerner
        /// if notUseDate == true the method will evalute the Apprenant even if quiz is not finished
        /// if false it will evaluate only if date passe expiration date of quiz
        Date today = new Date();
        double noteQuiz = 0;
        HashMap<Quiz,List<Reponse>> quizzes = apprenant.getQuizsEntames();
        if(quiz.getExpirationDate().before(today) || notUseDate){
            /// comparer les repnses
            List<Reponse> reponses = quizzes.get(quiz);
            for (Reponse reponse: reponses) {
                for (Question question: quiz.getQuestions()
                        ) {
                    if(question.getId().equals(reponse.getId())){
                        noteQuiz+= evaluerReponse(question,reponse);
                    }
                }
            }
            apprenant.addEvalution(quiz,noteQuiz*100/quiz.getQuestions().size());
            apprenant.getQuizsEntames().remove(quiz);
        }
    }

    private double evaluerReponse(Question question , Reponse reponse){
        /// récupérer l'ensemble des proposition choisi de l'apprenant depuis sa réponse
        HashSet<Proposition> propositions = reponse.getPropositions();
        List<Proposition> questionPropostion = question.getPropositions(); /// les propositions de la question

        if( question instanceof QO || question instanceof QCU) {
            Proposition choisi = propositions.iterator().next(); // recupérer la proposition entrée
            /// pour chaque proposition de la question si on trouve une correct on retourne 1
            for (Proposition propo: questionPropostion
                 ) {

               double correct =  question.evaluer(propo,choisi);
               if(correct == 1){
                   return 1;
               }
            }
            /// retourner 0 si aucune reponse choisi n'est correct
            return  0;
        }

        if(question instanceof QCM) {
           return ((QCM) question).evaluer(propositions); /// evaluer l'ensemble des propositions choisi
        }
        return 0;
    }

    public double tauxAccomplissement(Apprenant apprenant,String quizId){
        /// Calcule le taux d'accomplissement d'un quiz par un apprenant
        for (Quiz quiz: apprenant.getQuizsEntames().keySet()
             ) {
            if(quiz.getId().equals(quizId)){
                return (quiz.getQuestions().size() ==0 ) ?0:(double)apprenant.getQuizsEntames().get(quiz).size()/(double)quiz.getQuestions().size();
            }
        }
        return 0;
    }
    public double tauxAccomplissement(Apprenant apprenant,Quiz quiz){
        /// Calcule le taux d'accomplissement d'un quiz par un apprenant

        return (quiz.getQuestions().size() ==0 ) ?0:(double)apprenant.getQuizsEntames().get(quiz).size()/(double)quiz.getQuestions().size();

    }
    public void afficherQuizAcheve(String apprenantID){
        Apprenant apprenant = getFormation().getApprenants().get(apprenantID);
                for (Quiz quiz: apprenant.getEvaluations().keySet()
                     ) {
                    System.out.println("Quiz ID : "+quiz.getId()+" Evaluation : "+apprenant.getEvaluations().get(quiz));
                }

    }
    public void afficherQuizEntame(String apprenantID){
        Apprenant apprenant = getFormation().getApprenants().get(apprenantID);

                for (Quiz quiz: apprenant.getQuizsEntames().keySet()
                        ) {
                    System.out.println("Quiz ID : " + quiz.getId() + " Taux Accomplissement : "+ tauxAccomplissement(apprenant,quiz));
                    for (Reponse reponse : apprenant.getQuizsEntames().get(quiz)
                         ) {
                        System.out.println("Question : "+ reponse.getId());
                        System.out.println("Proposition choisis");
                        for (Proposition propo:reponse.getPropositions()
                             ) {
                            System.out.println(propo.getId() +" : "+ propo.getProposition());
                        }
                    }
                }

    }

    public void calculReussiteMoyenne(String apprenantID){
        Apprenant ap = getFormation().getApprenants().get(apprenantID);
        if(ap != null){
            ap.calculReussiteMoyenne();
        }
    }
    public void afficherReussiteMoyenne(String apprenantID){
        Apprenant apprenant = getFormation().getApprenants().get(apprenantID);
        System.out.println("Apprenant : "+apprenantID+" Nom : " +apprenant.getName()+" Moyenne : " +apprenant.getReussiteMoyenne());


    }
    public Apprenant nouveauApprenant(Personne person) {
        Apprenant apprenant = new Apprenant(GeneratorID.newPersonID(Apprenant.class),person.getName(),person.getPrenom(),person.getDateDeNaissance(),person.getLieuDeNaissance(),person.getAdresse());
        return apprenant;
    }

    public Compte nouveauCompteApprenant(Personne personne){
        Compte compte = GenerateurCompte.generateCompte(personne);
        return compte;
    }
    public List<Apprenant> trierApprenantsSelonMoyenne(boolean descending){
        /// ordre décroissant si descending == true sinon croissant
        List apprenants = Arrays.asList(getFormation().getApprenants().values().toArray());
        if(descending){
            Collections.sort(apprenants,Collections.reverseOrder());
            return apprenants;
        }
        Collections.sort(apprenants);
    return  apprenants;
    }
    public Apprenant getApprenantByID(String id) {
        return getApprenantByID(this.getFormation(),id);
    }
    public Apprenant getApprenantByID(Formation formation , String id) {

        return formation.getApprenants().get(id);
    }
}
