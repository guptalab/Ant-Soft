package com.antcircuit;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class WireComponent extends JPanel{
	
	Components p1,p2;
	int offSetX1,offSetY1,offSetX2,offSetY2;
	int xCoord,yCoord;
	public WireComponent(Components x, Components y)
	{
		p1=x;
		p2=y;
	}
	
	public void makeWire(Graphics2D g2d)
	{
		//if(DisplayCanvas.wireMouseDragged!=2){
		if(DisplayCanvas.wireMouseDragged==1)
		{
			g2d.drawLine((int)p1.topLeft.getX()+offSetX1,(int)p1.topLeft.getY()+offSetY1,xCoord,(int)p1.topLeft.getY()+offSetY1);
			g2d.drawLine((int)xCoord,(int)p1.topLeft.getY()+offSetY1,xCoord,yCoord);
		}
		else
		{	
			g2d.drawLine((int)p1.topLeft.getX()+offSetX1,(int)p1.topLeft.getY()+offSetY1,(int)p2.topLeft.getX()+offSetX2,(int)p1.topLeft.getY()+offSetY1);
			g2d.drawLine((int)p2.topLeft.getX()+offSetX2,(int)p1.topLeft.getY()+offSetY1,(int)p2.topLeft.getX()+offSetX2,(int)p2.topLeft.getY()+offSetY2);
		}
		//}
		//repaint();
	}
}
