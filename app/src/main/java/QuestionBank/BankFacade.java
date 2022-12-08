package QuestionBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import com.google.common.flogger.FluentLogger;


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

    private StateObject stateObject = new StateObject();

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();


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

    /**
     * Method that gets all question in the bank
     * @return A list of all questions
     */
    public LinkedList<Questions> GetQuestions() {
        return QuestionBank.GetQuestions();
    }

    /**
     * Method that gets all tags in the bank
     * @return A list of all tags
     */
    public LinkedList<Tag> GetTagsList() {
        return QuestionBank.GetTagsList();
    }

    /**
     * Method that creates a question
     * @param typeChoice 1 for multianswer, 2 for single answer, 3 for short answer
     * @param questionDescription The prompt for the question
     * @param correctAnswers A list of correct answers to the question - can be null if short answer
     * @param possibleAnswers A list of possible answers to the question - can be null if short answer
     * @return True if the question was created successfully; False otherwise
     */
    public Boolean CreateQuestion(int typeChoice, String questionDescription, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        return QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers);
    }

    /**
     * Overload to allow adding tags
     * @param typeChoice
     * @param questionDescription
     * @param correctAnswers
     * @param possibleAnswers
     * @param tagsList
     * @return
     */
    public Boolean CreateQuestion(int typeChoice, String questionDescription, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers, LinkedList<String> tagsList) {
        Boolean success = QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers);
        Questions question = QuestionBank.GetQuestions().peekLast();
        for (String tag : tagsList){
            QuestionBank.AddTagToQuestion(question.GetIdNumber(),tag);
        }
        return success;
    }

    /**
     * Method that adds a tag to a specified question
     * @param idNumber The ID number of the question
     * @param tagName The name of the tag to add
     */
    public void AddTagToQuestion(int idNumber, String tagName) {
        QuestionBank.GetInstance().AddTagToQuestion(idNumber, tagName);
    }

    /**
     * Method to remove a specified question from the bank
     * @param id The ID number of the question
     * @return True if the question was removed successfully; False otherwise
     */
    public boolean RemoveQuestion(int id) {
        return QuestionBank.RemoveQuestion(id);
    }

    /**
     * Method that removes a specified tag from the bank
     * @param tagName The name of the tag
     * @return True if the tag was removed successfully; False otherwise
     */
    public boolean RemoveTag(String tagName) {
        return QuestionBank.RemoveTag(tagName);
    }

    /**
     * Method that removes a specified tag from a specified question
     * @param questionID the ID of the question
     * @param tagName the name of the tag
     * @return True if the tag was removed successfully
     */
    public boolean RemoveTagFromQuestion(int questionID, String tagName){
        return QuestionBank.GetQuestion(questionID).RemoveTag(tagName);
    }

    // needed for out-dated save implementation
    private QuestionBank GetQuestionBank() {return QuestionBank;}

    /**
     * Method that gets a specified question
     * @param id The ID number of the question
     * @return The question specified
     */
    public Questions GetQuestion(int id) {
        return QuestionBank.GetQuestion(id);
    }

    /**
     * Method that gets all questions that have a specified tag
     * @param tagName The name of the tag
     * @return A list of questions with the tag
     */
    public LinkedList<Questions> GetTaggedQuestions(String tagName) {
        return QuestionBank.GetTaggedQuestions(tagName);
    }


    // Methods for save/load state

    /**
     * Method that sets the state of the stateObject
     */
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
            logger.atFinest().log("Valid state of file at: %s", file);
            loaded = true;
        }
        return loaded;
    }

    /**
     * Method that resets the bank and removes everything from it
     */
    public void resetBank() {
        QuestionBank.ResetBank();
    }

}
