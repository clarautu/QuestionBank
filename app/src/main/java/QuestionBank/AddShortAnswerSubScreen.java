package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddShortAnswerSubScreen implements FocusListener{
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel panel;
    private JPanel bottomPanel;
    private JTextArea getPrompt;
    private String prompt;

    protected AddShortAnswerSubScreen() {
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("Add a short answer question");
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(4, 1));

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
        statusLabel.isFocusOwner();

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

        //Make and add the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setActionCommand("Submit");
        submitButton.addActionListener(new ButtonClickListener());
        bottomPanel.add(submitButton);

        //Add the labels and panel to the frame
        frame.add(headerLabel);
        frame.add(panel);
        frame.add(bottomPanel);
        frame.add(statusLabel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Method that does final prep work and shows the window
     */
    private void Show() {
        headerLabel.setText("Enter the prompt and hit 'Submit'");

        //Create text box
        getPrompt = new JTextArea(10, 30);
        getPrompt.setLineWrap(true);
        getPrompt.setWrapStyleWord(true);
        getPrompt.addFocusListener(this);


        //Add button to panel
        panel.add(getPrompt);

        frame.setVisible(true);
    }

    /**
     * Private, internal class for defining Listeners for each button
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "Submit" -> Submit();
                case "Cancel" -> Cancel();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void Submit() {
            String text = getPrompt.getText();

            //check if input is good
            if (text.length() >= 5 & text.length() <= 350) {
                if (BankFacade.GetInstance().CreateQuestion(3, text, null, null)) {
                    statusLabel.setText("Short answer question added");
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                } else {
                    statusLabel.setText("Failed to add question");
                }
            } else {
                statusLabel.setText("Question must be 5-350 characters long");
            }
        }

        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            new AddQuestionSubScreen();
        }
    }

        public void focusGained(FocusEvent l) {
            if (getPrompt.isFocusOwner()) {
                statusLabel.setText("Add another question?");
            }
        }

        public void focusLost(FocusEvent e) {

        }
}
