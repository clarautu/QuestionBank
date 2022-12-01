package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class AddShortAnswerSubScreen implements FocusListener{
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel panel;
    private JPanel bottomPanel;
    private JTextArea getPrompt;
    private String prompt;
    private JComboBox<String> tagsListBox;
    private JPanel addTagPanel;
    private JComboBox<String> removeTagSelection;
    private JPanel removeTagPanel;
    private LinkedList<String> tagsAdded = new LinkedList<>();

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
        frame.setLayout(new GridLayout(6, 1));

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

        //Create tag panels
        addTagPanel = new JPanel();
        addTagPanel.setLayout(new BoxLayout(addTagPanel, BoxLayout.Y_AXIS));
        removeTagPanel = new JPanel();
        removeTagPanel.setLayout(new BoxLayout(removeTagPanel, BoxLayout.Y_AXIS));

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
        frame.add(addTagPanel);
        frame.add(removeTagPanel);

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
        /*
        for (String name : question.GetTags()) {
            int maxLength = 20;
            if (name.length() > maxLength) {
                name = name.substring(0, 20);
            }
            removeTagSelection.addItem(name);
        }
         */
        if (tagsAdded != null) {
            for (String name : tagsAdded) {
                int maxLength = 20;
                if (name.length() > maxLength) {
                    name = name.substring(0, 20);
                }
                removeTagSelection.addItem(name);
            }
        }
        removeTagPanelTemp.add(removeTagSelection);
        JButton removeTag = new JButton("Remove Tag");
        removeTagPanelTemp.add(removeTag);
        removeTagPanel.add(removeTagPanelTemp);
        removeTag.setActionCommand("removeTag");
        removeTag.addActionListener((new ButtonClickListener()));

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
                case "addTag" -> AddTag();
                case "removeTag" -> RemoveTag();
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
    private void AddTag(){
        //BankFacade.GetInstance().AddTagToQuestion(question.GetIdNumber(),(String)tagsListBox.getSelectedItem());
        tagsAdded.add((String)tagsListBox.getSelectedItem());

        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        Prepare();
        Show();
    }
    private void RemoveTag(){
        //BankFacade.GetInstance().RemoveTagFromQuestion(question.GetIdNumber(),(String) removeTagSelection.getSelectedItem());
        tagsAdded.remove((String) removeTagSelection.getSelectedItem());
        //UpdateTagPanel();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        Prepare();
        Show();
    }
}
