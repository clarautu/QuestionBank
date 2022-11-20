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

    public LinkedList<Questions> getQuestions() {return questions;}

    public LinkedList<Tag> getTagsLists() {return tagsLists;}

    public Boolean isValid() {return valid;}

    public void setValid(Boolean set) {valid = set;}

    public void setQuestions(LinkedList<Questions> setQuestions) {questions = setQuestions;}

    public void setTagsLists(LinkedList<Tag> setTagsLists) {tagsLists = setTagsLists;}
}
