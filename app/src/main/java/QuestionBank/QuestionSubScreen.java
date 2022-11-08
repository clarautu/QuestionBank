package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel panel;
    private String command;

    protected QuestionSubScreen(String command) {
        this.command = command;
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        frame = new JFrame("Question Window for " + command);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 1));

        //Create labels for the window
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        //Create a panel for housing the buttons
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //Add the labels and panel to the frame
        frame.add(headerLabel);
        frame.add(panel);
        frame.add(statusLabel);

        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    /**
     * Method that does final prep work and shows the window
     */
    private void Show() {
        headerLabel.setText("What would you like to do?");

        //Sub screen - Click on a question
        /*
        Edit a question
        Delete a question
        Add a tag to a particular question
        Remove a tag from a particular question
         */

        //Create buttons
        JButton editQuestionButton = new JButton("Edit Question");
        JButton deleteQuestionButton = new JButton("Delete Question");
        JButton addTagButton = new JButton("Add Tag");
        JButton removeTagButton = new JButton("Remove Tag");

        //Set button commands
        editQuestionButton.setActionCommand("EditQuestion");
        deleteQuestionButton.setActionCommand("DeleteQuestion");
        addTagButton.setActionCommand("AddTag");
        removeTagButton.setActionCommand("RemoveTag");

        //Add listeners to buttons
        editQuestionButton.addActionListener(new ButtonClickListener());
        deleteQuestionButton.addActionListener(new ButtonClickListener());
        addTagButton.addActionListener(new ButtonClickListener());
        removeTagButton.addActionListener(new ButtonClickListener());

        //Add buttons to panel
        panel.add(editQuestionButton);
        panel.add(deleteQuestionButton);
        panel.add(addTagButton);
        panel.add(removeTagButton);

        frame.setVisible(true);
    }

    /**
     * Private, internal class for defining Listeners for each button
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command){
                case "EditQuestion" -> EditQuestion();
                case "DeleteQuestion" -> DeleteQuestion();
                case "AddTag" -> AddTag();
                case "RemoveTag" -> RemoveTag();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void EditQuestion() {
            statusLabel.setText("Edit question button clicked");
        }
        private void DeleteQuestion() {
            statusLabel.setText("Delete question button clicked");
        }
        private void AddTag() {
            statusLabel.setText("Add tag button clicked");
        }
        private void RemoveTag() {
            statusLabel.setText("Remove tag button clicked");
        }
    }
}
