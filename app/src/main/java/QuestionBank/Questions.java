package QuestionBank;

import java.util.LinkedList;
import java.util.List;

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
    }

    protected int GetIdNumber(){return this.IdNumber;}
    protected String GetQuestion(){return this.Question;}
    //Start of 'Might be unnecessary'
    protected LinkedList<String> GetCorrectAnswer(){return this.CorrectAnswers;}
    protected LinkedList<String> GetPossibleAnswers(){return this.PossibleAnswers;}
    //End
    protected LinkedList<String> GetTags(){return this.Tags;}
    protected boolean AddTag(String tagName){return true;}
    protected boolean RemoveTag(String tagName){return true;}
    protected boolean SetQuestion(String question){return true;}
    protected boolean AddPossibleAnswer(String answer){return true;}
    protected boolean RemoveAnswer(String answer){return true;}
    protected boolean SetCorrectAnswers(LinkedList<String> CorrectAnswers){return true;}
}
