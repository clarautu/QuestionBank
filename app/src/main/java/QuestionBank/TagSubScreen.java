package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class TagSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel panel;
    private JPanel bottomPanel;
    private String tagName;

    protected TagSubScreen(String command) {
        this.tagName = command;
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("Tag Window for tag: " + tagName);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 1));

        //Unhide the main screen on closing
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                UI.GetInstance().UnHide();
            }
        });

        //Create labels for the window
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        //Create a panel for housing the buttons
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //Create a panel for housing the cancel button
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

        //Make and add cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ButtonClickListener());
        bottomPanel.add(cancelButton);
        bottomPanel.add(Box.createHorizontalGlue());
        //Add status label to bottom panel - for testing
        bottomPanel.add(statusLabel);

        //Add the labels and panel to the frame
        frame.add(headerLabel);
        frame.add(panel);
        frame.add(bottomPanel);

        frame.setLocationRelativeTo(null);
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
                case "Cancel" -> Cancel();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void GetQuestions() {
            statusLabel.setText("Get questions with tag button clicked");
            LinkedList<Questions> taggedQuestions = BankFacade.GetInstance().GetTaggedQuestions(tagName);
            //Display these
        }
        private void RemoveTag() {
            statusLabel.setText("Remove tag button clicked");
            Boolean didItWork = BankFacade.GetInstance().RemoveTag(tagName);
            //Let user know if it worked, based on returned boolean
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            new AllTagsSubScreen();
        }
    }
}
