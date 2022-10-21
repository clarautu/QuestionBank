package QuestionBank;

import java.util.LinkedList;
import java.util.Scanner;

public class QuestionBank {
    private static QuestionBank instance = null;
    private LinkedList<Questions> Questions;
    private LinkedList<Tag> TagsLists;

    //Constructor - enforce singleton pattern
    private QuestionBank(){
        this.Questions = new LinkedList<Questions>();
        this.TagsLists = new LinkedList<Tag>();
    }
    public static QuestionBank GetInstance() {
        if (instance == null) {
            instance = new QuestionBank();
        }
        return instance;
    }

    /**
     * Method that returns the list of all questions
     * @return A list containing all questions
     */
    public LinkedList<Questions> GetQuestions(){
        return this.Questions;
    }

    /**
     * Method that returns a list of all tags
     * @return A list containing all tags
     */
    public LinkedList GetTagsList(){
        return this.TagsLists;
    }

    /**
     * Method that creates a question, based on user input
     * @return True if the question was created successfully; False otherwise
     */
    public Boolean CreateQuestion(int typeChoice, String questionDescription, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers){
        int lastUsedId; // Variable to hold last used id number for a question
        if (this.Questions.peekLast() == null) { // Check if any questions have been added
            // If no questions yet, set lastUsedId to -1
            lastUsedId = -1;
        } else { // There is at least one question
            // Set lastUsedId equal to the last question's id number
            lastUsedId = this.Questions.peekLast().GetIdNumber();
        }
        int currentId = lastUsedId + 1;

        // Get user input on what kind of question to make
        // Temporary CLI input code - will be replaced when we upgrade to a GUI
        //int typeChoice = GetUserQuestionType();
        //String questionDescription = GetUserQuestionDescription();
        //LinkedList<String> correctAnswers = GetUserCorrectAnswers();
        //LinkedList<String> possibleAnswers = GetUserPossibleAnswers();


        // Create question here
        Questions question = MakeQuestionBasedOnChoice(currentId, typeChoice, questionDescription, correctAnswers, possibleAnswers);
        // will need to prompt user for what kind of question to be created
        // and what tag to list it as
        // If no tag specified, have a default tag?

        // Once done, add new question to this.Questions
        return this.Questions.add(question);
    }

    /**
     * Method that gets user input on what kind of question to make
     * @return Int corresponding to what kind of question to make
     */
    /*
    private int GetUserQuestionType() {
        boolean incorrectInput = true;
        int typeChoice = -1;
        while (incorrectInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please select what kind of question:\n" +
                        "enter 1 for multiple choice with only one answer\n" +
                        "enter 2 for multiple choice with several answers\n" +
                        "enter 3 for short answer\n" +
                        "Your choice: ");
                String input = scanner.nextLine();
                scanner.close();
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
                incorrectInput = false; // break our of while loop
            }
        }
        return typeChoice;
    }

    /**
     * Method that gets user input for the question description
     * @return String of question description
     */
    /*
    private String GetUserQuestionDescription() {
        boolean incorrectInput = true;
        String description = null;
        while (incorrectInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter the question description:\n" +
                        "Description: ");
                description = scanner.nextLine();
                scanner.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return description;
    }

    /**
     * Method that gets user input for the correct answers to the question
     * @return A list of correct answers for the question
     */
    /*
    private LinkedList<String> GetUserCorrectAnswers() {
        LinkedList<String> correctAnswers = new LinkedList<>();
        while (true) {
            String input = null;
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please a correct answer:\n" +
                        "enter 'end' when you are finished adding answers\n" +
                        "Answer: ");
                input = scanner.nextLine();
                scanner.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (input == "end" || input == "End") {
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
    /*
    private LinkedList<String> GetUserPossibleAnswers() {
        LinkedList<String> possibleAnswers = new LinkedList<>();
        while (true) {
            String input = null;
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please a possible answer:\n" +
                        "enter 'end' when you are finished adding answers\n" +
                        "Answer: ");
                input = scanner.nextLine();
                scanner.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (input == "end" || input == "End") {
                break; // break out of while loop
            }
            possibleAnswers.add(input);
        }
        return possibleAnswers;
    }
    */

