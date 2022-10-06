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
    }

    protected int GetIdNumber(){return this.IdNumber;}
    protected String GetQuestion(){return this.Question;}
    //Start of 'Might be unnecessary'
    protected LinkedList<String> GetCorrectAnswer(){return this.CorrectAnswers;}
    protected LinkedList<String> GetPossibleAnswers(){return this.PossibleAnswers;}
    //End
    protected LinkedList<String> GetTags(){return this.Tags;}
    protected boolean SetQuestion(String question){
        Question = question;
        return true;
    }
    protected boolean AddPossibleAnswer(String answer){
        return PossibleAnswers.add(answer);
    }
    protected boolean RemoveAnswer(String answer){
        CorrectAnswers.remove(answer);
        return PossibleAnswers.remove(answer);
    }
    protected boolean SetCorrectAnswers(LinkedList<String> CorrectAnswers){
        this.CorrectAnswers = new LinkedList<>(CorrectAnswers);
        return true;
    }
    protected boolean AddTag(String tagName){
        return Tags.add(tagName);
    }
    protected boolean RemoveTag(String tagName){
        return Tags.remove(tagName);
    }
}
