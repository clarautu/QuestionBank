package QuestionBank;

public class ShortAnswer {
    //Fields
    private String Prompt;

    //Methods
    /**
     * Method that returns the current short answer question.
     * @return the current short answer prompt.
     */
    public String getPrompt() {
        return Prompt;
    }

    /**
     * Method that updates the value of the current short answer question.
     * @param prompt The current short answer question.
     */
    public void setPrompt(String prompt) {
        Prompt = prompt;
    }
}
