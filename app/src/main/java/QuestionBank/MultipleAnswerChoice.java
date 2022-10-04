package QuestionBank;

import java.util.LinkedList;
import java.util.List;

public class MultipleAnswerChoice extends Questions {
    //Fields
    private List<String> OtherAnswer;

    //Inherited fields
    private int IdNumber;
    private String Question;
    private LinkedList<String> CorrectAnswers;
    private LinkedList<String> PossibleAnswers;
    private LinkedList<String> Tags;

    //Constructor
    protected MultipleAnswerChoice(int idNumber, String question, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        super(idNumber, question, correctAnswers, possibleAnswers);
    }

    //Methods
    public LinkedList GetOtherAnswers() {
        return null;
    }

    //Inherited Methods
    protected int GetIdNumber(){return this.IdNumber;}
    protected String GetQuestion(){return this.Question;}
    //Start of 'Might be unnecessary'
    protected LinkedList<String> GetCorrectAnswer(){return this.CorrectAnswers;}
    protected LinkedList<String> GetPossibleAnswers(){return this.PossibleAnswers;}
    //End
    protected LinkedList<String> GetTags(){return this.Tags;}
    protected boolean AddTag(String tagName){return true;}
    protected boolean SetQuestion(String question){
        Question = question;
        return true;
    }
    protected boolean AddPossibleAnswer(String answer){
        PossibleAnswers.add(answer);
        return true;
    }
    protected boolean RemoveAnswer(String answer){
        PossibleAnswers.remove(answer); //gonna be honest, don't know if this works, or if i need to do a search
        return true;
    }
    protected boolean SetCorrectAnswers(LinkedList<String> CorrectAnswers){
        this.CorrectAnswers = CorrectAnswers;
        return true;
    }
    protected boolean RemoveTag(String tagName){
        Tags.remove(tagName); //Not sure if this will work
        return true;
    }

}
