package QuestionBank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

import static javax.swing.JOptionPane.showMessageDialog;

public class TagSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel panel;
    private JPanel questionPanel;
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
        frame.setLayout(new GridLayout(4, 1));

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
        panel.setLayout(new FlowLayout());

        //Create a panel for holding questions
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.add(new JLabel("Questions with this tag"));

        //Create a panel for housing the cancel button
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

        //Make and add cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ButtonClickListener());
        bottomPanel.add(cancelButton);
        bottomPanel.add(Box.createHorizontalGlue());

        //Add the labels and panel to the frame
        frame.add(headerLabel);
        frame.add(questionPanel);
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

        //Create buttons
        JButton exportQuestionsButton = new JButton("Export Questions With Tag");
        JButton removeTageButton = new JButton("Remove Tag");

        //Set button commands
        exportQuestionsButton.setActionCommand("GetQuestions");
        removeTageButton.setActionCommand("RemoveTag");

        //Add listeners to buttons
        exportQuestionsButton.addActionListener(new ButtonClickListener());
        removeTageButton.addActionListener(new ButtonClickListener());

        //Add buttons to panel
        panel.add(exportQuestionsButton);
        panel.add(removeTageButton);

        //Get and display question with this tag
        LinkedList<Questions> questions = BankFacade.GetInstance().GetTaggedQuestions(tagName);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        if (questions != null) { // Check if no questions have this tag
            for (Questions q : questions) {
                p.add(new JLabel(q.GetQuestion()));
            }
        } else { // If so, let user no
            p.add(new JLabel("<No questions with this tag>"));
        }
        JScrollPane questionHolder = new JScrollPane(p);
        questionPanel.add(questionHolder);

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
            LinkedList<Questions> taggedQuestions = BankFacade.GetInstance().GetTaggedQuestions(tagName);
            //Export these
            Gson gson = new GsonBuilder().registerTypeAdapter(Questions.class, new GsonInstanceCreator()).create();
            String json = gson.toJson(taggedQuestions);
            try {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.showSaveDialog(null);
                File fileToSave = fileChooser.getSelectedFile();
                FileWriter file = new FileWriter(fileToSave.getAbsoluteFile());
                file.write(json);
                file.close();
            } catch (Exception e) {
                System.out.println("Problem exporting");
            }
        }
        private void RemoveTag() {
            Boolean didItWork = BankFacade.GetInstance().RemoveTag(tagName);
            //Let user know if it worked, based on returned boolean
            if (didItWork) {
                showMessageDialog(null, "Tag: " + tagName + " removeed successfully.");
            } else {
                showMessageDialog(null, "Something went wrong! The tag wasn't removed.");
            }
            //Close screen
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            new AllTagsSubScreen();
        }
    }
}
