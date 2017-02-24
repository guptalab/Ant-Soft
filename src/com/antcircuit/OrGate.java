package com.antcircuit;

import java.awt.geom.Point2D;

public class OrGate extends Components {
		
	public OrGate(int x, int y) {
		
		double newX = (double) x;
		double newY = (double) y;
		Point2D p = new Point2D.Double(x,y);
		topLeft = p;	
		compName = "OR";
		antFile="./src/com/antcircuit/antcomponents/OrGateExt.png";
		//gatePresentInputOffset=550;
		
		offsetInputX1=-65;
		offsetInputY1=-85;
		offsetInputX2=13;
		offsetInputY2=-235;
		offsetOutputX1=-345;
		offsetOutputY1=0;
		wireOffsetX=520;
		wireOffsetY=0;
		IntersectionOffsetX=-540;
		IntersectionOffsetY=-175;
		gatePresentInputOffsetX1=70;
		gatePresentInputOffsetY1=-30;
		gatePresentInputOffsetX2=-5;//-350
		gatePresentInputOffsetY2=125;
		//gatePresentInputOffsetX2=-368;//-368
		gatePresentOutputOffset=520;
		
	}
	

}
