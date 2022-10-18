package QuestionBank;

import java.util.LinkedList;
import java.util.Scanner;
import static java.lang.System.exit;

public class UserInterface {
    //Make a simple CLI just to get some interface going for presentation on Thursday
    //Add a line to get user input within QuestionBank to decide what question to make
    //Just get some simple interactions going for now -- can switch to a GUI later
    public static void main(String[] args){
        System.out.println("Type 'exit', 'stop', or 'end' to close the program.");
        try {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("Commands:\n\t'add'\t\tto add a question" +
                        "\n\t'get all'\tto get all questions in bank");
                System.out.print("Input: ");
                String input = scanner.nextLine();
                if (input.equals("exit") || input.equals("end") || input.equals("stop")) {
                    scanner.close();
                    exit(0);
                }
                try {
                    boolean result = ParseInput(input);
                    if (!result) {
                        System.out.println("Your input was not a valid command. Please try again.");
                        System.out.println(input);
                    }
                } catch (Exception e) {
                    System.out.println("Your input was not understood. Please try again");
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean ParseInput(String input) {
        //Start by grabbing instance of BankFacade
        BankFacade facade = BankFacade.GetInstance();
        switch(input){
            case "add":
                facade.CreateQuestion();
                /*something*/ break;
            case "remove":
                /*something*/ break;
            case "get all":
                LinkedList<Questions> questions = facade.GetQuestions();
                for (Questions q : questions) {
                    System.out.println(q);
                }
                /*something*/ break;
            default:
                return false;
        }
        return true;
    }
}
