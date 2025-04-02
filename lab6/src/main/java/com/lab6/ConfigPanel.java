package com.lab6;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    JButton button;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Number of dots:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        button = new JButton("New Game");

        add(label);
        add(spinner);
        add(button);

    }

    public MainFrame getFrame() {
        return frame;
    }
}
