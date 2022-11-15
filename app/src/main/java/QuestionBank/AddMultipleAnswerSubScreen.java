package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class AddMultipleAnswerSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel panel;
    private JPanel bottomPanel;
    private JTextField getPrompt;
    private JTextField getAnswers;
    private JPanel answerHolder;
    private JTextField getPossible;
    private JPanel possibleHolder;
    private JLabel promptHolder;
    private LinkedList<String> answers;
    private LinkedList<String> possibleAnswers;

    protected AddMultipleAnswerSubScreen() {
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("Add a multiple answer question");
        frame.setSize(500, 500);
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

        //Create a panel for housing the buttons
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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
        headerLabel.setText("Follow instructions and hit 'Submit'");

        //Get prompt
        JPanel promptPanel = new JPanel();
        JLabel promptLabel = new JLabel("Prompt: ");
        getPrompt = new JTextField(20);
        JButton promptButton = new JButton("Update Prompt");
        promptButton.setActionCommand("UpdatePrompt");
        promptButton.addActionListener(new ButtonClickListener());
        promptHolder = new JLabel();
        promptPanel.add(promptLabel);
        promptPanel.add(getPrompt);
        promptPanel.add(promptButton);
        promptPanel.add(promptHolder);

        //Add answers for question
        JPanel answerPanel = new JPanel();
        JLabel answerLabel = new JLabel("Answer: ");
        getAnswers = new JTextField(10);
        JButton answerButton = new JButton("Add answer");
        answerButton.setActionCommand("AddAnswer");
        answerButton.addActionListener(new ButtonClickListener());

        answerHolder = new JPanel();
        answerHolder.setLayout(new BoxLayout(answerHolder, BoxLayout.Y_AXIS));
        JScrollPane answerScroll = new JScrollPane(answerHolder);
        JPanel answerScrollPanel = new JPanel();
        answerScrollPanel.add(answerScroll);

        answerPanel.add(answerLabel);
        answerPanel.add(getAnswers);
        answerPanel.add(answerButton);
        answerPanel.add(answerScrollPanel);

        //Add possible answers for question
        JPanel possiblePanel = new JPanel();
        JLabel possibleLabel = new JLabel("Possible answer: ");
        getPossible = new JTextField(10);
        JButton possibleButton = new JButton("Add possible answer");
        possibleButton.setActionCommand("AddPossible");
        possibleButton.addActionListener(new ButtonClickListener());

        possibleHolder = new JPanel();
        possibleHolder.setLayout(new BoxLayout(possibleHolder, BoxLayout.Y_AXIS));
        JScrollPane possibleScroll = new JScrollPane(possibleHolder);
        JPanel possibleScrollPanel = new JPanel();
        possibleScrollPanel.add(possibleScroll);

        possiblePanel.add(possibleLabel);
        possiblePanel.add(getPossible);
        possiblePanel.add(possibleButton);
        possiblePanel.add(possibleScrollPanel);


        //Add box and label to panel
        panel.add(promptPanel);
        panel.add(answerPanel);
        panel.add(possiblePanel);

        frame.setVisible(true);
    }

    /**
     * Private, internal class for defining Listeners for each button
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command){
                case "Submit" -> Submit();
                case "Cancel" -> Cancel();
                case "UpdatePrompt" -> UpdatePrompt();
                case "AddAnswer" -> AddAnswer();
                case "AddPossible" -> AddPossible();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void Submit() {
            //Get prompt
            String prompt = promptHolder.getText();
            //Get answers
            LinkedList<String> answers = new LinkedList<>();
            for (Component label : answerHolder.getComponents()) {
                answers.add(((JLabel) label).getText());
            }
            //Get Possible answers
            LinkedList<String> possible = new LinkedList<>();
            for (Component label : possibleHolder.getComponents()) {
                possible.add(((JLabel) label).getText());
            }
            //Make the question
            BankFacade.GetInstance().CreateQuestion(1, prompt, answers, possible);
            //Close the screen
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            new AddQuestionSubScreen();
        }
        private void AddAnswer() {
            statusLabel.setText("Add answer button clicked");
            answerHolder.add(new JLabel(getAnswers.getText()));
            getAnswers.setText("");
        }
        private void AddPossible() {
            statusLabel.setText("Add possible answer button clicked");
            possibleHolder.add(new JLabel(getPossible.getText()));
            getPossible.setText("");
        }
        private void UpdatePrompt() {
            statusLabel.setText("Update prompt button clicked");
            promptHolder.setText(getPrompt.getText());
            getPrompt.setText("");
        }
    }
}
