package com.lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    private final int canvasWidth = 400;
    private final int canvasHeight = 400;
    private final int dotSize = 20;
    BufferedImage image;
    Graphics2D offscreen;

    private final java.util.List<Point> dots;

    public MainFrame getFrame() {
        return frame;
    }

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        dots = new ArrayList<>();
        createOffscreenImage();
        init();
    }

    public final void createOffscreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    private void init() {
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
        offscreen.setColor(Color.BLACK);

        for (Point dot : dots) {
            offscreen.fillOval(dot.x - dotSize / 2, dot.y - dotSize / 2,
                    dotSize, dotSize);
        }

        graphics.drawImage(image, 0, 0, this);
    }

    public void generateRandomDots(int count) {
        dots.clear();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(canvasWidth - dotSize) + dotSize / 2;
            int y = rand.nextInt(canvasHeight - dotSize) + dotSize / 2;
            dots.add(new Point(x, y));
        }
        repaint();
    }

    public java.util.List<Point> getDots() {
        return Collections.unmodifiableList(dots);
    }
}