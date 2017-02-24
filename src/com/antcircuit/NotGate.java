package com.antcircuit;

import java.awt.geom.Point2D;

public class NotGate extends Components {
	
	 //int gatePlaceX;
	// int gatePlaceY;
	
	public NotGate(int x, int y) {
		
		double newX = (double) x;
		double newY = (double) y;
		Point2D p = new Point2D.Double(x,y);
		topLeft = p;
		compName = "NOT";				
		antFile="./src/com/antcircuit/antcomponents/NotGateSmall.png";
		offsetInputX1=30;
		offsetInputY1=-50;
		offsetOutputX1=0;
		offsetOutputY1=0;
		gateOffsetX=110;
		gateOffsetY=10;
		wireOffsetX=155;
		wireOffsetY=-15;
		IntersectionOffsetX=-250;
		IntersectionOffsetY=-116;
		gatePresentInputOffsetX1=0;//-20
		gatePresentInputOffsetY1=-50;//-30
		gatePresentOutputOffset=155;
	}
	

}
