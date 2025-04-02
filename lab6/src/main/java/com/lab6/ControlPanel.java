package com.lab6;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 3));

        add(loadBtn);
        add(saveBtn);
        add(exitBtn);

        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        exitBtn.addActionListener(e -> exitGame());
    }

    private void loadGame(ActionEvent e) {

        String actionCommand = e.getActionCommand();
        //
    }

    private void saveGame(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        //
    }

    private void exitGame() {
        frame.dispose();
    }
}
