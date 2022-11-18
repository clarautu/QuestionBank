package QuestionBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Facade for meshing front-end to back-end with QuestionBank class.
 * This class will also provide a state manager.
 * @version 1.0
 */
public class BankFacade {
    //Variables
    private final StateManager stateManager = new StateManager();
    private static BankFacade FacadeInstance = null;
    private QuestionBank QuestionBank;

    private Scanner scanner = new Scanner(System.in);

    private StateObject stateObject = new StateObject();

    //Constructors
    private BankFacade() {
        QuestionBank = QuestionBank.GetInstance();
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

    public Boolean CreateQuestion(int typeChoice, String questionDescription, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        return QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers);
    }

    public boolean RemoveQuestion(int id) {
        return QuestionBank.RemoveQuestion(id);
    }

    public boolean RemoveTag(String tagName) {
        return QuestionBank.RemoveTag(tagName);
    }

    // needed for out-dated save implementation
    private QuestionBank GetQuestionBank() {return QuestionBank;}

    public Questions GetQuestion(int id) {
        return QuestionBank.GetQuestion(id);
    }

    public LinkedList<Questions> GetTaggedQuestions(String tagName) {
        return QuestionBank.GetTaggedQuestions(tagName);
    }


    // Methods for save/load state

    public void setState() {
        stateObject.setQuestions(QuestionBank.GetQuestions());
        stateObject.setTagsLists(QuestionBank.GetTagsList());
        stateObject.setValid(true);
    }

    /**
     * Saves the game with the current state
     * @param filePath full path of directory to save at
     * @param fileName specific file name to save in the directory
     * @return a boolean to confirm save
     */
    public boolean save(File filePath, String fileName) {
        boolean saved = false;
        setState();
        if (stateObject.isValid()) {
            saved = stateManager.saveData(filePath, fileName, stateObject);
        }
        return saved;
    }


    /**
     * Used to load a game from a file
     * @param file to load from
     * @throws FileNotFoundException
     * @return boolean to confirm load
     */
    public boolean load(File file) throws FileNotFoundException {
        boolean loaded = false;
        stateObject = stateManager.loadData(file);

        if (stateObject.isValid()) {
            QuestionBank.setState(stateObject);
            System.out.println("state valid");
            loaded = true;
        }
        return loaded;
    }

    public void resetBank() {
        QuestionBank.ResetBank();
    }

}
