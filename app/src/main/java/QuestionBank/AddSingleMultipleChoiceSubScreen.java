package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class AddSingleMultipleChoiceSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel panel;
    private JPanel bottomPanel;
    private JTextField getPrompt;
    private JLabel promptHolder;
    private JTextField getAnswer;
    private JLabel answerHolder;
    private JTextField getPossible;
    private JPanel possibleHolder;
    private LinkedList<String> possibleAnswers = new LinkedList<>();


    protected AddSingleMultipleChoiceSubScreen() {
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("Add a single answer question");
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 1));

        //Unhide the main screen on closing
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                UI.GetInstance().UnHide();
            }
        });

        //Create labels for the window
        headerLabel = new JLabel("", JLabel.CENTER);

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
        getPrompt = new JTextField(10);
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
        getAnswer = new JTextField(10);
        JButton answerButton = new JButton("Update answer");
        answerButton.setActionCommand("UpdateAnswer");
        answerButton.addActionListener(new ButtonClickListener());

        answerHolder = new JLabel();

        answerPanel.add(answerLabel);
        answerPanel.add(getAnswer);
        answerPanel.add(answerButton);
        answerPanel.add(answerHolder);

        //Add possible answers for question
        JPanel possiblePanel = new JPanel();
        JLabel possibleLabel = new JLabel("Possible answer: ");
        getPossible = new JTextField(10);
        JButton possibleButton = new JButton("Add possible answer");
        possibleButton.setActionCommand("AddPossible");
        possibleButton.addActionListener(new ButtonClickListener());

        possibleHolder = new JPanel();

        possiblePanel.add(possibleLabel);
        possiblePanel.add(getPossible);
        possiblePanel.add(possibleButton);
        possiblePanel.add(possibleHolder);


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
                case "UpdateAnswer" -> UpdateAnswer();
                case "AddPossible" -> AddPossible();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void Submit() {
            //Get the answer
            LinkedList<String> answer = new LinkedList<>();
            answer.add(answerHolder.getText());
            //Make the question
            BankFacade.GetInstance().CreateQuestion(2, promptHolder.getText(), answer, possibleAnswers);
            //Close the screen
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            new AddQuestionSubScreen();
        }
        private void UpdateAnswer() {
            answerHolder.setText(getAnswer.getText());
            getAnswer.setText("");
        }
        private void AddPossible() {
            possibleAnswers.add(getPossible.getText());
            getPossible.setText("");
            possibleHolder.removeAll();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            for (String s : possibleAnswers) {
                panel.add(new JLabel(s));
            }
            JScrollPane scroll = new JScrollPane(panel);
            possibleHolder.add(scroll);
            frame.setVisible(true);
        }
        private void UpdatePrompt() {
            promptHolder.setText(getPrompt.getText());
            getPrompt.setText("");
        }
    }
}
