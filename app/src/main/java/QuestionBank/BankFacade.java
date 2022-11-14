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

    public Boolean CreateQuestion() {
        // Get user input on what kind of question to make
        // CLI input code - will also have option to use GUI
        int typeChoice = GetUserQuestionType();
        String questionDescription = GetUserQuestionDescription();
        LinkedList<String> correctAnswers = GetUserCorrectAnswers();
        LinkedList<String> possibleAnswers = GetUserPossibleAnswers();

        return QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers);
    }

    public boolean RemoveQuestion(int id) {
        return QuestionBank.RemoveQuestion(id);
    }

    public boolean RemoveTag(String tagName) {
        return QuestionBank.RemoveTag(tagName);
    }

    private QuestionBank GetQuestionBank() {return QuestionBank;}

    public Questions GetQuestion(int id) {
        return QuestionBank.GetQuestion(id);
    }

    public LinkedList<Questions> GetTaggedQuestions(String tagName) {
        return QuestionBank.GetTaggedQuestions(tagName);
    }

    /**
     * Method that gets user input on what kind of question to make
     * @return Int corresponding to what kind of question to make
     */
    private int GetUserQuestionType() {
        int typeChoice = -1;
        while (true) {
            try {
                System.out.println("Please select what kind of question:\n" +
                        "enter 1 for multiple choice with only one answer\n" +
                        "enter 2 for multiple choice with several answers\n" +
                        "enter 3 for short answer");
                System.out.print("Your Choice: ");
                String input = scanner.nextLine();
                try {
                    typeChoice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter only a number.");
                    typeChoice = -1;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (typeChoice == 1 || typeChoice == 2 || typeChoice == 3) {
                break; // break our of while loop
            }
        }
        return typeChoice;
    }

    /**
     * Method that gets user input for the question description
     * @return String of question description
     */
    private String GetUserQuestionDescription() {
        String description = null;
        while (true) {
            try {
                System.out.println("Please enter the question description");
                System.out.print("Description: ");
                description = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (description != null) {
                break; //Break out of while loop
            }
        }
        return description;
    }

    /**
     * Method that gets user input for the correct answers to the question
     * @return A list of correct answers for the question
     */
    private LinkedList<String> GetUserCorrectAnswers() {
        LinkedList<String> correctAnswers = new LinkedList<>();
        while (true) {
            String input = null;
            try {
                System.out.println("Please add correct answer:\n" +
                        "enter 'end' when you are finished adding answers");
                System.out.print("Correct Answer: ");
                input = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (input.equals("end") || input.equals("End")) {
                break; // break out of while loop
            }
            correctAnswers.add(input);
        }
        return correctAnswers;
    }

    /**
     * Method that gets user input for possible answers for the question
     * @return A list of possible answers
     */
    private LinkedList<String> GetUserPossibleAnswers() {
        LinkedList<String> possibleAnswers = new LinkedList<>();
        while (true) {
            String input = null;
            try {
                System.out.println("Please a possible answer:\n" +
                        "enter 'end' when you are finished adding answers");
                System.out.print("Incorrect Answer: ");
                input = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (input.equals("end") || input.equals("End")) {
                break; // break out of while loop
            }
            possibleAnswers.add(input);
        }
        return possibleAnswers;
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
        stateManager.loadData(file, stateObject);
        if (stateObject.isValid()) {
            QuestionBank.setState(stateObject);
        }
        return loaded;
    }

}
