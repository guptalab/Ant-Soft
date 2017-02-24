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


public class DisplayCanvas extends JPanel implements MouseListener {

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

	DisplayCanvas() {
		setBackground(Color.white);
		setSize(450, 400);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionHandler());

		// bi = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		// bufferedImage.add(bi);
		System.out.println("in displaycanvas()" + bufferedImage.size());
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(3));
		if (AntGUI.clearClicked == 1) {
			AntGUI.clearClicked = 0;
			component.clear();
			idAssigned=0;
			wires.clear();
			// initialWireCoordinates.clear();
			// finalWireCoordinates.clear();
			countWires = 0;
			// bi=null;
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		} else {

			// if(AntGUI.wireFLAG==AntGUI.COMP_WIRE)
			{
				for (WireComponent wire : wires) {
					wire.makeWire(g2D); repaint();
				}
			}
			if (dragged == 1) {
				if (component.size() > 0)
					for (int i = 0; i < id; i++)
						// System.out.println("Display canvas value of count"+" "+ToolLabelMouseListeners.count);
						g2D.drawImage(component.get(i).img, (int)component.get(i).topLeft.getX(),
								(int)component.get(i).topLeft.getY(), null);
				
				for (int i =id+1; i < component.size(); i++)
					// System.out.println("Display canvas value of count"+" "+ToolLabelMouseListeners.count);
					g2D.drawImage(component.get(i).img, (int)component.get(i).topLeft.getX(),
							(int)component.get(i).topLeft.getY(), null);

				g2D.drawImage(component.get(id).img, x, y, null);
				component.get(id).topLeft=new Point(x,y);
				
				for (WireComponent wire : wires) {
					wire.makeWire(g2D);// repaint();
				}
				//xCoordinate.set(id, x);
				//yCoordinate.set(id, y);
				repaint();
			} else {
				//System.out.println("componentsize is"+component.size());
				for (int i = 0; i < idAssigned; i++)
					// System.out.println("Display canvas value of count"+" "+ToolLabelMouseListeners.count);
					{//System.out.println("i is "+i);
					//component.get(i);
					g2D.drawImage(component.get(i).img,
							(int) component.get(i).topLeft.getX(),
							(int) component.get(i).topLeft.getY(), null);
				//System.out.println("coordinates are "+(int) component.get(i).topLeft.getX());
					}
				/*
				 * for(int i=0;i<bufferedImage.size();i++) int
				 * a=bufferedImage.size(); g2D.drawImage(bufferedImage.get(a-1),
				 * x+(a+20), y+(a+20), this);
				 */repaint();
			}
		}
		repaint();
	}

	class MouseMotionHandler extends MouseAdapter {
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			if (AntGUI.wireFLAG == AntGUI.COMP_WIRE) {
				newX = getX() + (e.getX() - startX);
				newY = getY() + (e.getY() - startY);
				// setLocation(newX, newY);
				
				// finalWireCoordinates.set(countWires,new Point(x,y));
				// wirecomponent.p2=finalWireCoordinates.get(countWires);
				//wirecomponent.p2 = null;
				System.out.println("offsets in dragging are"+wirecomponent.offSetX1+" "+wirecomponent.offSetY1);
				if(wireMouseDragged==1){
				//wireMouseDragged=1;
				wirecomponent.xCoord=e.getX();
				wirecomponent.yCoord=e.getY();
				wires.set(countWires, wirecomponent);}
				
			} else {
				dragged = 1;
			}
			repaint();
		}
	}

	@Override
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		System.out.println(x + " " + getX());
		// xCoordinate.add(x);yCoordinate.add(y);
		
		int i,j,k;
		for (k = 0; k < component.size(); k++) {
			for (i = 0; i < 100; i++) {
				if (x == component.get(k).topLeft.getX() + i) {
					for (j = 0; j < 100; j++)
						if (y == component.get(k).topLeft.getY() + j) {
							selectionId = k;
							selectComponenent="ON";
							break;
						}
						else
						{
							selectionId=-1;
							selectComponenent="OFF";
						}
					break;
				}
				// System.out.println("hieght is "+xCoordinate.get(0)+"size is "+xCoordinate.size());
			}
		}
		if(selectComponenent=="ON")
		{
			AntGUI.wireFLAG=AntGUI.NONE;
			AntGUI.CompFLAG=AntGUI.NONE;
//			AntGUI.set
			AntGUI.setAttributePanel("selection : "+component.get(selectionId).compName,component.get(selectionId).compName,selectionId);
			System.out.println("selection is "+ component.get(selectionId).compName+ component.get(selectionId).id);
		}
		
		if (AntGUI.CompFLAG == AntGUI.COMP_AND) {
			clearScreen = OFF;		
			Components c=new AndGate(x,y);
			
			ToolLabelMouseListeners.andClicked();
			c.img=bi;
			c.id=idAssigned++;
			
			component.add(c);
			for(i=0;i<component.size();i++)
				System.out.println("values are "+component.get(i).topLeft.getX());	
			AntGUI.setAttributePanel("selection : "+component.get(component.size()-1).compName,component.get(component.size()-1).compName,component.size()-1);
			System.out.println("selection is "+ component.get(component.size()-1).compName+ component.get(component.size()-1).id);
		}

		if (AntGUI.CompFLAG == AntGUI.COMP_OR) {
			clearScreen = OFF;
			Components c=new OrGate(x,y);
			
			ToolLabelMouseListeners.orClicked();
			c.img=bi;
			c.id=idAssigned++;
			
			component.add(c);
			AntGUI.setAttributePanel("selection : "+component.get(component.size()-1).compName,component.get(component.size()-1).compName,component.size()-1);
			System.out.println("selection is "+ component.get(component.size()-1).compName+ component.get(component.size()-1).id);
		}

		if (AntGUI.CompFLAG == AntGUI.COMP_NOT) {
			clearScreen = OFF;
			Components c=new NotGate(x,y);
			
			ToolLabelMouseListeners.notClicked();
			c.img=bi;
			c.id=idAssigned++;
			
			component.add(c);
			AntGUI.setAttributePanel("selection : "+component.get(component.size()-1).compName,component.get(component.size()-1).compName,component.size()-1);
			System.out.println("selection is "+ component.get(component.size()-1).compName+ component.get(component.size()-1).id);
		}
		
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		wirecomponent=null;id=-1;
		
		x = e.getX();
		y = e.getY();
		int i = 0, j = 0, k = 0,flag=0;
		point=0;
		for (k = 0; k < component.size(); k++) {
			for (i = 0; i < 100; i++) {
				if (x == component.get(k).topLeft.getX() + i) {
					for (j = 0; j < 100; j++)
						if (y == component.get(k).topLeft.getY() + j) {
							flag=1;
							break;
						}
					break;
				}
				// System.out.println("hieght is "+xCoordinate.get(0)+"size is "+xCoordinate.size());
			}
		}
		if(flag==0)
		{
			AntGUI.wireFLAG=AntGUI.NONE;
		}
		if (AntGUI.wireFLAG == AntGUI.COMP_WIRE) {
			// Point2D p;//=new Point(x,y);
			// p.x
			//startX = e.getX();
			//startY = e.getY();
			// initialWireCoordinates.add(new Point(x, y));
			// finalWireCoordinates.add(new Point(x, y));
			
			Connection conn=new Connection();
			for (k = 0; k < component.size(); k++) {
				for (i = 0; i < 100; i++) {
					if (x == component.get(k).topLeft.getX() + i) {
						for (j = 0; j < 100; j++)
							if (y == component.get(k).topLeft.getY() + j) {
								id = k;
								break;
							}
						break;
					}
					// System.out.println("hieght is "+xCoordinate.get(0)+"size is "+xCoordinate.size());
				}
			}
			
			wirecomponent = new WireComponent(component.get(id), component.get(id));
			//wirecomponent.initialCoordinate=new Point(startX,startY);
			
			wirecomponent.offSetX1=(int) (e.getX()-component.get(id).topLeft.getX());
			wirecomponent.offSetY1=(int) (e.getY()-component.get(id).topLeft.getY());
			
			conn.connection_id=connectionId;
			conn.start=component.get(id);
			conn.status="input";
			conn.wire=wirecomponent;
			System.out.println("offsets are"+wirecomponent.offSetX1+" "+wirecomponent.offSetY1);
			if((wirecomponent.offSetX1>=-5&&wirecomponent.offSetX1<=5&&wirecomponent.offSetY1%10>=0&&wirecomponent.offSetY1%10<=5)||
					(wirecomponent.offSetX1<=45&&wirecomponent.offSetX1>=35&&wirecomponent.offSetY1<=25&&wirecomponent.offSetY1>=15))
			{
				conn.connection_id=connectionId;
				conn.start=component.get(id);
				conn.status="input";
				conn.wire=wirecomponent;
				wireMouseDragged=1;
				conn.wire=wirecomponent;
				connection.add(conn);
				wires.add(wirecomponent);
				connection.add(conn);
				if((wirecomponent.offSetX1>=-5&&wirecomponent.offSetX1<=5&&wirecomponent.offSetY1%10>=0&&wirecomponent.offSetY1%10<=5))
				{
					wirecomponent.offSetX1=0;				
					if(wirecomponent.offSetY1>=5&&wirecomponent.offSetY1<15)
						wirecomponent.offSetY1=10;
					if(wirecomponent.offSetY1>=25&&wirecomponent.offSetY1<35)
						wirecomponent.offSetY1=30;
				}
				else if(wirecomponent.offSetX1<=45&&wirecomponent.offSetX1>=35&&wirecomponent.offSetY1<=25&&wirecomponent.offSetY1>=15)
				{
					wirecomponent.offSetX1=40;
					wirecomponent.offSetY1=20;
				}
			}
			else 
				wireMouseDragged=2;

			} else {
			if (component.size() > 0)
				for (k = 0; k < component.size(); k++) {
					for (i = 0; i < 100; i++) {
						if (x == component.get(k).topLeft.getX() + i) {
							for (j = 0; j < 100; j++)
								if (y == component.get(k).topLeft.getY() + j) {
									id = k;
									break;
								}
							break;
						}
						// System.out.println("hieght is "+xCoordinate.get(0)+"size is "+xCoordinate.size());
					}
				}
			/*for(i=0;i<connection.size();i++)						//imagecomponent is found here
			{
				if(connection.get(i).start.equals(component.get(id)))
				{
					//wirecomponent=connection.get(i).wire;
					wiresAttached.add(connection.get(i).wire);
					point=3;
				}
			}*/
			
		/*	for(int l=wiresAttached.size()-1;l>=0;l--)
			{
				wirecomponent=wiresAttached.get(l);
				for (i = 0; i < 100; i++) {
				if (wiresAttached.get(l).p1.getX() == component.get(id).topLeft.getX() + i) {
					for (j = 0; j < 100; j++)
						if (wiresAttached.get(l).p1.getY() == component.get(id).topLeft.getY() + j) {
							//id = k;
														
							point=1;
							System.out.println("POINT IS "+point);
							System.out.println("wireattache in p1 IS "+wiresAttached.size());
							break;
							
						}
					break;
				}
				// System.out.println("hieght is "+xCoordinate.get(0)+"size is "+xCoordinate.size());

			}
			
			if(point!=1&&point==0&&wirecomponent!=null)
			{
				point=2;
			//	wirecomponent=wiresAttached.get(l);
				System.out.println("POINT IS "+point);
				System.out.println("wireattache sie IS "+wiresAttached.size());
			//	wiresAttached.remove(wiresAttached.size()-1);
			}
			
			}*/
			/*if(wirecomponent.p1==connection.get(i).wire.p1||wirecomponent.p1==connection.get(i).wire.p2)
			{
				point=1;
			}
			else if (wirecomponent.p2==connection.get(i).wire.p2||wirecomponent.p2==connection.get(i).wire.p1)
			{
				point=2;
			}
			*/
//			wirecomponent = new WireComponent(new Point(x, y), new Point(x, y));
		}
		// System.out.println(x+" "+xCoordinate.get(id)+" "+y+" "+yCoordinate.get(id)+" "+id+"size is "+bufferedImage.size()+" "+i+" "+j);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (AntGUI.wireFLAG == AntGUI.COMP_WIRE) {
			x2 = e.getX();
			y2 = e.getY();
			int i = 0, j = 0, k = 0;
			// finalWireCoordinates.set(countWires,new Point(x2,y2));
			
			Connection conn=new Connection();
			if (component.size() > 0)
				for (k = 0; k < component.size(); k++) {
					for (i = 0; i < 100; i++) {
						if (x2 == component.get(k).topLeft.getX() + i) {
							for (j = 0; j < 100; j++)
								if (y2 == component.get(k).topLeft.getY() + j) {
									id = k;
									break;
								}
							break;
						}
						// System.out.println("hieght is "+xCoordinate.get(0)+"size is "+xCoordinate.size());

					}
				}
			wirecomponent.p2 = component.get(id);
			wirecomponent.offSetX2=(int) (e.getX()-component.get(id).topLeft.getX());
			wirecomponent.offSetY2=(int) (e.getY()-component.get(id).topLeft.getY());
			
			conn.start=component.get(id);
			conn.status="output";
			
			
			conn.wire=wirecomponent;
			conn.connection_id=connectionId++;
			connection.add(conn);	
			if(wireMouseDragged==1){
			wires.set(countWires, wirecomponent);			
			countWires++;
			wireMouseDragged=0;
			connection.add(conn);
			//if((wirecomponent.offSetX1>=-5&&wirecomponent.offSetX1<=5&&wirecomponent.offSetY1%10>=0&&wirecomponent.offSetY1%10<=5)||
			//		(wirecomponent.offSetX1<=45&&wirecomponent.offSetX1>=35&&wirecomponent.offSetY1<=25&&wirecomponent.offSetY1>=15))
			System.out.println("offsets on release are"+wirecomponent.offSetX2+" "+wirecomponent.offSetY2);
			
			if((wirecomponent.offSetX2>=-5&&wirecomponent.offSetX2<=5&&wirecomponent.offSetY2%10>=0&&wirecomponent.offSetY2%10<=5))
			{
				wirecomponent.offSetX2=0;				
				if(wirecomponent.offSetY2>=5&&wirecomponent.offSetY2<15)
					wirecomponent.offSetY2=10;
				if(wirecomponent.offSetY2>=25&&wirecomponent.offSetY2<35)
					wirecomponent.offSetY2=30;
			}
			else if(wirecomponent.offSetX2<=45&&wirecomponent.offSetX2>=35&&wirecomponent.offSetY2<=25&&wirecomponent.offSetY2>=15)
			{
				wirecomponent.offSetX2=40;
				wirecomponent.offSetY2=20;
			}
			
			if(((wirecomponent.offSetX2<-5||wirecomponent.offSetX2>5)||(wirecomponent.offSetY2%10<0||wirecomponent.offSetY2%10>5))
					&&((wirecomponent.offSetX2>45||wirecomponent.offSetX2<35)||(wirecomponent.offSetY2>25||wirecomponent.offSetY2<15)))
			{
				wires.remove(--countWires);
				connection.remove(connection.size()-1);connectionId--;
			}
			System.out.println("wirearray size is"+wires.size());
			}
			
		} else
			{dragged = 0;wiresAttached.clear();}
		/*	if(point==1)
				wirecomponent.p1=new Point(x2, y2);
			else
				if(point==2)
					wirecomponent.p1=new Point(x2, y2);
					*/
	}

	public static void clear() {
		new DisplayCanvas();
		System.out.println("clear reached");
		for(int i=0;i<component.size();i++)
			System.out.println(component.get(i).compName);
		component.clear();
	}
	
	/*public static void generate()
	{
		new DisplayCanvas();
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
	
	public int distance(int x)
	{		
		return 0;
	}*/
}