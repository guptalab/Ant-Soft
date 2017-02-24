package learning;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class DragTest extends JFrame implements MouseMotionListener,MouseListener {

    private JPanel leftPanel = new JPanel(null);
    private JPanel rightPanel = new JPanel(null);
    private JLabel dragLabel = new JLabel("drop");
    private final JWindow window = new JWindow();
    JLabel dropLabel;

    public DragTest() {
    	
        this.setLayout(new GridLayout(1, 2));

        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(leftPanel);
        this.add(rightPanel);
        leftPanel.addMouseListener(this);
        leftPanel.addMouseMotionListener(this);

        JTextArea area = new JTextArea();

        rightPanel.setLayout(new GridLayout(1, 1));
        rightPanel.add(area);

        dragLabel.setFont(new Font("Serif", Font.BOLD, 48));
    }

    @Override
    public void mousePressed(MouseEvent e) {

        dropLabel = new JLabel("drop");

        Dimension labelSize = dropLabel.getPreferredSize();
        dropLabel.setSize(labelSize);
        int x = e.getX() - labelSize.width / 2;
        int y = e.getY() - labelSize.height / 2;
        dropLabel.setLocation(x, y);
        leftPanel.add(dropLabel);

        dropLabel.setTransferHandler(new TransferHandler("text"));

        dropLabel.getTransferHandler().exportAsDrag(dropLabel, e,
                TransferHandler.COPY);

        repaint();

    }

    @Override
    public void mouseDragged(MouseEvent me) {
        dragLabel = new JLabel("drop");
        dragLabel.setFont(new Font("Serif", Font.BOLD, 48));
        int x = me.getPoint().x;
        int y = me.getPoint().y;
        window.add(dragLabel);
        window.pack();
        Point pt = new Point(x, y);
        Component c = (Component) me.getSource();
        SwingUtilities.convertPointToScreen(pt, c);
        window.setLocation(pt);
        window.setVisible(true);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

//      leftPanel.remove(dropLabel);

        window.remove(dragLabel);
        window.setVisible(false);

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {

        DragTest frame = new DragTest();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}