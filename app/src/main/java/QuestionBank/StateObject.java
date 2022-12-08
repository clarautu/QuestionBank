package QuestionBank;

import java.util.LinkedList;

/**
 * Used a vessel for game state info to be saved as a file
 */
public class StateObject {

    //Variables // This all needs to be filled out for saving to retain a state
    private LinkedList<Questions> questions;

    private LinkedList<Tag> tagsLists;

    private boolean valid = false;

    // Methods

    /**
     * Gets a linked list of questions
     * @return Linked list of type Questions
     */
    public LinkedList<Questions> getQuestions() {return questions;}

    /**
     * Gets a linked list of tags
     * @return Links list of type Tag
     */
    public LinkedList<Tag> getTagsLists() {return tagsLists;}

    /**
     * Checks if object is valid
     * @return a boolean to confirm validation
     */
    public Boolean isValid() {return valid;}

    /**
     * Sets a value to valid
     * @param set boolean value to set true
     */
    public void setValid(Boolean set) {valid = set;}

    /**
     * Sets the questions to be saved
     * @param setQuestions the set of questions to be saved
     */
    public void setQuestions(LinkedList<Questions> setQuestions) {questions = setQuestions;}

    /**
     * Sets the tags to be saved
     * @param setTagsLists the set of tags to be saved
     */
    public void setTagsLists(LinkedList<Tag> setTagsLists) {tagsLists = setTagsLists;}
}
