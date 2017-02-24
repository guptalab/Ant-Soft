package com.antcircuit;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SaveFile {

	static BufferedImage GateImage_p1;
	static BufferedImage GateImage_p2;
	static BufferedImage bottom_left;
	static BufferedImage bottom_right;
	static BufferedImage top_left;
	static BufferedImage top_right;
	static BufferedImage vert_wire;
	static BufferedImage horiz_wire;
	static Graphics2D g2;
	static BufferedImage bgImage;
	static ArrayList<Components> gateImplemented;
	
	public static void saveImage() {		

		gateImplemented=new ArrayList<Components>();
		bgImage = new BufferedImage(6000, 6000, BufferedImage.TYPE_INT_ARGB);
		int n=DisplayCanvas.component.size();	
		int noOfwires=DisplayCanvas.wires.size();
		
		g2 = bgImage.createGraphics();
		Color oldColor = g2.getColor();      
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, 6000, 6000);      
		g2.setColor(oldColor);

		try{
			horiz_wire=ImageIO.read(new File("./src/com/antcircuit/antcomponents/WireSmall1.png"));
			bottom_left=ImageIO.read(new File("./src/com/antcircuit/antcomponents/BottomLeft1.png"));
			bottom_right=ImageIO.read(new File("./src/com/antcircuit/antcomponents/BottomRight1.png"));
			top_left=ImageIO.read(new File("./src/com/antcircuit/antcomponents/TopLeft1.png"));
			top_right=ImageIO.read(new File("./src/com/antcircuit/antcomponents/TopRight1.png"));
			vert_wire=ImageIO.read(new File("./src/com/antcircuit/antcomponents/WireSmall1.png"));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i=0;i<noOfwires;i++)
		{
			makeWire(DisplayCanvas.wires.get(i));
		}

		g2.dispose();

		System.out.println("call from saveimage");
		try{
			File outputfile = new File("image.png");
			ImageIO.write(bgImage, "png", outputfile);
		}
		catch(IOException e){}

	}

	public static void makeWire(WireComponent wire)
	{
		if(wire.p1.topLeft.getX()<wire.p2.topLeft.getX())
		{
			if(wire.p1.topLeft.getY()<=wire.p2.topLeft.getY())
			{
				int prod1=1,flag1=0,flag2=0;
				
				Point2D pt=new Point2D.Double();
				Point2D pt1=new Point2D.Double();
				Point2D pt2=new Point2D.Double();
				Point2D last_coord=new Point2D.Double();
				
				for(int k=0;k<gateImplemented.size();k++)
				{
					if(wire.p2==gateImplemented.get(k))
						{
							flag2=1;
							break;
						}
				}
				
				switch(flag2)
				{
				case 0:{
				
				System.out.println("offsety2 is"+wire.offSetY2);
				int val = 0;
				pt=getIntersectionPoint(val+90, val+180,  ((int)wire.p2.topLeft.getX()*10-150), ((int)wire.p2.topLeft.getX()*10-110),
						((int)wire.p1.topLeft.getY())*10,
						((int)wire.p1.topLeft.getY())*10,
						((int)wire.p2.topLeft.getY()*10-36), ((int)wire.p2.topLeft.getY()*10-116));

				val=0;int prod = 1;prod1=1;
				for(int k=0;k<gateImplemented.size();k++)
				{
					if(wire.p1==gateImplemented.get(k))
						flag1=1;
				}
				int last_coord_x,last_coord_y;
				
				if(flag1==0)
				{					
						g2.drawImage(horiz_wire, null,((int)wire.p1.topLeft.getX())*10+155,((int)wire.p1.topLeft.getY())*10);
						val=(int)wire.p1.topLeft.getX()*10+155;prod++;
						
					{
						last_coord=
								horizontalLine(val, ((int)wire.p1.topLeft.getY())*10,
										(int)pt.getX(), ((int)wire.p1.topLeft.getY())*10);
									
					}
						
					last_coord_x=(int)last_coord.getX();
			
					last_coord_y=(int)last_coord.getY();
					
					g2.drawImage(top_right, null, last_coord_x,last_coord_y);
				}
				else
				{
						g2.drawImage(horiz_wire, null,(int)wire.p1.gatePlaceX+wire.p1.wireOffsetX,(int)wire.p1.gatePlaceY+wire.p1.wireOffsetY);
						val=(int)wire.p1.gatePlaceX+wire.p1.wireOffsetX;prod++;
	
						{       					
							//g2.drawImage(horiz_wire, null,(val)+90*(prod1++),(int)wire.p1.gatePlaceY+wire.p1.wireOffsetY);
							last_coord=
									horizontalLine(val, (int)wire.p1.gatePlaceY+wire.p1.wireOffsetY,
											(int)pt.getX(), (int)wire.p1.gatePlaceY+wire.p1.wireOffsetY);
							
						}
						
					last_coord_x=(int)last_coord.getX();					
					last_coord_y=(int)last_coord.getY();
					
					g2.drawImage(top_right, null, last_coord_x,last_coord_y);
				}
				pt1.setLocation(((int)wire.p1.topLeft.getX())*10,((int)wire.p1.topLeft.getY())*10);
				
				int prod3=1;
				g2.drawImage(vert_wire, null, last_coord_x+35,last_coord_y+100);
				
				last_coord_x=last_coord_x+36;
				last_coord_y=last_coord_y+80;
				
				if(wire.offSetY2==10||wire.offSetY2==20)
				{
					last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p2.topLeft.getY()*10)+wire.p2.offsetInputY1,0);
				}
				else
					if(wire.offSetY2==30)
					{						
						System.out.println("wireoffset in y2 is "+wire.offSetY2);
							
						System.out.println("**********************");
						last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p2.topLeft.getY()*10)+wire.p2.offsetInputY2,0);
						
					}
								
				last_coord_x=(int)last_coord.getX()-3*(prod3)-9*(prod3);
				last_coord_y=(int)last_coord.getY()+20*(prod3);
				
				g2.drawImage(bottom_left, null, last_coord_x-35,last_coord_y-20);

				last_coord_x=last_coord_x+60;//+120;
				last_coord_y=last_coord_y+60;//+60;
				
				if(wire.offSetY2==10||wire.offSetY2==20)
				{
					pt2.setLocation(last_coord_x,last_coord_y);
				}
				else
					if(wire.offSetY2==30)
					{
						last_coord=horizontalLine
						(last_coord_x, last_coord_y,((int)wire.p2.topLeft.getX()*10)+wire.p2.offsetInputX2,last_coord_y);
						pt2.setLocation(last_coord.getX()-10,last_coord.getY());		
					}

				addGate(wire.p1, wire.p2,pt1,pt2,1,wire);
				break;
				}
				
				case 1:
				{
					System.out.println("********************");
					System.out.println("offsety2 is"+wire.offSetY2);
					int val = 0;flag1=0;
					
					pt=getIntersectionPoint(val+90, val+180,  ((int)wire.p2.gatePlaceX+wire.p2.IntersectionOffsetX), ((int)wire.p2.gatePlaceX+wire.p2.IntersectionOffsetX+40),
							((int)wire.p1.topLeft.getY())*10,
							((int)wire.p1.topLeft.getY())*10,
							((int)wire.p2.gatePlaceY+wire.p2.IntersectionOffsetY+80), ((int)wire.p2.gatePlaceY+wire.p2.IntersectionOffsetY)); 	
					
					val=0;int prod = 1;prod1=1;
					for(int k=0;k<gateImplemented.size();k++)
					{
						if(wire.p1==gateImplemented.get(k))
							flag1=1;
					}
					int last_coord_x,last_coord_y;
					
					if(flag1==0)
					{
							g2.drawImage(horiz_wire, null,((int)wire.p1.topLeft.getX())*10+180,((int)wire.p1.topLeft.getY())*10);
							last_coord_x=((int)wire.p1.topLeft.getX())*10+160;
				
							val=last_coord_x+20*prod++;
					 						
						last_coord_x=(val); 
						last_coord_y=((int)wire.p1.topLeft.getY())*10;
						g2.drawImage(top_right, null, last_coord_x,last_coord_y);
					}
					else
					{
						{
							g2.drawImage(horiz_wire, null,(int)wire.p1.gatePlaceX+wire.p1.gatePresentOutputOffset,(int)wire.p1.gatePlaceY);//155//-10
							val=(int)wire.p1.gatePlaceX+wire.p1.gatePresentOutputOffset;prod++;
						}
						 last_coord_x=(val)+20*(prod1++); 
						 last_coord_y=(int)wire.p1.gatePlaceY;
						
						g2.drawImage(top_right, null, last_coord_x,last_coord_y);//+180
					}
					
					pt1.setLocation(((int)wire.p1.topLeft.getX())*10,((int)wire.p1.topLeft.getY())*10);
					
					int prod3=1;
					g2.drawImage(vert_wire, null, last_coord_x+35,last_coord_y+100);
					
					if(wire.offSetY2==10||wire.offSetY2==20)
					{
						last_coord=verticalLine(last_coord_x+36, last_coord_y+80, 0, ((int)wire.p2.gatePlaceY)+wire.p2.gatePresentInputOffsetY1,0);
					}
					else
						if(wire.offSetY2==30)
						{
				
							System.out.println("********offsety2 is "+wire.p2.gatePresentInputOffsetY2);
							last_coord=verticalLine(last_coord_x+36, last_coord_y+80, 0, ((int)wire.p2.gatePlaceY)+wire.p2.gatePresentInputOffsetY2,0);
														
						}
			
					last_coord_x=(int)last_coord.getX()-3*(prod3)-9*(prod3);
					last_coord_y=(int)last_coord.getY()+20*(prod3);
					
					g2.drawImage(bottom_left, null, last_coord_x-35,last_coord_y-20);
				
					g2.drawImage(horiz_wire, null,last_coord_x+80,last_coord_y+60);
				
					last_coord_x=last_coord_x+80;
					last_coord_y=last_coord_y+60;
					
					prod1=1;
				
					if(wire.p2.compName=="NOT")System.out.println("not value is "+ wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX2);
					if(wire.p2.compName=="AND")System.out.println("and value is "+ wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX2);
					if(wire.offSetY2==10||wire.offSetY2==20)						
						last_coord=horizontalLine(last_coord_x, last_coord_y, wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX1, last_coord_y);
					else
					if(wire.offSetY2==30)
						last_coord=horizontalLine(last_coord_x, last_coord_y, wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX2, last_coord_y);	
				    
					last_coord_x=(int)last_coord.getX();	//+20*(prod1++);
					last_coord_y=last_coord_y;
					
					pt2.setLocation(last_coord_x,last_coord_y);addGate(wire.p1, wire.p2,pt1,pt2,1,wire);break;	
				}
				
				}
				
			}
			
	/////////////////////////////////////////////////////////////////////////////////////////////////
			
			else if(wire.p1.topLeft.getY()>wire.p2.topLeft.getY())
			{
				System.out.println("hello*******");
				int prod1=1,flag2=0,flag1=0,val=0,prod=1;
				Point2D pt1=new Point2D.Double();
				Point2D pt2=new Point2D.Double();
				Point2D last_coord=new Point2D.Double();
				int last_coord_x=0,last_coord_y=0;
				
				for(int k=0;k<gateImplemented.size();k++)
				{
					if(wire.p2==gateImplemented.get(k))
						{
							flag2=1;
							break;
						}
				}
				
				switch(flag2)
				{
				case 0:
				{
					for(int k=0;k<gateImplemented.size();k++)
					{
						if(wire.p1==gateImplemented.get(k))
							{
								flag1=1;
								break;
							}
					}
					
				if(flag1==0)
				{
					g2.drawImage(horiz_wire, null,((int)wire.p1.topLeft.getX())*10+175,((int)wire.p1.topLeft.getY())*10);
					val=(int)wire.p1.topLeft.getX()*10+155;prod++;
					g2.drawImage(bottom_right, null, ((int)wire.p1.topLeft.getX()*10+200),((int)wire.p1.topLeft.getY()*10)-20);
						
					g2.drawImage(vert_wire, null, ((int)wire.p1.topLeft.getX()*10+290),((int)wire.p1.topLeft.getY()*10-40));
						
					 last_coord_x=((int)wire.p1.topLeft.getX()*10+288);
					 last_coord_y=((int)wire.p1.topLeft.getY()*10-40);
				}
				else
					if(flag1==1)
				{
						g2.drawImage(horiz_wire, null,(int)wire.p1.gatePlaceX+wire.p1.gatePresentOutputOffset,(int)wire.p1.gatePlaceY);
						val=(int)wire.p1.gatePlaceX+wire.p1.gatePresentOutputOffset;prod++;
						g2.drawImage(bottom_right, null, val+22,(int)wire.p1.gatePlaceY-17);
						
				
				int val1=val+22;
				g2.drawImage(vert_wire, null, val1+95,((int)wire.p1.gatePlaceY-40));
					int prod3=1;

        		last_coord_x=val1+95;
        		last_coord_y=((int)wire.p1.gatePlaceY-40);
				}
				
				if(wire.offSetY2==10||wire.offSetY2==20)
					last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p2.topLeft.getY()*10+wire.p2.offsetInputY2), 1);
				else if(wire.offSetY2==30)
					last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p2.topLeft.getY()*10+wire.p2.offsetInputY1), 1);
       		
				last_coord_x=(int)last_coord.getX();//+3*(prod3)+9*(prod3);
				last_coord_y=(int)last_coord.getY();//+20-20*(prod3);
        		
				g2.drawImage(top_left, null, last_coord_x,last_coord_y-38);
        		
				last_coord_x=last_coord_x+80;
				last_coord_y=last_coord_y-38;
        		
				int prod3=1;
                																
        		last_coord=horizontalLine(last_coord_x, last_coord_y, (int)wire.p2.topLeft.getX()*10, last_coord_y);
        		
        		last_coord_x=(int)last_coord.getX();//_x+20*(prod3)-20;
        		
        		pt1.setLocation(wire.p1.topLeft.getX()*10, wire.p1.topLeft.getY()*10);
        		pt2.setLocation(last_coord_x-30,last_coord_y);
        		addGate(wire.p1, wire.p2, pt1, pt2,2,wire);
        		
				}
								
				break;	
				
				case 1: 
				{
					for(int k=0;k<gateImplemented.size();k++)
					{
						if(wire.p1==gateImplemented.get(k))
							{
								flag1=1;
								break;
							}
					}
					
					if(flag1==0)
					{
						if(wire.p2.compName=="NOT"){
						g2.drawImage(horiz_wire, null,((int)wire.p2.gatePlaceX)+wire.p2.gatePresentInputOffsetX1-20,(int)wire.p2.gatePlaceY-wire.p2.gatePresentInputOffsetY1);
						last_coord_x=(int)wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX1-80-20;
						last_coord_y=(int)wire.p2.gatePlaceY-wire.p2.gatePresentInputOffsetY1;
						}
						else if(wire.p2.compName=="AND")
						{
							if(wire.offSetY2==10){
							g2.drawImage(horiz_wire, null,((int)wire.p2.gatePlaceX)+wire.p2.gatePresentInputOffsetX1-20,(int)wire.p2.gatePlaceY-wire.p2.gatePresentInputOffsetY1-40);
							last_coord_x=(int)wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX1-80-20;
							last_coord_y=(int)wire.p2.gatePlaceY-wire.p2.gatePresentInputOffsetY1-40;
							}
							else if(wire.offSetY2==30)
							{
								g2.drawImage(horiz_wire, null,((int)wire.p2.gatePlaceX)+wire.p2.gatePresentInputOffsetX2-25,(int)wire.p2.gatePlaceY+wire.p2.gatePresentInputOffsetY2+110);
								last_coord_x=(int)wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX2-80-25;
								last_coord_y=(int)wire.p2.gatePlaceY+wire.p2.gatePresentInputOffsetY2+110;
							}
						}
						else if(wire.p2.compName=="OR")
						{
							if(wire.offSetY2==10){
							g2.drawImage(horiz_wire, null,((int)wire.p2.gatePlaceX)+wire.p2.gatePresentInputOffsetX1,(int)wire.p2.gatePlaceY-wire.p2.gatePresentInputOffsetY1+60);
							last_coord_x=(int)wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX1-80;
							last_coord_y=(int)wire.p2.gatePlaceY-wire.p2.gatePresentInputOffsetY1+60;
							}
							else if(wire.offSetY2==30)
							{
								g2.drawImage(horiz_wire, null,((int)wire.p2.gatePlaceX)+wire.p2.gatePresentInputOffsetX2-25,(int)wire.p2.gatePlaceY+wire.p2.gatePresentInputOffsetY2+110);
								last_coord_x=(int)wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX2-80-25;
								last_coord_y=(int)wire.p2.gatePlaceY+wire.p2.gatePresentInputOffsetY2+110;
							}
						}
						g2.drawImage(top_left, null, last_coord_x-20,last_coord_y);
						
						
					/*	g2.drawImage(horiz_wire, null,((int)wire.p1.topLeft.getX())*10+175,((int)wire.p1.topLeft.getY())*10);
						val=(int)wire.p1.topLeft.getX()*10+155;prod++;
						g2.drawImage(bottom_right, null, ((int)wire.p1.topLeft.getX()*10+200),((int)wire.p1.topLeft.getY()*10)-20);
							
						g2.drawImage(vert_wire, null, ((int)wire.p1.topLeft.getX()*10+290),((int)wire.p1.topLeft.getY()*10-40));
							
						 last_coord_x=((int)wire.p1.topLeft.getX()*10+288);
						 last_coord_y=((int)wire.p1.topLeft.getY()*10-40);
					 */
						g2.drawImage(vert_wire,null,last_coord_x-30,last_coord_y+40);
						
						g2.drawImage(bottom_right,null,last_coord_x-120,last_coord_y+60);
						
						int last_coord_x1=last_coord_x-120;
						int last_coord_y1=last_coord_y+60;
						g2.drawImage(horiz_wire,null,last_coord_x-140,last_coord_y+80);
						
						pt1.setLocation(last_coord_x-140-175,last_coord_y+80-wire.p1.offsetOutputY1);
						
						
					}
					else if(flag1==1)
					{
						g2.drawImage(horiz_wire, null,(int)wire.p1.gatePlaceX+wire.p1.gatePresentOutputOffset,(int)wire.p1.gatePlaceY);
						val=(int)wire.p1.gatePlaceX+wire.p1.gatePresentOutputOffset;
						g2.drawImage(bottom_right, null, val+22,(int)wire.p1.gatePlaceY-17);
				
						int val1=val+22;
						g2.drawImage(vert_wire, null, val1+95,((int)wire.p1.gatePlaceY-40));
						
						last_coord_x=val1+95;
						last_coord_y=((int)wire.p1.gatePlaceY-40);					
					
					
					if(wire.offSetY2==10||wire.offSetY2==20)
						last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p2.gatePlaceY-wire.p2.offsetInputY1+20), 1);
					else if(wire.offSetY2==30)
						last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p2.gatePlaceY-wire.p2.offsetInputY2+20), 1);
	       		
					last_coord_x=(int)last_coord.getX();//+3*(prod3)+9*(prod3);
					last_coord_y=(int)last_coord.getY();//+20-20*(prod3);
	        		
					g2.drawImage(top_left, null, last_coord_x,last_coord_y-38);
	        		
					last_coord_x=last_coord_x+80;
					last_coord_y=last_coord_y-38;
	        		
					int prod3=1;
	                
					if(wire.offSetY2==10||wire.offSetY2==20)
						last_coord=horizontalLine(last_coord_x, last_coord_y, (int)wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX1, last_coord_y);
					else if(wire.offSetY2==30)
						last_coord=horizontalLine(last_coord_x, last_coord_y, (int)wire.p2.gatePlaceX+wire.p2.gatePresentInputOffsetX2, last_coord_y);
					
					last_coord_x=(int)last_coord.getX();//_x+20*(prod3)-20;
	        		
	        		pt1.setLocation(wire.p1.topLeft.getX()*10, wire.p1.topLeft.getY()*10);
	        		pt2.setLocation(last_coord_x-30,last_coord_y);
	        		}
	        		addGate(wire.p1, wire.p2, pt1, pt2,2,wire);
					
				}break;
				}
				
        		
        		
        	//	g2.drawImage(horiz_wire, null, ((int)wire.p1.topLeft.getX()*10+65),((int)wire.p1.topLeft.getY()*10));
        	   			}
		}
		/////////////////////////////////////////////////////////////////////////////////
		else if(wire.p1.topLeft.getX()>wire.p2.topLeft.getX())
		{
			if(wire.p1.topLeft.getY()<wire.p2.topLeft.getY())
			{
				{
					System.out.println("hello*******");
					int prod1=1,flag2=0,flag1=0,val=0,prod=1;
					Point2D pt1=new Point2D.Double();
					Point2D pt2=new Point2D.Double();
					Point2D last_coord=new Point2D.Double();
					int last_coord_x=0,last_coord_y=0;
					
					for(int k=0;k<gateImplemented.size();k++)
					{
						if(wire.p1==gateImplemented.get(k))
							{
								flag2=1;
								break;
							}
					}
					
					switch(flag2)
					{
					case 0:
					{
						for(int k=0;k<gateImplemented.size();k++)
						{
							if(wire.p2==gateImplemented.get(k))
								{
									flag1=1;
									break;
								}
						}
						
					if(flag1==0)
					{
						g2.drawImage(horiz_wire, null,((int)wire.p2.topLeft.getX())*10+175,((int)wire.p2.topLeft.getY())*10);
						val=(int)wire.p2.topLeft.getX()*10+155;prod++;
						g2.drawImage(bottom_right, null, ((int)wire.p2.topLeft.getX()*10+200),((int)wire.p2.topLeft.getY()*10)-20);
							
						g2.drawImage(vert_wire, null, ((int)wire.p2.topLeft.getX()*10+290),((int)wire.p2.topLeft.getY()*10-40));
							
						 last_coord_x=((int)wire.p2.topLeft.getX()*10+288);
						 last_coord_y=((int)wire.p2.topLeft.getY()*10-40);
					}
					else
						if(flag1==1)
					{
							g2.drawImage(horiz_wire, null,(int)wire.p2.gatePlaceX+wire.p2.gatePresentOutputOffset,(int)wire.p2.gatePlaceY);
							val=(int)wire.p2.gatePlaceX+wire.p2.gatePresentOutputOffset;prod++;
							g2.drawImage(bottom_right, null, val+22,(int)wire.p2.gatePlaceY-17);
							
					
					int val1=val+22;
					g2.drawImage(vert_wire, null, val1+95,((int)wire.p2.gatePlaceY-40));
						int prod3=1;

	        		last_coord_x=val1+95;
	        		last_coord_y=((int)wire.p2.gatePlaceY-40);
					}
					
					if(wire.offSetY1==10||wire.offSetY1==20)
						last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p1.topLeft.getY()*10+wire.p1.offsetInputY2), 1);
					else if(wire.offSetY1==30)
						last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p1.topLeft.getY()*10+wire.p1.offsetInputY1), 1);
	       		
					last_coord_x=(int)last_coord.getX();//+3*(prod3)+9*(prod3);
					last_coord_y=(int)last_coord.getY();//+20-20*(prod3);
	        		
					g2.drawImage(top_left, null, last_coord_x,last_coord_y-38);
	        		
					last_coord_x=last_coord_x+80;
					last_coord_y=last_coord_y-38;
	        		
					int prod3=1;
	                																
	        		last_coord=horizontalLine(last_coord_x, last_coord_y, (int)wire.p1.topLeft.getX()*10, last_coord_y);
	        		
	        		last_coord_x=(int)last_coord.getX();//_x+20*(prod3)-20;
	        		
	        		pt1.setLocation(wire.p2.topLeft.getX()*10, wire.p2.topLeft.getY()*10);
	        		pt2.setLocation(last_coord_x-30,last_coord_y);
	        		addGate(wire.p2, wire.p1, pt1, pt2,2,wire);
	        		
					}
									
					break;	
					
					case 1: 
					{
						for(int k=0;k<gateImplemented.size();k++)
						{
							if(wire.p2==gateImplemented.get(k))
								{
									flag1=1;
									break;
								}
						}
						
						if(flag1==0)
						{
							if(wire.p1.compName=="NOT"){
							g2.drawImage(horiz_wire, null,((int)wire.p1.gatePlaceX)+wire.p1.gatePresentInputOffsetX1-20,(int)wire.p1.gatePlaceY-wire.p1.gatePresentInputOffsetY1);
							last_coord_x=(int)wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX1-80-20;
							last_coord_y=(int)wire.p1.gatePlaceY-wire.p1.gatePresentInputOffsetY1;
							}
							else if(wire.p1.compName=="AND")
							{
								if(wire.offSetY1==10){
								g2.drawImage(horiz_wire, null,((int)wire.p1.gatePlaceX)+wire.p1.gatePresentInputOffsetX1-20,(int)wire.p1.gatePlaceY-wire.p1.gatePresentInputOffsetY1-40);
								last_coord_x=(int)wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX1-80-20;
								last_coord_y=(int)wire.p1.gatePlaceY-wire.p1.gatePresentInputOffsetY1-40;
								}
								else if(wire.offSetY1==30)
								{
									g2.drawImage(horiz_wire, null,((int)wire.p1.gatePlaceX)+wire.p1.gatePresentInputOffsetX2-25,(int)wire.p1.gatePlaceY+wire.p1.gatePresentInputOffsetY2+110);
									last_coord_x=(int)wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX2-80-25;
									last_coord_y=(int)wire.p1.gatePlaceY+wire.p1.gatePresentInputOffsetY2+110;
								}
							}
							else if(wire.p1.compName=="OR")
							{
								if(wire.offSetY1==10){
								g2.drawImage(horiz_wire, null,((int)wire.p1.gatePlaceX)+wire.p1.gatePresentInputOffsetX1,(int)wire.p1.gatePlaceY-wire.p1.gatePresentInputOffsetY1+60);
								last_coord_x=(int)wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX1-80;
								last_coord_y=(int)wire.p1.gatePlaceY-wire.p1.gatePresentInputOffsetY1+60;
								}
								else if(wire.offSetY1==30)
								{
									g2.drawImage(horiz_wire, null,((int)wire.p1.gatePlaceX)+wire.p1.gatePresentInputOffsetX2-25,(int)wire.p1.gatePlaceY+wire.p1.gatePresentInputOffsetY2+110);
									last_coord_x=(int)wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX2-80-25;
									last_coord_y=(int)wire.p1.gatePlaceY+wire.p1.gatePresentInputOffsetY2+110;
								}
							}
							g2.drawImage(top_left, null, last_coord_x-20,last_coord_y);
							
							
						/*	g2.drawImage(horiz_wire, null,((int)wire.p2.topLeft.getX())*10+175,((int)wire.p2.topLeft.getY())*10);
							val=(int)wire.p2.topLeft.getX()*10+155;prod++;
							g2.drawImage(bottom_right, null, ((int)wire.p2.topLeft.getX()*10+200),((int)wire.p2.topLeft.getY()*10)-20);
								
							g2.drawImage(vert_wire, null, ((int)wire.p2.topLeft.getX()*10+290),((int)wire.p2.topLeft.getY()*10-40));
								
							 last_coord_x=((int)wire.p2.topLeft.getX()*10+288);
							 last_coord_y=((int)wire.p2.topLeft.getY()*10-40);
						 */
							g2.drawImage(vert_wire,null,last_coord_x-30,last_coord_y+40);
							
							g2.drawImage(bottom_right,null,last_coord_x-120,last_coord_y+60);
							
							int last_coord_x1=last_coord_x-120;
							int last_coord_y1=last_coord_y+60;
							g2.drawImage(horiz_wire,null,last_coord_x-140,last_coord_y+80);
							
							pt1.setLocation(last_coord_x-140-175,last_coord_y+80-wire.p2.offsetOutputY1);
							
							
						}
						else if(flag1==1)
						{
							g2.drawImage(horiz_wire, null,(int)wire.p2.gatePlaceX+wire.p2.gatePresentOutputOffset,(int)wire.p2.gatePlaceY);
							val=(int)wire.p2.gatePlaceX+wire.p2.gatePresentOutputOffset;
							g2.drawImage(bottom_right, null, val+22,(int)wire.p2.gatePlaceY-17);
					
							int val1=val+22;
							g2.drawImage(vert_wire, null, val1+95,((int)wire.p2.gatePlaceY-40));
							
							last_coord_x=val1+95;
							last_coord_y=((int)wire.p2.gatePlaceY-40);					
						
						
						if(wire.offSetY1==10||wire.offSetY1==20)
							last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p1.gatePlaceY-wire.p1.offsetInputY1+20), 1);
						else if(wire.offSetY1==30){}
							last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p1.gatePlaceY-wire.p1.offsetInputY2+20), 1);
		       		
						last_coord_x=(int)last_coord.getX();//+3*(prod3)+9*(prod3);
						last_coord_y=(int)last_coord.getY();//+20-20*(prod3);
		        		
						g2.drawImage(top_left, null, last_coord_x,last_coord_y-38);
		        		
						last_coord_x=last_coord_x+80;
						last_coord_y=last_coord_y-38;
		        		
						int prod3=1;
		                
						if(wire.offSetY1==10||wire.offSetY1==20)
							last_coord=horizontalLine(last_coord_x, last_coord_y, (int)wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX1, last_coord_y);
						else if(wire.offSetY1==30)
							last_coord=horizontalLine(last_coord_x, last_coord_y, (int)wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX2, last_coord_y);
						
						last_coord_x=(int)last_coord.getX();//_x+20*(prod3)-20;
		        		
		        		pt1.setLocation(wire.p2.topLeft.getX()*10, wire.p2.topLeft.getY()*10);
		        		pt2.setLocation(last_coord_x-30,last_coord_y);
		        		}
		        		addGate(wire.p2, wire.p1, pt1, pt2,2,wire);
						
					}break;
					}
				}
				}			
			
			else if(wire.p1.topLeft.getY()>wire.p2.topLeft.getY())
			{
				{

					int prod1=1,flag1=0,flag2=0;
				//	Components wire.p2,wire.p1;	
				//	wire.p2=wire.wire.p1;
				//	wire.p1=wire.wire.p2;
					Point2D pt=new Point2D.Double();
					Point2D pt1=new Point2D.Double();
					Point2D pt2=new Point2D.Double();
					Point2D last_coord=new Point2D.Double();
					
					for(int k=0;k<gateImplemented.size();k++)
					{
						if(wire.p1==gateImplemented.get(k))
							{
								flag2=1;
								break;
							}
					}
					
					switch(flag2)
					{
					case 0:{
					
					System.out.println("offsety2 is"+wire.offSetY2);
					int val = 0;
					pt=getIntersectionPoint(val+90, val+180,  ((int)wire.p1.topLeft.getX()*10-150), ((int)wire.p1.topLeft.getX()*10-110),
							((int)wire.p2.topLeft.getY())*10,
							((int)wire.p2.topLeft.getY())*10,
							((int)wire.p1.topLeft.getY()*10-36), ((int)wire.p1.topLeft.getY()*10-116));

					val=0;int prod = 1;prod1=1;
					for(int k=0;k<gateImplemented.size();k++)
					{
						if(wire.p2==gateImplemented.get(k))
							flag1=1;
					}
					int last_coord_x,last_coord_y;
					
					if(flag1==0)
					{					
							g2.drawImage(horiz_wire, null,((int)wire.p2.topLeft.getX())*10+155,((int)wire.p2.topLeft.getY())*10);
							val=(int)wire.p2.topLeft.getX()*10+155;prod++;
							
						{
							last_coord=
									horizontalLine(val, ((int)wire.p2.topLeft.getY())*10,
											(int)pt.getX(), ((int)wire.p2.topLeft.getY())*10);
										
						}
							
						last_coord_x=(int)last_coord.getX();
				
						last_coord_y=(int)last_coord.getY();
						
						g2.drawImage(top_right, null, last_coord_x,last_coord_y);
					}
					else
					{
							g2.drawImage(horiz_wire, null,(int)wire.p2.gatePlaceX+wire.p2.wireOffsetX,(int)wire.p2.gatePlaceY+wire.p2.wireOffsetY);
							val=(int)wire.p2.gatePlaceX+wire.p2.wireOffsetX;prod++;
		
							{       					
								//g2.drawImage(horiz_wire, null,(val)+90*(prod1++),(int)wire.p2.gatePlaceY+wire.p2.wireOffsetY);
								last_coord=
										horizontalLine(val, (int)wire.p2.gatePlaceY+wire.p2.wireOffsetY,
												(int)pt.getX(), (int)wire.p2.gatePlaceY+wire.p2.wireOffsetY);
								
							}
							
						last_coord_x=(int)last_coord.getX();					
						last_coord_y=(int)last_coord.getY();
						
						g2.drawImage(top_right, null, last_coord_x,last_coord_y);
					}
					pt1.setLocation(((int)wire.p2.topLeft.getX())*10,((int)wire.p2.topLeft.getY())*10);
					
					int prod3=1;
					g2.drawImage(vert_wire, null, last_coord_x+35,last_coord_y+100);
					
					last_coord_x=last_coord_x+36;
					last_coord_y=last_coord_y+80;
					
					if(wire.offSetY1==10||wire.offSetY1==20)
					{
						last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p1.topLeft.getY()*10)+wire.p1.offsetInputY1,0);
					}
					else
						if(wire.offSetY1==30)
						{						
							System.out.println("wireoffset in y2 is "+wire.offSetY2);
								
							System.out.println("**********************");
							last_coord=verticalLine(last_coord_x, last_coord_y, 0, ((int)wire.p1.topLeft.getY()*10)+wire.p1.offsetInputY2,0);
							
						}
									
					last_coord_x=(int)last_coord.getX()-3*(prod3)-9*(prod3);
					last_coord_y=(int)last_coord.getY()+20*(prod3);
					
					g2.drawImage(bottom_left, null, last_coord_x-35,last_coord_y-20);

					last_coord_x=last_coord_x+60;//+120;
					last_coord_y=last_coord_y+60;//+60;
					
					if(wire.offSetY1==10||wire.offSetY1==20)
					{
						pt2.setLocation(last_coord_x,last_coord_y);
					}
					else
						if(wire.offSetY1==30)
						{
							last_coord=horizontalLine
							(last_coord_x, last_coord_y,((int)wire.p1.topLeft.getX()*10)+wire.p1.offsetInputX2,last_coord_y);
							pt2.setLocation(last_coord.getX()-10,last_coord.getY());		
						}

					addGate(wire.p2, wire.p1,pt1,pt2,1,wire);
					
					break;
					}
					
					case 1:
					{
						System.out.println("********************");
						System.out.println("offsety2 is"+wire.offSetY2);
						int val = 0;flag1=0;
						
						pt=getIntersectionPoint(val+90, val+180,  ((int)wire.p1.gatePlaceX+wire.p1.IntersectionOffsetX), ((int)wire.p1.gatePlaceX+wire.p1.IntersectionOffsetX+40),
								((int)wire.p2.topLeft.getY())*10,
								((int)wire.p2.topLeft.getY())*10,
								((int)wire.p1.gatePlaceY+wire.p1.IntersectionOffsetY+80), ((int)wire.p1.gatePlaceY+wire.p1.IntersectionOffsetY)); 	
						
						val=0;int prod = 1;prod1=1;
						for(int k=0;k<gateImplemented.size();k++)
						{
							if(wire.p2==gateImplemented.get(k))
								flag1=1;
						}
						int last_coord_x,last_coord_y;
						
						if(flag1==0)
						{
								g2.drawImage(horiz_wire, null,((int)wire.p2.topLeft.getX())*10+180,((int)wire.p2.topLeft.getY())*10);
								last_coord_x=((int)wire.p2.topLeft.getX())*10+160;
					
								val=last_coord_x+20*prod++;
						 						
							last_coord_x=(val); 
							last_coord_y=((int)wire.p2.topLeft.getY())*10;
							g2.drawImage(top_right, null, last_coord_x,last_coord_y);
						}
						else
						{
							{
								g2.drawImage(horiz_wire, null,(int)wire.p2.gatePlaceX+wire.p2.gatePresentOutputOffset,(int)wire.p2.gatePlaceY);//155//-10
								val=(int)wire.p2.gatePlaceX+wire.p2.gatePresentOutputOffset;prod++;
							}
							 last_coord_x=(val)+20*(prod1++); 
							 last_coord_y=(int)wire.p2.gatePlaceY;
							
							g2.drawImage(top_right, null, last_coord_x,last_coord_y);//+180
						}
						
						pt1.setLocation(((int)wire.p2.topLeft.getX())*10,((int)wire.p2.topLeft.getY())*10);
						
						int prod3=1;
						g2.drawImage(vert_wire, null, last_coord_x+35,last_coord_y+100);
						
						if(wire.offSetY1==10||wire.offSetY1==20)
						{
							last_coord=verticalLine(last_coord_x+36, last_coord_y+80, 0, ((int)wire.p1.gatePlaceY)+wire.p1.gatePresentInputOffsetY1,0);
						}
						else
							if(wire.offSetY1==30)
							{
					
								System.out.println("********offsety2 is "+wire.p1.gatePresentInputOffsetY2);
								last_coord=verticalLine(last_coord_x+36, last_coord_y+80, 0, ((int)wire.p1.gatePlaceY)+wire.p1.gatePresentInputOffsetY2,0);
															
							}
				
						last_coord_x=(int)last_coord.getX()-3*(prod3)-9*(prod3);
						last_coord_y=(int)last_coord.getY()+20*(prod3);
						
						g2.drawImage(bottom_left, null, last_coord_x-35,last_coord_y-20);
					
						g2.drawImage(horiz_wire, null,last_coord_x+80,last_coord_y+60);
					
						last_coord_x=last_coord_x+80;
						last_coord_y=last_coord_y+60;
						
						prod1=1;
					
						if(wire.p1.compName=="NOT")System.out.println("not value is "+ wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX2);
						if(wire.p1.compName=="AND")System.out.println("and value is "+ wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX2);
						if(wire.offSetY1==10)						
							last_coord=horizontalLine(last_coord_x, last_coord_y, wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX1, last_coord_y);
						else
						if(wire.offSetY1==30)
							last_coord=horizontalLine(last_coord_x, last_coord_y, wire.p1.gatePlaceX+wire.p1.gatePresentInputOffsetX2, last_coord_y);	
					    
						last_coord_x=(int)last_coord.getX();	//+20*(prod1++);
						last_coord_y=last_coord_y;
						
						pt2.setLocation(last_coord_x,last_coord_y);
						addGate(wire.p2, wire.p1,pt1,pt2,1,wire);break;	
					}
					
					}
					
				}
					}}
				
	}
	
	public static void addGate(Components p1, Components p2,Point2D pt1,Point2D pt2,int wire_case,WireComponent wire)
	{
		int flagP1=0;
		int flagP2=0;
		try {
			GateImage_p1=ImageIO.read(new File(p1.antFile));
			GateImage_p2=ImageIO.read(new File(p2.antFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<gateImplemented.size();i++)
		{
			if(p1==gateImplemented.get(i))
				flagP1=1;
			if(p2==gateImplemented.get(i))
				flagP2=1;
		}
		System.out.println("flags at upar are "+flagP1+" "+flagP2+" "+gateImplemented.size());
		//switch(wire_case)
		//{
		//	case 1:
			{	
				if(flagP1==0)
					{
						g2.drawImage(GateImage_p1, null, (int)pt1.getX()+p1.offsetOutputX1,(int)pt1.getY()+p1.offsetOutputY1);
						gateImplemented.add(p1);				
						p1.gatePlaceX=(int)pt1.getX()+p1.offsetOutputX1;
						p1.gatePlaceY=(int)pt1.getY()+p1.offsetOutputY1;
						
						System.out.println("p1 "+p1.gatePlaceX+" "+p1.gatePlaceY);
					}
				if(flagP2==0)
				{
				if(p2.compName=="NOT")
					{
						g2.drawImage(GateImage_p2, null, (int)pt2.getX()+p2.offsetInputX1,(int)pt2.getY()+p2.offsetInputY1);
						p2.gatePlaceX=(int)pt2.getX()+p2.offsetInputX1;
						p2.gatePlaceY=(int)pt2.getY()+p2.offsetInputY1;
						System.out.println("p2 "+p2.gatePlaceX+" "+p2.gatePlaceY);
					}
				else
					if((p2.compName=="AND"||p2.compName=="OR")&&(wire.offSetY1==10)||(wire.offSetY2==10))
					{
						g2.drawImage(GateImage_p2, null, (int)pt2.getX()+p2.offsetInputX1,(int)pt2.getY()+p2.offsetInputY1);
						p2.gatePlaceX=(int)pt2.getX()+p2.offsetInputX1;
						p2.gatePlaceY=(int)pt2.getY()+p2.offsetInputY1;
						System.out.println("p2 "+p2.gatePlaceX+" "+p2.gatePlaceY);
					}
				else
					if((p2.compName=="AND"||p2.compName=="OR")&&(wire.offSetY1==30)||(wire.offSetY2==30))
					{
						g2.drawImage(GateImage_p2, null, (int)pt2.getX()+p2.offsetInputX2,(int)pt2.getY()+p2.offsetInputY2);
						p2.gatePlaceX=(int)pt2.getX()+p2.offsetInputX2;
						p2.gatePlaceY=(int)pt2.getY()+p2.offsetInputY2;
					}
				gateImplemented.add(p2);
				
			}
	//			break;
			}
	//		case 2:
			{
	//			g2.drawImage(GateImage_p1, null, (int)pt1.getX(),(int)pt1.getY());
	//			g2.drawImage(GateImage_p2, null, (int)pt2.getX(),(int)pt2.getY());
	//			break;
			}
//		}
		System.out.println("flags at neeche are "+flagP1+" "+flagP2+" "+gateImplemented.size());
	}

	public static Point2D.Double horizontalLine(int x1,int y1,int x2,int y2)
	{
		int val=x1,prod1=1;
		for(;(val)+20*(prod1)<=x2;)			
		{
			g2.drawImage(horiz_wire, null,(val)+20*(prod1++),y1);
		}
		return new Point2D.Double((val)+20*(prod1++),y2);
	}
	
	public static Point2D.Double verticalLine(int x1,int y1,int x2,int y2,int wire_case)
	{	
		switch(wire_case){
		
		case 0:{
		int prod3=1;
		for (;y1+20*(prod3)<=y2;)
		{
			g2.drawImage(vert_wire, null, x1-3*(prod3)-9*(prod3++),y1+20*(prod3));//+3+24*(prod3));
		}
			return new Point2D.Double(x1-3*(prod3)-9*(prod3++),y1+20*(prod3));		
		}
		
		case 1:{
			int prod3=1;
     		for(;y1-20*(prod3)>=y2;)
    			{
        			g2.drawImage(vert_wire, null, x1+3*(prod3)+9*(prod3++),y1+20-20*(prod3));
    			}
     		return new Point2D.Double(x1+3*(prod3)+9*(prod3),y1+20-20*(prod3));
		}
		
		default: return null;
		}
	}

	public static Point2D.Double getIntersectionPoint(int x1,int x2,int x3,int x4,int y1,int y2,int y3,int y4) {

		int px = x1,
				py = y1,
				rx = x2-x1,
				ry = y2-y1;
		int qx = x3,
				qy = y3,
				sx = x4-x3,
				sy = y4-y3;

		int det = sx*ry - sy*rx;
		if (det == 0) {
			return null;
		} else {
			int z = (sx*(qy-py)+sy*(px-qx))/det;
			//  if (z==0 ||  z==1) return null;  // intersection at end point!

			return new Point2D.Double(
					(int)(px+z*rx), (int)(py+z*ry));
		}
	} 
}
