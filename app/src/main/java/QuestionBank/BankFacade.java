package QuestionBank;

import java.util.LinkedList;


/**
 * Facade for meshing front-end to back-end with QuestionBank class.
 * This class will also provide a state manager.
 * @Author Hunter
 * @Version 1.0
 */
public class BankFacade {



    //Variables
    private static BankFacade FacadeInstance;
    private QuestionBank QuestionBank;



    //Constructors
    private BankFacade() {
        QuestionBank =new QuestionBank();
    }

    //Singleton instance
    public static BankFacade GetInstance(){
        if (FacadeInstance == null){ //if there is no instance available... create new one
            FacadeInstance = new BankFacade();
        }
        return FacadeInstance;
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
