package com.antcircuit;

import java.awt.geom.Point2D;

public class AndGate extends Components {
		
	public AndGate(int x, int y) {
		
		double newX = (double) x;
		double newY = (double) y;
		Point2D p = new Point2D.Double(x,y);
		topLeft = p;
		compName = "AND";
		
		
				
	}
	

}
