package QuestionBank;

import java.util.LinkedList;
import java.util.Scanner;

public class BankFacade {

    //Variables
    private QuestionBank QuestionBank;
    private static BankFacade instance = null;
    private final Scanner scanner = new Scanner(System.in);

    //Constructor - enforce singleton pattern
    private BankFacade(){
        QuestionBank = QuestionBank.GetInstance();
    }
    public static BankFacade GetInstance() {
        if (instance == null) {
            instance = new BankFacade();
        }
        return instance;
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

}
