package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class EditQuestionSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel panel;
    private JPanel bottomPanel;
    private JTextField getPrompt;
    private JLabel promptHolder;
    private JTextField getAnswers;
    private JPanel answerHolder;
    private JTextField getPossible;
    private JPanel possibleHolder;
    private Questions question;
    private JTextArea getPromptBox;
    private JComboBox<String> tagsListBox;
    private JPanel addTagPanel;
    private JComboBox<String> removeTagSelection;
    private JPanel removeTagPanel;
    protected EditQuestionSubScreen(Questions question) {
        this.question = question;
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("Edit a question");
        frame.setSize(600, 950);
        frame.setLayout(new GridLayout(5, 1));

        //Unhide the main screen on closing
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                UI.GetInstance().UnHide();
            }
        });

        //Create label for the window
        headerLabel = new JLabel("", JLabel.CENTER);

        //Create a panel for housing the buttons
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Create a panel for housing the cancel button
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

        //Create tag panels
        addTagPanel = new JPanel();
        addTagPanel.setLayout(new BoxLayout(addTagPanel, BoxLayout.Y_AXIS));
        removeTagPanel = new JPanel();
        removeTagPanel.setLayout(new BoxLayout(removeTagPanel, BoxLayout.Y_AXIS));

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

        //Add the label and panels to the frame
        frame.add(headerLabel);
        frame.add(panel);
        frame.add(addTagPanel);
        frame.add(removeTagPanel);
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
        getPrompt.setText(question.GetQuestion());
        JButton promptButton = new JButton("Update Prompt");
        promptButton.setActionCommand("UpdatePrompt");
        promptButton.addActionListener(new ButtonClickListener());
        promptHolder = new JLabel(question.GetQuestion());
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

        answerPanel.add(answerLabel);
        answerPanel.add(getAnswers);
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

        //Add Tags JComboBox
        tagsListBox = new JComboBox<String>();
        JPanel addTagPanelTemp = new JPanel();
        LinkedList<Tag> tags = BankFacade.GetInstance().GetTagsList();
        tagsListBox.setEditable(true);
        for (Tag t : tags) {
            String name = t.GetTagName();
            int maxLength = 20;
            if (name.length() > maxLength) {
                name = name.substring(0, 20);
            }
            tagsListBox.addItem(name);
        }
        addTagPanelTemp.add(tagsListBox);
        JButton addTag = new JButton("Add Tag");
        addTagPanelTemp.add(addTag);
        addTagPanel.add(addTagPanelTemp);
        addTag.setActionCommand("addTag");
        addTag.addActionListener(new ButtonClickListener());

        //Remove tags
        //removeTagPanel.add(new JLabel("Removal section"));
        JPanel removeTagPanelTemp = new JPanel();
        removeTagSelection = new JComboBox<String>();
        for (String name : question.GetTags()) {
            int maxLength = 20;
            if (name.length() > maxLength) {
                name = name.substring(0, 20);
            }
            removeTagSelection.addItem(name);
        }
        removeTagPanelTemp.add(removeTagSelection);
        JButton removeTag = new JButton("Remove Tag");
        removeTagPanelTemp.add(removeTag);
        removeTagPanel.add(removeTagPanelTemp);
        removeTag.setActionCommand("removeTag");
        removeTag.addActionListener((new ButtonClickListener()));

        //Add box and label to panel
        panel.add(promptPanel);
        panel.add(answerPanel);
        panel.add(possiblePanel);

        frame.setVisible(true);

        //Don't add answers and possible answers if question is a ShortAnswer
        if (question instanceof ShortAnswer) {
            answerPanel.setVisible(false);
            possiblePanel.setVisible(false);
            promptHolder.setVisible(false);
            getPrompt.setVisible(false);
            getPromptBox = new JTextArea(10, 30);
            getPromptBox.setText(question.GetQuestion());
            getPrompt.setText(getPromptBox.getText());
            promptPanel.add(getPromptBox);
        } else {
            UpdateFrame();
        }
    }

    /**
     * Private method that updates the frame with question answers and possible answers
     */
    private void UpdateFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String s : question.GetCorrectAnswer()) {
            panel.add(new JLabel(s));
        }
        JScrollPane scroll = new JScrollPane(panel);
        answerHolder.add(scroll);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String s : question.GetPossibleAnswers()) {
            panel.add(new JLabel(s));
        }
        scroll = new JScrollPane(panel);
        possibleHolder.add(scroll);
        frame.setVisible(true);
    }
    private void UpdateTagPanel(){
        //Update addTag panel
        //tagsListBox = new JComboBox<String>();
        tagsListBox.removeAll();
        //JPanel addTagPanelTemp = new JPanel();
        LinkedList<Tag> tags = BankFacade.GetInstance().GetTagsList();
        tagsListBox.setEditable(true);
        //tagsListBox.addItem("");
        for (Tag t : tags) {
            String name = t.GetTagName();
            int maxLength = 20;
            if (name.length() > maxLength) {
                name = name.substring(0, 20);
            }
            tagsListBox.addItem(name);
        }
        //addTagPanelTemp.add(tagsListBox);
        //JButton addTag = new JButton("Add Tag");
        //addTagPanelTemp.add(addTag);
        //addTagPanel.add(addTagPanelTemp);
        //addTag.setActionCommand("addTag");
        //addTag.addActionListener(new ButtonClickListener());

        //update removeTag panel
        //JPanel removeTagPanelTemp = new JPanel();
        //removeTagSelection = new JComboBox<String>();
        removeTagSelection.removeAll();
        //removeTagSelection.addItem("");
        for (String name : question.GetTags()) {
            int maxLength = 20;
            if (name.length() > maxLength) {
                name = name.substring(0, 20);
            }
            removeTagSelection.addItem(name);
        }
        //removeTagPanelTemp.add(removeTagSelection);
        //JButton removeTag = new JButton("Remove Tag");
        //removeTagPanelTemp.add(removeTag);
        //removeTagPanel.add(removeTagPanelTemp);
        //removeTag.setActionCommand("removeTag");
        //removeTag.addActionListener((new ButtonClickListener()));

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
                case "addTag" -> AddTag();
                case "removeTag" -> RemoveTag();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void Submit() {
            //Close the screen
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            new QuestionSubScreen(String.valueOf(question.GetIdNumber()));
        }
        private void AddAnswer() {
            question.AddCorrectAnswer(getAnswers.getText());

            getAnswers.setText("");
            answerHolder.removeAll();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            for (String s : question.GetCorrectAnswer()) {
                panel.add(new JLabel(s));
            }
            JScrollPane scroll = new JScrollPane(panel);
            answerHolder.add(scroll);
            frame.setVisible(true);
        }
        private void AddPossible() {
            question.AddPossibleAnswer(getPossible.getText());

            getPossible.setText("");
            possibleHolder.removeAll();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            for (String s : question.GetPossibleAnswers()) {
                panel.add(new JLabel(s));
            }
            JScrollPane scroll = new JScrollPane(panel);
            possibleHolder.add(scroll);
            frame.setVisible(true);
        }
        private void UpdatePrompt() {
            if (question instanceof ShortAnswer) {
                question.SetQuestion(getPromptBox.getText());
            }
            else {
                promptHolder.setText(getPrompt.getText());
                question.SetQuestion(getPrompt.getText());
            }
            getPrompt.setText("");
        }
        private void AddTag(){
            BankFacade.GetInstance().AddTagToQuestion(question.GetIdNumber(),(String)tagsListBox.getSelectedItem());

            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            Prepare();
            Show();
        }
        private void RemoveTag(){
            BankFacade.GetInstance().RemoveTagFromQuestion(question.GetIdNumber(),(String) removeTagSelection.getSelectedItem());

            //UpdateTagPanel();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            Prepare();
            Show();
        }
    }
}
