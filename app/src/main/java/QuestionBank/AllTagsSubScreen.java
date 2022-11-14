package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class AllTagsSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel scrollPanel;
    private JPanel bottomPanel;

    protected AllTagsSubScreen() {
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        //Hide the main screen
        UI.GetInstance().Hide();

        frame = new JFrame("All Tags Window");
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

        //Create a panel for housing the scrollPanel
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout());

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
        frame.add(scrollPanel);
        frame.add(bottomPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Method that does final prep work and shows the window
     */
    private void Show() {
        headerLabel.setText("Select a tag.");

        //List
        DisplayList();

        frame.setVisible(true);
    }

    /**
     * Method that creates the list of buttons and adds them to the ScrollPanel
     */
    private void DisplayList() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        //Test code
        JButton b = new JButton("TestTag");
        b.setActionCommand("TestTag");
        b.addActionListener(new ButtonClickListener());
        p.add(b);
        /*
        LinkedList<String> list = new LinkedList<>(List.of("Button 1", "Button 2", "Button 3",
                "Button 4", "Button 5", "Button 6", "Button 7", "Button 8", "Button 9"));

        for (String s : list) {
            JButton button = new JButton(s);
            button.setActionCommand(s);
            button.addActionListener(new ButtonClickListener());
            //button.setPreferredSize(new Dimension(100, 50));
            p.add(button);
        }
         */

        LinkedList<Tag> tags = BankFacade.GetInstance().GetTagsList();
        for (Tag t : tags) {
            JButton button = new JButton(t.GetTagName());
            button.setActionCommand(t.GetTagName());
            button.addActionListener(new ButtonClickListener());
            p.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(p);
        scrollPanel.add(scrollPane);
        frame.setVisible(true);
    }

    /**
     * Private, internal class for defining Listeners for each button
     */
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Cancel")){
                Cancel();
            } else if (command instanceof String) {
                ListButton(command);
            } else {
                throw new IllegalArgumentException("Command '" + command.toString() + "' not found");
            }
        }

        private void ListButton(String command) {
            new TagSubScreen(command);
            frame.dispose();
        }
        private void Cancel() {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
