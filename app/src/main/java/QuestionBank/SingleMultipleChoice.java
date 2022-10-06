package QuestionBank;

import java.util.LinkedList;

public class SingleMultipleChoice extends Questions{
    //Fields

    //Inherited Fields
    private int IdNumber;
    private String Question;
    private LinkedList<String> CorrectAnswers;
    private LinkedList<String> PossibleAnswers;
    private LinkedList<String> Tags;

    //Constructor
    protected SingleMultipleChoice(int idNumber, String question, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        super(idNumber, question, correctAnswers, possibleAnswers);
    }

    //Methods

}
