package learning;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class test extends JFrame implements MouseMotionListener {

private JPanel panel = new JPanel(null);    
private JLabel dragLabel = new JLabel("drag test");
private int mouseX = 200;
private int mouseY = 200;

public test() {
    this.add(panel);
    panel.setBackground(Color.WHITE);
    panel.add(dragLabel);
    dragLabel.setForeground(Color.RED);
    dragLabel.setBounds(mouseX, mouseY, 100, 50);
    panel.addMouseMotionListener(this);
}

@Override
public void mouseDragged(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
    dragLabel.setBounds(mouseX, mouseY, 100, 50);
}

@Override
public void mouseMoved(MouseEvent e) {}

public static void main(String[] args) {
    test frame = new test();
    frame.setVisible(true);
    frame.setSize(600, 400);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}