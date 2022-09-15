package QuestionBank;

import java.util.LinkedList;

public abstract class Questions {
    private int IdNumber;
    private String Question;
    private LinkedList<String> CorrectAnswer;
    private LinkedList<String> PossibleAnswers;
    private LinkedList<String> Tags;

    protected Questions(int idNumber, String question, LinkedList<String> correctAnswer, LinkedList<String> possibleAnswers) {
        this.IdNumber = idNumber;
        this.Question = question;
        this.CorrectAnswer = correctAnswer;
        this.PossibleAnswers = possibleAnswers;
    }

    protected int GetIdNumber(){return this.IdNumber;}
    protected String GetQuestion(){return this.Question;}
    //Start of 'Might be unnecessary'
    protected LinkedList<String> GetCorrectAnswer(){return this.CorrectAnswer;}
    protected LinkedList<String> GetPossibleAnswers(){return this.PossibleAnswers;}
    //End
    protected LinkedList<String> GetTags(){return this.Tags;}
    protected boolean AddTag(String tagName){return true;}
}
