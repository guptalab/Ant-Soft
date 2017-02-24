package com.antcircuit;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class DrawPad extends JComponent {

	Image image;
	Graphics2D graphics2D;

	public final static int NONE = 0;
	public final static int CompAND = 1;
	public final static int CompNOT = 2;
	public final static int CompOR = 3;
	public final static int CLEAR = 4;
	public final static int CompWIRE = 5;

	public final static int WIRE_INIT = 0;
	public final static int WIRE_END = 1;

	boolean imageFlag = false;
	int currentX, currentY, oldX, oldY, tempx, tempy, currX, currY;
	int wireFlag = NONE, CompFlag = NONE;
	ArrayList<Integer> validDots;

	public DrawPad() {
		
		validDots = new ArrayList<Integer>();

		setDoubleBuffered(false);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (CompFlag == NONE) {

					System.out.println("NONE pressed");
					return;

				} else if (CompFlag == CompWIRE) {

					if (wireFlag == WIRE_INIT) {

						oldX = e.getX();
						oldY = e.getY();
						oldX = adjust(oldX);
						oldY = adjust(oldY);
						graphics2D.setStroke(new BasicStroke(6));
						graphics2D.setPaint(Color.red);

						repaint();

						graphics2D.draw(new Ellipse2D.Double(oldX, oldY, 1, 1));
						graphics2D.setPaint(Color.black);

						wireFlag = WIRE_END;

					} else if (wireFlag == WIRE_END) {

						currX = e.getX();
						currY = e.getY();

						currX = adjust(currX);
						currY = adjust(currY);

						System.out.println(oldX + " " + oldY + "\n");
						System.out.println(currX + " " + currY);

						graphics2D.setStroke(new BasicStroke(3));
						graphics2D.drawLine(currX, oldY, oldX, oldY);
						graphics2D.drawLine(currX, currY, currX, oldY);

						wireFlag = WIRE_INIT;
						repaint();

					}
				} else if (CompFlag == CompAND || CompFlag == CompOR
						|| CompFlag == CompNOT) {
					
					System.out.println(e.getX()+" "+ e.getY());
					int pressedX = adjust(e.getX());
					int pressedY = adjust(e.getY());
					System.out.println(pressedX+" "+ pressedY);
					
					System.out.println("press is" + CompFlag);
					Image img;
					img = Toolkit
							.getDefaultToolkit()
							.getImage(
									"C:/Users/Lavish/Dropbox/AntProject_Shared/AntProject/src/learning/images/and.gif");
					graphics2D.drawImage(img, pressedX, pressedY, null);
					CompFlag = CompWIRE;
					repaint();

				}
			}

		});

		addMouseListener(new MouseAdapter() {
			public void mousepressed(MouseEvent e) {

			}
		});
	}

	public void paintComponent(Graphics g) {

		if (image == null) {

			image = createImage(AntGUI.frame.getWidth(),
					AntGUI.frame.getHeight());
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}

		g.drawImage(image, 0, 0, null);
		setDots();
		repaint();

	}

	public void clear() {

		graphics2D.setStroke(new BasicStroke(1));
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);

		repaint();
		CompFlag = NONE;
		wireFlag = WIRE_INIT;
		imageFlag = false;
		setDots();

	}

	public void setDots() {

		if (!imageFlag) {

			for (int i = 0; i < AntGUI.frame.getWidth(); i = i + 25) {

				for (int j = 0; j < AntGUI.frame.getHeight(); j = j + 25) {

					graphics2D.draw(new Ellipse2D.Double(i, j, 1, 1));

					imageFlag = true;
				}
			}
		}
	}

	public void or() {
		
		
		System.out.println("or pressed");
		CompFlag = CompOR;

	}

	public void and() {

		System.out.println("and pressed");
		CompFlag = CompAND;

	}

	public void not() {

		System.out.println("not pressed");
		CompFlag = CompNOT;

	}

	public int adjust(int old) {

		int temp = old;
		int x = old % 25;

		if (x >= 12) {
			temp = old + (25 - x);

		} else if (x < 12) {
			temp = old - x;

		}
		return temp;

	}
}