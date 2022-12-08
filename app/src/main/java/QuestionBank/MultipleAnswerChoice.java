package QuestionBank;

import java.util.LinkedList;

public class MultipleAnswerChoice extends Questions {
    //Fields

    //Constructor
    protected MultipleAnswerChoice(int idNumber, String question, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        super(idNumber, question, correctAnswers, possibleAnswers);
    }
}
