package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddQuestionSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel panel;
    private JPanel bottomPanel;

    protected AddQuestionSubScreen() {
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("Add a new question");
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
        headerLabel.setText("What kind of question would you like?");

        //Create buttons
        JButton shortAnswerButton = new JButton("Short Answer");
        JButton singleAnswerButton = new JButton("Single Answer Multiple Choice");
        JButton multipleAnswerButton = new JButton("Multiple Answer Multiple Choice");

        //Set button commands
        shortAnswerButton.setActionCommand("Short");
        singleAnswerButton.setActionCommand("Single");
        multipleAnswerButton.setActionCommand("Multiple");

        //Add listeners to buttons
        shortAnswerButton.addActionListener(new ButtonClickListener());
        singleAnswerButton.addActionListener(new ButtonClickListener());
        multipleAnswerButton.addActionListener(new ButtonClickListener());

        //Add buttons to panel
        panel.add(shortAnswerButton);
        panel.add(singleAnswerButton);
        panel.add(multipleAnswerButton);

        frame.setVisible(true);
    }

    /**
     * Private, internal class for defining Listeners for each button
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command){
                case "Short" -> Short();
                case "Single" -> Single();
                case "Multiple" -> Multiple();
                case "Cancel" -> Cancel();
                default -> throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void Short() {
            statusLabel.setText("Short Answer button clicked");
            new AddShortAnswerSubScreen();
        }
        private void Single() {
            statusLabel.setText("Single Answer button clicked");
            new AddSingleMultipleChoiceSubScreen();
        }
        private void Multiple() {
            statusLabel.setText("Multiple Answer button clicked");
            new AddMultipleAnswerSubScreen();
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
