package learning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    ArrayList<Square> squares = new ArrayList<Square>();

    public MyPanel() {
        squares.add(new Square(20, 40));
        squares.add(new Square(100, 102));
        squares.add(new Square(56, 65));

        Listener listener = new Listener();
        addMouseMotionListener(listener);
        addMouseListener(listener);
    }

    
    public static void main(String args[]){
    	
    	new MyPanel();
    	
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Square s : squares) {
            s.draw(g);
        }
    }

    class Listener extends MouseAdapter {
        Square selected = null;

        @Override
        public void mousePressed(MouseEvent e) {
            for(Square s : squares) {
                int x = s.getX();
                int y = s.getY();
                int mx = e.getX();
                int my = e.getY();

                if(mx - x >= 0 && mx - x <= 10 && my - y >= 0 && my - y <= 10) {
                    selected = s;
                    break;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selected = null;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(selected == null) {
                return;
            }

            selected.setX(e.getX() - 5);
            selected.setY(e.getY() - 5);

            repaint();
        }
    }
}