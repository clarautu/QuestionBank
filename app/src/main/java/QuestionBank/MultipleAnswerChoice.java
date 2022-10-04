package QuestionBank;

import java.util.LinkedList;
import java.util.List;

public class MultipleAnswerChoice extends Questions {
    //Fields
    private LinkedList<String> OtherAnswer;

    //Inherited fields
    private int IdNumber;
    private String Question;
    private LinkedList<String> CorrectAnswers;
    private LinkedList<String> PossibleAnswers;
    private LinkedList<String> Tags;

    //Constructor
    protected MultipleAnswerChoice(int idNumber, String question, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        super(idNumber, question, correctAnswers, possibleAnswers);
        //need some more work there
    }

    //Methods
    public LinkedList GetOtherAnswers() {
        return GetOtherAnswers();
    }

    /* I left this here instead of deleting it in case it was needed still for some reason
    //Inherited Methods
    protected int GetIdNumber(){return this.IdNumber;}
    protected String GetQuestion(){return this.Question;}
    //Start of 'Might be unnecessary'
    protected LinkedList<String> GetCorrectAnswer(){return this.CorrectAnswers;}
    protected LinkedList<String> GetPossibleAnswers(){return this.PossibleAnswers;}
    //End
    protected LinkedList<String> GetTags(){return this.Tags;}
    protected boolean AddTag(String tagName){
        return Tags.add(tagName);
    }

     */
    protected boolean SetQuestion(String question){
        Question = question;
        return true;
    }
    protected boolean AddPossibleAnswer(String answer){
        return PossibleAnswers.add(answer);
    }
    protected boolean RemoveAnswer(String answer){
        return PossibleAnswers.remove(answer);
    }
    protected boolean SetCorrectAnswers(LinkedList<String> CorrectAnswers){
        this.CorrectAnswers = new LinkedList<>(CorrectAnswers);
        return true;
    }
    protected boolean RemoveTag(String tagName){
        return Tags.remove(tagName);
    }
}
