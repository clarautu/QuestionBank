package QuestionBank;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class UI {
    private static UI instance = null;
    private JFrame mainFrame;
    private JLabel headerlabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    //Constructor - enforce singleton pattern
    private UI() {
        PrepareGUI();
    }
    protected static UI GetInstance() {
        if (instance == null) {
            instance = new UI();
        }
        return instance;
    }

    public static void main(String[] args) {
        UI gui = GetInstance();
        gui.ShowWindow();
    }

    /**
     * Method that prepares the GUI
     */
    private void PrepareGUI(){
        mainFrame = new JFrame("Question Bank Window");
        mainFrame.setSize(800, 800);
        mainFrame.setLayout(new GridLayout(3, 1));

        //Create labels for the window
        headerlabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        //Shut down the program upon closing the window
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        //Create a panel for housing the buttons
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        //Add the labels and panel to the frame
        mainFrame.add(headerlabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Method that does final prep work and shows the window
     */
    private void ShowWindow(){
        headerlabel.setText("What would you like to do?");

        //Initial screen
        /*
        Get a list of questions
        Make a question
        Get a list of tags
        Save the bank
        Load questions into bank
        Delete question bank
         */

        //Create buttons
        JButton seeAllQuestionsButton = new JButton("See all questions in bank");
        JButton addQuestionButton = new JButton("Add a new question");
        JButton seeAllTagsButton = new JButton("See all tags in bank");
        JButton saveBankButton = new JButton("Save the Bank");
        JButton loadQuestionsButton = new JButton("Load Questions");
        JButton deleteBankButton = new JButton("Delete Bank");

        //Set button commands
        seeAllQuestionsButton.setActionCommand("SeeAll");
        addQuestionButton.setActionCommand("AddNew");
        seeAllTagsButton.setActionCommand("SeeTags");
        saveBankButton.setActionCommand("SaveBank");
        loadQuestionsButton.setActionCommand("LoadQuestions");
        deleteBankButton.setActionCommand("DeleteBank");

        //Add listeners to buttons
        seeAllQuestionsButton.addActionListener(new ButtonClickListener());
        addQuestionButton.addActionListener(new ButtonClickListener());
        seeAllTagsButton.addActionListener(new ButtonClickListener());
        saveBankButton.addActionListener(new ButtonClickListener());
        loadQuestionsButton.addActionListener(new ButtonClickListener());
        deleteBankButton.addActionListener(new ButtonClickListener());

        //Add buttons to the panel in the frame
        controlPanel.add(seeAllQuestionsButton);
        controlPanel.add(addQuestionButton);
        controlPanel.add(seeAllTagsButton);
        controlPanel.add(saveBankButton);
        controlPanel.add(loadQuestionsButton);
        controlPanel.add(deleteBankButton);

        mainFrame.setVisible(true);
    }

    /**
     * Method that hides the main screen
     */
    protected void Hide() {
        mainFrame.setVisible(false);
        mainFrame.setEnabled(false);
    }

    /**
     * Method that un-hides the main screen
     */
    protected void UnHide() {
        mainFrame.setVisible(true);
        mainFrame.setEnabled(true);
    }

    /**
     * Private, internal class for defining Listeners for each button
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command){
                case "SeeAll" -> SeeAll();
                case "AddNew" -> AddNew();
                case "SeeTags" -> SeeTags();
                case "SaveBank" -> SaveBank();
                case "LoadQuestions" -> LoadQuestions();
                case "DeleteBank" -> DeleteBank();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void SeeAll() {
            statusLabel.setText("See all questions button clicked.");
            new AllQuestionsSubScreen();
        }
        private void AddNew() {
            statusLabel.setText("Add new button clicked.");
            new AddQuestionSubScreen();
        }
        private void SeeTags() {
            statusLabel.setText("See all tags button clicked");
            new AllTagsSubScreen();
        }
        private void SaveBank() {
            statusLabel.setText("Save bank button clicked");
        }
        private void LoadQuestions() {
            statusLabel.setText("Load questions button clicked");
        }
        private void DeleteBank() {
            statusLabel.setText("Delete bank button clicked");
        }
    }
}
