package com.lab6;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        configPanel.button.addActionListener(e -> {
            int dotsCount = (Integer) configPanel.spinner.getValue();
            canvas.generateRandomDots(dotsCount);
        });

        pack();
    }

}