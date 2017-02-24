package com.antcircuit;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class AntDisplay extends JPanel implements MouseListener {

	public static final int OFF = 0;
	public static final int ON = 1;
	public static int idAssigned = 0;

	int x, y, x2, y2;
	public static Graphics2D g;
	static BufferedImage bi;
	public static ArrayList<Components> component = new ArrayList<Components>();
	//public Components comp;
	public int clearScreen = ON;

	public static ArrayList<BufferedImage> bufferedImage = new ArrayList<BufferedImage>();
	public static ArrayList<Integer> xCoordinate = new ArrayList<Integer>();
	public static ArrayList<Integer> yCoordinate = new ArrayList<Integer>();
		
	public static ArrayList<WireComponent> wires = new ArrayList<WireComponent>();
	public static ArrayList<Connection> connection=new ArrayList<Connection>();
	public static ArrayList<WireComponent> wiresAttached=new ArrayList<WireComponent>();
	
	// public static ArrayList<Point2D> initialWireCoordinates=new
	// ArrayList<Point2D>();
	// public static ArrayList<Point2D> finalWireCoordinates=new
	// ArrayList<Point2D>();
	WireComponent wirecomponent;

	public static int wireMouseDragged=0;
	public static int componentCount = 0;
	public static int connectionId=0;
	public static int countWires = 0;
	public static int id=-1;
	public static int dragged = 0;
	int startX, startY, newX, newY;
	public int point=0;
	
	public static int selectionId=-1;
	public static String selectComponenent="off";

	AntDisplay() {
		setBackground(Color.white);
		setSize(450, 400);
		addMouseListener(this);
//		addMouseMotionListener(new MouseMotionHandler());

		// bi = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		// bufferedImage.add(bi);
		System.out.println("in antcanvas()" + bufferedImage.size());
	}

	public void paintComponent(Graphics g) {
		
		g.drawLine(0,0,100,100);
	}
/*
	public static void generate()
	{
		new AntDisplay();
		BufferedImage bgImage = null;
		DisplayCanvas.bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		try
		{
			for(int i=0;i<component.size();i++)
				{
					if(component.get(i).compName=="NOT")
						bgImage=ImageIO.read(new File("C:/Users/SAHIL/Desktop/NotGate.png"));
					else
					if(component.get(i).compName=="OR")
						bgImage=ImageIO.read(new File("C:/Users/SAHIL/Desktop/NotGate.png"));
					else
					if(component.get(i).compName=="AND")
						bgImage=ImageIO.read(new File("C:/Users/SAHIL/Desktop/NotGate.png"));
		
					AntGUI.canvas.g = DisplayCanvas.bi.createGraphics();
					AntGUI.canvas.g.drawImage(bgImage, 0, 0, null);		
					component.get(i).img=bgImage;					
				}
			BufferedImage bgImage1 = null;
			DisplayCanvas.bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

			for(int i=0;i<wires.size();i++)
			{
				bgImage=ImageIO.read(new File("C:/Users/SAHIL/Desktop/Wire.png"));
				AntGUI.canvas.g = DisplayCanvas.bi.createGraphics();
				AntGUI.canvas.g.drawImage(bgImage1, 0, 0, null);
				
				//wires.get(i).img=bgImage;
			//	bgImage.
			}
		}
		catch(IOException e){}
		
	}
	*/

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}