package QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class AllTagsSubScreen {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel scrollPanel;

    protected AllTagsSubScreen() {
        Prepare();
        Show();
    }

    /**
     * Method that prepares the screen
     */
    private void Prepare() {
        frame = new JFrame("All Tags Window");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 1));

        //Create labels for the window
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        //Create a panel for housing the scrollPanel
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout());

        //Add the labels and panel to the frame
        frame.add(headerLabel);
        frame.add(scrollPanel);
        frame.add(statusLabel);

        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
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
        LinkedList<String> list = new LinkedList<>(List.of("Button 1", "Button 2", "Button 3",
                "Button 4", "Button 5", "Button 6", "Button 7", "Button 8", "Button 9"));

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        for (String s : list) {
            JButton button = new JButton(s);
            button.setActionCommand(s);
            button.addActionListener(new ButtonClickListener());
            //button.setPreferredSize(new Dimension(100, 50));
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

            if (command instanceof String) {
                Test(command);
            } else {
                throw new IllegalArgumentException("Command '" + command + "' not found");
            }
        }

        private void Test(String command) {
            statusLabel.setText("List button clicked.");
            new TagSubScreen(command);
            frame.dispose();
        }
    }
}