    /**
     * Method that makes a question based on specified inputs
     * @param id The id number of the question to be made
     * @param typeChoice The type of question to make - coded answer
     * @param questionDescription The description/prompt for the question
     * @param correctAnswers List of correct answers for the question
     * @param possibleAnswers List of possible answers for the question
     * @return The newly made question
     */
    private Questions MakeQuestionBasedOnChoice(int id, int typeChoice, String questionDescription, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers) {
        Questions question = null;
        switch(typeChoice) {
            case 1 :
                // make MultipleAnswerChoice
                question = new MultipleAnswerChoice(id, questionDescription, correctAnswers, possibleAnswers);
                break;
            case 2 :
                // make SingleAnswerChoice
                question = new SingleMultipleChoice(id, questionDescription, correctAnswers, possibleAnswers);
                break;
            case 3 :
                // make ShortAnswer
                question = new ShortAnswer(id, questionDescription);
                break;
            default :
                return null;
        }
        return question;
    }

    /**
     * Method that finds and removes a Question with a specified ID number
     * @param ID The ID number of the question being searched for
     * @return True if the question was successfully removed; False if question not found
     */
    public Boolean RemoveQuestion(int ID){
        for (Questions q : this.Questions) {
            if (q.GetIdNumber() == ID) {
                return Questions.remove(q);
            }
        }
        return false;
    }

    /**
     * Method that finds and returns a Question with a specified ID number
     * @param ID The ID number of the question being searched for
     * @return The question with the specified ID number; Null if question not found
     */
    public Questions GetQuestion(int ID){
        for (Questions q : this.Questions) {
            if (q.GetIdNumber() == ID) {
                return q;
            }
        }
        return null;
    }

    /**
     * Method that finds and returns all questions of a specified tag
     * @param tagname The name of the tag to search for questions of
     * @return A list of all questions with the specified tag; Null if tag not found
     */
    public LinkedList<Questions> GetTaggedQuestions(String tagname) {
        LinkedList<Questions> taggedQuestions = new LinkedList<Questions>();

        // Search through this.TagsList to find the specified tag
        for (Tag t : this.TagsLists) {
            if (t.GetTagName() == tagname) { // Found right tag
                // List of id numbers for questions that have the specified tag
                LinkedList<Integer> ids = t.GetQuestionsOfTagType();
                // Search for questions with id numbers in ids, then add questions to taggedQuestions
                for (int id : ids) {
                    for (Questions q : this.Questions) {
                        if (q.GetIdNumber() == id) {
                            taggedQuestions.add(q);
                            // Break out of internal for loop, as the question with the id number has been found
                            break;
                        }
                    }
                }
                return taggedQuestions;
            }
        }
        // Tag not found, return null
        return null;
    }

    /**
     * Method that removes a specified tag from all questions that have it and from the list of tags
     * @param tagName The name of the tag to be removed
     * @return True if found and removed correctly; False otherwise
     */
    public boolean RemoveTag(String tagName) {
        // Search through this.TagsList to find specified tag
        for (Tag t : this.TagsLists) {
            if(t.GetTagName() == tagName) { // Found tag
                // Get a list of id numbers of questions that have this tag
                LinkedList<Integer> ids = t.GetQuestionsOfTagType();
                // Loop through ids
                for (int i : ids) {
                    // Find the question that has the specified tag number
                    for (Questions q : this.Questions) {
                        if (q.GetIdNumber() == i) { // Found question
                            q.RemoveTag(tagName); // Remove tag from question
                            break; // Break out of internal for loop
                        }
                    }
                }
                // Remove tag from this.TagsList
                return this.TagsLists.remove(t);
            }
        }
        // Specified tag not found
        return false;
    }
}
