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


    protected int GetIdNumber(){return this.IdNumber;}
    protected String GetQuestion(){return this.Question;}
    protected LinkedList<String> GetCorrectAnswer(){return this.CorrectAnswers;}
    protected LinkedList<String> GetPossibleAnswers(){return this.PossibleAnswers;}
    protected LinkedList<String> GetTags(){return this.Tags;}
    protected boolean SetQuestion(String question){
        Question = question;
        return true;
    }
    protected boolean AddPossibleAnswer(String answer){
        for(String ans : PossibleAnswers){
            if (ans == answer){return false;} //If already identical answer, then return false without adding
        }
        return PossibleAnswers.add(answer);
    }
    protected boolean RemoveAnswer(String answer){
        CorrectAnswers.remove(answer);
        return PossibleAnswers.remove(answer);
    }
    protected boolean AddCorrectAnswer(String CorrectAnswer){
        AddPossibleAnswer(CorrectAnswer); //I decided to have it automatically add the answer to PossibleAnswers
        //the AddPossibleAnswer method will catch if there is already an identical answer and ignore it
        return CorrectAnswers.add(CorrectAnswer);
    }
    protected boolean RemoveCorrectAnswer(String answer){
        return CorrectAnswers.remove(answer);
    }
    protected boolean AddTag(String tagName){
        return Tags.add(tagName);
    }
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
