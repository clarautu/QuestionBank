package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TagSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel panel;
    private String command;

    protected TagSubScreen(String command) {
        this.command = command;
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        frame = new JFrame("Tag Window for " + command);
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

        //Sub screen - Click on a tag
        /*
        Get all questions of a particular tag
        Remove a tag
         */

        //Create buttons
        JButton getQuestionsButton = new JButton("Get All Questions With Tag");
        JButton removeTageButton = new JButton("Remove Tag");

        //Set button commands
        getQuestionsButton.setActionCommand("GetQuestions");
        removeTageButton.setActionCommand("RemoveTag");

        //Add listeners to buttons
        getQuestionsButton.addActionListener(new ButtonClickListener());
        removeTageButton.addActionListener(new ButtonClickListener());

        //Add buttons to panel
        panel.add(getQuestionsButton);
        panel.add(removeTageButton);

        frame.setVisible(true);
    }

    /**
     * Private, internal class for defining Listeners for each button
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command){
                case "GetQuestions" -> GetQuestions();
                case "RemoveTag" -> RemoveTag();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void GetQuestions() {
            statusLabel.setText("Get questions with tag button clicked");
        }
        private void RemoveTag() {
            statusLabel.setText("Remove tag button clicked");
        }
    }
}
