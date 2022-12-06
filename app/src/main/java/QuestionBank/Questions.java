package QuestionBank;

import java.util.LinkedList;

public abstract class Questions {
    private int IdNumber;
    private String Question;
    private LinkedList<String> CorrectAnswers;
    private LinkedList<String> PossibleAnswers;
    private LinkedList<String> Tags;

    protected Questions(int idNumber, String question, LinkedList<String> correctAnswer, LinkedList<String> possibleAnswers) {
        this.IdNumber = idNumber;
        this.Question = question;
        this.CorrectAnswers = correctAnswer;
        this.PossibleAnswers = possibleAnswers;
        this.Tags = new LinkedList<>();
    }

    public Questions() {} // no args constructor for custom gson deserialization builder

    /**
     * Method that gets the ID number for the question
     * @return The ID number for this question
     */
    protected int GetIdNumber(){return this.IdNumber;}

    /**
     * Method that gets the prompt for this question
     * @return The prompt/description of this question
     */
    protected String GetQuestion(){return this.Question;}

    /**
     * Method that gets all the correct answers for this question
     * @return A list of correct answers for this question
     */
    protected LinkedList<String> GetCorrectAnswer(){return this.CorrectAnswers;}

    /**
     * Method that gets all the possible answers for this question
     * @return A list of possible answers for this question
     */
    protected LinkedList<String> GetPossibleAnswers(){return this.PossibleAnswers;}

    /**
     * Method that gets all the tags for this question
     * @return A list of all the tags for this question
     */
    protected LinkedList<String> GetTags(){return this.Tags;}

    /**
     * Method that updates the prompt for this question
     * @param question The new prompt
     * @return true if the update was successful; False otherwise
     */
    protected boolean SetQuestion(String question){
        Question = question;
        return true;
    }

    /**
     * Method that adds a new possible answer to the question
     * @param answer The new possible answer to add
     * @return True if it was added successfully; False otherwise
     */
    protected boolean AddPossibleAnswer(String answer){
        for(String ans : PossibleAnswers){
            if (ans == answer){return false;} //If already identical answer, then return false without adding
        }
        return PossibleAnswers.add(answer);
    }

    /**
     * Method that adds a new correct answer to the question
     * @param CorrectAnswer The new correct answer to add
     * @return True if it was added successfully; False otherwise
     */
    protected boolean AddCorrectAnswer(String CorrectAnswer){
        AddPossibleAnswer(CorrectAnswer); //I decided to have it automatically add the answer to PossibleAnswers
        //the AddPossibleAnswer method will catch if there is already an identical answer and ignore it
        return CorrectAnswers.add(CorrectAnswer);
    }

    /**
     * Method that adds a tag to the question
     * @param tagName The name of the tag to add
     * @return True if it was added successfully; False otherwise
     */
    protected boolean AddTag(String tagName){
        return Tags.add(tagName);
    }

    /**
     * Method that removes a specified tag from the question
     * @param tagName The name of the tag to be removed
     * @return True if it was updated successfully; False otherwise
     */
    protected boolean RemoveTag(String tagName){
        return Tags.remove(tagName);
    }

    @Override
    public String toString() {
        return "Question{" +
                "IdNumber=" + IdNumber +
                ", Question='" + Question + '\'' +
                ", CorrectAnswers=" + CorrectAnswers +
                ", PossibleAnswers=" + PossibleAnswers +
                ", Tags=" + Tags +
                '}';
    }
}
