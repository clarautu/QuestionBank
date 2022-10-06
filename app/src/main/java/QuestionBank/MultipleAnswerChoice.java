package QuestionBank;

import java.util.LinkedList;

public class MultipleAnswerChoice extends Questions {
    //Fields

    //Inherited fields
    private int IdNumber;
    private String Question;
    private LinkedList<String> CorrectAnswers;
    private LinkedList<String> PossibleAnswers;
    private LinkedList<String> Tags;

    //Constructor
    protected MultipleAnswerChoice(int idNumber, String question, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        super(idNumber, question, correctAnswers, possibleAnswers);
    }

    //Methods

}
