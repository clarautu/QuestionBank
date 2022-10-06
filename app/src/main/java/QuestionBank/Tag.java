package QuestionBank;

import java.util.LinkedList;

public class Tag {

    //Attributes
    private String TagName;

    private LinkedList<Integer> QuestionsOfTagType;

    //Constructor
    public Tag(LinkedList<Integer> questionsOfTagType) {
        QuestionsOfTagType = questionsOfTagType;
    }

    //Methods

    /**
     * Method that returns the current tag name.
     * @return Current tag name.
     */
    public String GetTagName() { return this.TagName;}

    /**
     * Method that returns list with a tag related to an integer
     * @return A list containing a specific tag.
     */
    public LinkedList<Integer> GetQuestionsOfTagType(){
        return this.QuestionsOfTagType;
    }

    /**
     * Method that updates the current tag name.
     * @param tagName The name of the tag being updated.
     */
    public void setTagName(String tagName) {
        TagName = tagName;
    }

    /**
     * Method that will update a specific lists tag, if needed.
     * @param questionsOfTagType list of specific tags.
     */
    public void setQuestionsOfTagType(LinkedList<Integer> questionsOfTagType) {
        QuestionsOfTagType = questionsOfTagType;
    }
}

