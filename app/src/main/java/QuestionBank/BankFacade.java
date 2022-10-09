package QuestionBank;

import java.util.LinkedList;

public class BankFacade {

    //Variables
    private QuestionBank QuestionBank;


    //Constructors
    public BankFacade(){
        QuestionBank = new QuestionBank();
    }

    //Methods
    public LinkedList<Questions> GetQuestions() {
        return QuestionBank.GetQuestions();
    }

    public LinkedList<Tag> GetTagsList() {
        return QuestionBank.GetTagsList();
    }

    public Boolean CreateQuestion() {   //I believe that this should take as arguments: question-String, correctAnswer-String, possibleAnswer-String. If we are passing down.
        return QuestionBank.CreateQuestion();
    }

    public boolean RemoveQuestion(int id) {
        return QuestionBank.RemoveQuestion(id);
    }

    public Questions GetQuestion(int id) {
        return QuestionBank.GetQuestion(id);
    }

    public LinkedList<Questions> GetTaggedQuestions(String tagName) {
        return QuestionBank.GetTaggedQuestions(tagName);
    }

}
