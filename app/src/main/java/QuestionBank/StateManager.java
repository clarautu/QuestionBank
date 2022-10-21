package QuestionBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;

public class StateManager {

    //Variables
    Gson gson = new Gson();

    //Methods
    //For Saving

    /**
     * Saves with the current state
     * @param file full path name of the directory to save to
     * @param fileName specific file name to make/save
     * @param state current state to save
     * @return a boolean to confirm save
     */
    public boolean saveData(File file, String fileName, QuestionBank state){
        boolean saved = false;
        try {
            if (file.isDirectory()) {
                file = this.createFile(file + "/" + fileName);
                FileWriter writer = new FileWriter(file, true);
                String json = gson.toJson(state);
                writer.write(json);
                writer.write("\n");
                System.out.println("Wrote to file: " + file);
                writer.close();
                saved = true;
            }
            else {
                System.out.println("Problem saving.");
            }
        }
        catch (IOException e){
            System.out.println("An error occurred while saving.");
            e.printStackTrace();
        }
        return saved;
    }

    /**
     * Used to create a file if none already exists
     * @param file name
     * @return returns the file to save to
     */
    private File createFile(String file) {
        File write_file = new File(file);
        try {
            if (write_file.createNewFile()) {
                System.out.println("File create: " + write_file.getName());
            } else {
                System.out.println("File may already exist.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating a file.");
            e.printStackTrace();
        }
        return write_file;
    }


    //For loading

    /**
     * Gets the game state from a given file.
     * @param file to get from
     * @return the stateObject to load from
     * @throws FileNotFoundException
     */
    public QuestionBank loadData(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        QuestionBank newState = new QuestionBank();
        if (scanner.hasNext()) {
            String json = scanner.nextLine();
            newState = gson.fromJson(json, QuestionBank.class);
            scanner.close();
            System.out.println("File read.");
        }
        return newState;
    }

    // For exporting to database
    public void exportToDatabase() {
    }

    // For Importing from database
    public void importFromDatabase() {

    }
}
