package QuestionBank;

import java.util.LinkedList;

public class SingleMultipleChoice extends Questions{
    //Fields

    //Constructor
    protected SingleMultipleChoice(int idNumber, String question, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        super(idNumber, question, correctAnswers, possibleAnswers);
    }

    //Methods
    /**
     * Method that updates the correct answer and removes the old one from the question
     * @param correctAnswer The new correct answer for the question
     */
    @Override protected boolean AddCorrectAnswer(String correctAnswer) {
        String oldAnswer = this.GetCorrectAnswer().pop();
        return super.AddCorrectAnswer(correctAnswer);
    }
}
