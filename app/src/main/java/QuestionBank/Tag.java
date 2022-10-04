package QuestionBank;

import java.util.LinkedList;

public class Tag {

    //Attributes
    private String TagName;

    private LinkedList<Integer> QuestionsOfTagType;

    public Tag(LinkedList<Integer> questionsOfTagType) {
        QuestionsOfTagType = questionsOfTagType;
    }



    //Methods
    public String GetTagName() { return this.TagName;}

    public LinkedList<Integer> GetQuestionsOfTagType(){
        return null;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public void setQuestionsOfTagType(LinkedList<Integer> questionsOfTagType) {
        QuestionsOfTagType = questionsOfTagType;
    }
}

