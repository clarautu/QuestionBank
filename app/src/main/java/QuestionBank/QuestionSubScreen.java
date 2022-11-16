package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.showMessageDialog;

public class QuestionSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel infoPanel;
    private JPanel panel;
    private JPanel bottomPanel;
    private Questions question;

    protected QuestionSubScreen(String command) {
        this.question = BankFacade.GetInstance().GetQuestion(Integer.parseInt(command));
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("Question Window for question #" + question.GetIdNumber() + " " +
                question.GetQuestion());
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(4, 1));

        //Create labels for the window
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        //Unhide the main screen on closing
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                UI.GetInstance().UnHide();
            }
        });

        //Create a panel for housing the buttons
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //Create a panel for housing the question info
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 3));

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
        frame.add(infoPanel);
        frame.add(panel);
        frame.add(bottomPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Method that does final prep work and shows the window
     */
    private void Show() {
        headerLabel.setText("Prompt: " + question.GetQuestion());

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

        //Make and add question info labels
        infoPanel.add(new JLabel("Answers"));
        infoPanel.add(new JLabel("Possible Answers"));
        infoPanel.add(new JLabel("Tags"));

        //Make and add panel for question answers
        JPanel answers = new JPanel();
        answers.setLayout(new BoxLayout(answers, BoxLayout.Y_AXIS));
        if (question.GetCorrectAnswer() != null){
            if (question.GetCorrectAnswer().size() == 0) { answers.add(new JLabel("<No Answers>"));}
            for (String s : question.GetCorrectAnswer()) {
                answers.add(new JLabel(s));
            }
        } else { answers.add(new JLabel("<No Answers>")); }
        infoPanel.add(answers);
        //Make and add panel for question possible answers
        JPanel possibles = new JPanel();
        possibles.setLayout(new BoxLayout(possibles, BoxLayout.Y_AXIS));
        if (question.GetPossibleAnswers() != null) {
            if (question.GetPossibleAnswers().size() == 0) { possibles.add(new JLabel("<No Possible Answers>"));}
            for (String s : question.GetPossibleAnswers()) {
                possibles.add(new JLabel(s));
            }
        } else { possibles.add(new JLabel("<No Possible Answers>")); }
        infoPanel.add(possibles);
        //Make and add panel for question tags
        JPanel tags = new JPanel();
        tags.setLayout(new BoxLayout(tags, BoxLayout.Y_AXIS));
        if (question.GetTags() != null) {
            if (question.GetTags().size() == 0) { tags.add(new JLabel("<No Tags>"));}
            for (String s : question.GetTags()) {
                tags.add(new JLabel(s));
            }
        } else { tags.add(new JLabel("<No Tags>")); }
        infoPanel.add(tags);


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
                case "Cancel" -> Cancel();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void EditQuestion() {
            //Close screen
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            //Open new screen
            new EditQuestionSubScreen(question);
        }
        private void DeleteQuestion() {
            Boolean didItWork = BankFacade.GetInstance().RemoveQuestion(question.GetIdNumber());
            //Let user know if it worked, based on returned boolean value
            if (didItWork) {
                showMessageDialog(null, "Question deleted.");
            } else {
                showMessageDialog(null, "Question failed to be deleted.");
            }
            //Close screen
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        private void AddTag() {
            statusLabel.setText("Add tag button clicked");
            //Call AddTag() method - need another screen?
        }
        private void RemoveTag() {
            statusLabel.setText("Remove tag button clicked");
            //Call RemoveTag() method - need another screen?
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            new AllQuestionsSubScreen();
        }
    }
}
