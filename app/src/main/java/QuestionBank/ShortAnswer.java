package QuestionBank;

import java.util.LinkedList;

public class ShortAnswer extends Questions{
    //Fields
    private String Prompt;

    //Constructor
    protected ShortAnswer(int idNumber, String question) {
        super(idNumber, question, null, null);
    }

    //Methods
    /**
     * Method that returns the current short answer question.
     * @return the current short answer prompt.
     */
    public String getPrompt() {
        return this.GetQuestion();
    }

    /**
     * Method that updates the value of the current short answer question.
     * @param prompt The current short answer question.
     */
    public void setPrompt(String prompt) {
        this.SetQuestion(prompt);
    }
}
