package QuestionBank;

import java.io.*;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.flogger.FluentLogger;

/**
 * Class to push data in and out of the project
 * @version 1.0
 */
public class StateManager {

    //Variables
    Gson gson = new GsonBuilder().registerTypeAdapter(Questions.class, new GsonInstanceCreator()).create();

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

//    Methods
//    For Saving

    /**
     * Saves with the current state
     * @param file full path name of the directory to save to
     * @param fileName specific file name to make/save
     * @param state current state to save
     * @return a boolean to confirm save
     */
    public boolean saveData(File file, String fileName, StateObject state){
        boolean saved = false;
        try {
            if (file.isDirectory()) {
                file = this.createFile(file + "/" + fileName);
                FileWriter writer = new FileWriter(file, true);
                String json = gson.toJson(state);
                writer.write(json);
                writer.write("\n");
                logger.atInfo().log("Wrote to file: %s", file);
                writer.close();
                saved = true;
            }
            else {
                logger.atWarning().log("Problem saving.");
            }
        }
        catch (IOException e){
            logger.atWarning().log("An error occurred while saving.");
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
                logger.atInfo().log("File created at: %s", file);
            }
            else {
                logger.atInfo().log("File exists and will be overwritten at: %s", file);
            }
        } catch (IOException e) {
            logger.atWarning().log("An error occurred creating file at: %s", file);
            e.printStackTrace();
        }
        return write_file;
    }


    //For loading

    /**
     * Gets the game state from a given file.
     * @param file to get from
     * @return the QuestionBank with state
     * @throws FileNotFoundException
     */
    public StateObject loadData(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StateObject newState = new StateObject();
        if (scanner.hasNext()) {
            String json = scanner.nextLine();
            newState = gson.fromJson(json, StateObject.class);
            scanner.close();
            logger.atInfo().log("File read at: %s", file);
            newState.setValid(true);
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
