package com.antcircuit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class AntGUI extends JFrame {

	Container container;
	
	public static DisplayCanvas canvas = null;
	public static AntDisplay antcanvas = null;

	public static final int COMP_OR = 1;
	public static final int COMP_AND = 2;
	public static final int COMP_NOT = 3;
	public static final int COMP_WIRE = 4;
	public static final int NONE = 0;
	
	public static int CompFLAG = NONE;
	public static int wireFLAG = NONE;
	public static int clearClicked = 0;
	public static int buttonclicked=0;
	
	JLabel andLabel;
	JLabel orLabel;
	JLabel notLabel;
	JLabel wireLabel;
	static JLabel selectedLabel;
	JLabel attr1;
	JLabel attr2;
	JLabel attr3;
	JLabel attr4;
	JLabel attr5;
	JLabel attr6;
	JLabel attr7;
	JLabel attr8;
	JLabel attr9;
	JLabel attr10;
	JLabel attr11;
	JLabel attr12;
	JComboBox combo1;
	JComboBox combo2;
	JComboBox combo3;
	JComboBox combo4;
	JComboBox combo5;
	JComboBox combo6;
	JComboBox combo7;
	JComboBox combo8;
	JComboBox combo9;
	JComboBox combo10;
	JComboBox combo11;
	JComboBox combo12;
	
	JButton clearButton;

	static JPanel DisplayPanel;
	JPanel DisplayPanel1;
	JPanel DisplayPanel2;
	JPanel sidePanel;
	JPanel toolPanel;
	static JPanel attributePanel;
	JPanel andAttributePanel;
	JPanel orAttributePanel;
	JPanel notAttributePanel;
	JPanel wireAttributePanel;
	JPanel defAttributePanel;
		
	String toolSelected;
	String[] orientation = {"EAST","WEST","NORTH","SOUTH"};
	String[] o2 = {"a","b", "c", "d"};
	String[] o3 = {"e","f", "g", "h"};
	String[] o4 = {"i","j", "k", "l"};
	String[] o5 = {"m","n", "o", "p"};
	String[] o6 = {"q","r", "s", "t"};
	String[] o7 = {"u","v", "w", "x"};
	String[] o8 = {"1","2", "3", "4"};
	String[] o9 = {"5","6", "7", "8"};
	String[] o10 = {"11","12", "13", "14"};
	String[] o11 = {"15","16", "17", "18"};
	String[] o12 = {"25","26", "27", "28"};
	
	public AntGUI() {

		container = getContentPane();

		canvas = new DisplayCanvas();
				
		antcanvas=new AntDisplay();
		
		DisplayPanel=new JPanel(new CardLayout());
		DisplayPanel1=new JPanel(new BorderLayout());
		DisplayPanel2=new JPanel(new BorderLayout());
		DisplayPanel1.add(canvas, BorderLayout.CENTER);
		DisplayPanel2.add(antcanvas, BorderLayout.CENTER);
		DisplayPanel.add(DisplayPanel1,"canvas");
		DisplayPanel.add(DisplayPanel2,"antcanvas");
		setDisplayPanel("antcanvas");
		container.add(DisplayPanel, BorderLayout.CENTER);
		
		sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(3, 1));
			
		toolPanel = new JPanel();
		toolPanel.setLayout(new GridLayout(1, 7));
		
		andLabel = new JLabel();
		orLabel = new JLabel();
		notLabel = new JLabel();
		wireLabel = new JLabel("wire");
	
		andLabel.setIcon(new ImageIcon(getClass().getResource("and.gif")));
		andLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				CompFLAG = COMP_AND;
				DisplayCanvas.selectComponenent="off";
				AntGUI.wireFLAG = AntGUI.NONE;
				andLabel.setOpaque(true);
				andLabel.setBackground(Color.green);
				orLabel.setBackground(Color.WHITE);
				notLabel.setBackground(Color.WHITE);
				wireLabel.setBackground(Color.WHITE);
				toolSelected = "AND";
				setAttributePanel("Tool : AND GATE", "AND", -1);
			}
		});

		orLabel.setIcon(new ImageIcon(getClass().getResource("or.gif")));
		orLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				CompFLAG = COMP_OR;
				AntGUI.wireFLAG = AntGUI.NONE;
				DisplayCanvas.selectComponenent="off";
				orLabel.setOpaque(true);
				orLabel.setForeground(Color.green);
				orLabel.setBackground(Color.green);
				andLabel.setBackground(Color.WHITE);
				notLabel.setBackground(Color.WHITE);
				wireLabel.setBackground(Color.WHITE);
				toolSelected = "OR";
				setAttributePanel("Tool : OR GATE", "OR", -1);
			}
		});

		notLabel.setIcon(new ImageIcon(getClass().getResource("not.gif")));
		notLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				CompFLAG = COMP_NOT;
				DisplayCanvas.selectComponenent="off";
				AntGUI.wireFLAG = AntGUI.NONE;
				notLabel.setOpaque(true);
				notLabel.setForeground(Color.green);
				notLabel.setBackground(Color.green);
				orLabel.setBackground(Color.WHITE);
				andLabel.setBackground(Color.WHITE);
				wireLabel.setBackground(Color.WHITE);
				toolSelected = "NOT";
				setAttributePanel("Tool : NOT GATE", "NOT", -1);
			}
		});

		wireLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				wireFLAG = COMP_WIRE;
				CompFLAG = NONE;
				DisplayCanvas.selectComponenent="off";
				System.out.println("wireflag is " + wireFLAG);
				wireLabel.setOpaque(true);
				wireLabel.setForeground(Color.green);
				wireLabel.setBackground(Color.green);
				orLabel.setBackground(Color.WHITE);
				notLabel.setBackground(Color.WHITE);
				andLabel.setBackground(Color.WHITE);
				toolSelected = "WIRE";
				setAttributePanel("Tool : WIRE", "WIRE", -1);
			}
		});

		clearButton = new JButton("Clear");
		clearButton.setBackground(new Color(59, 89, 182));
		clearButton.setForeground(Color.WHITE);
		clearButton.setFocusPainted(false);
		clearButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDisplayPanel("canvas");
				clearClicked = 1;
				DisplayCanvas.clear();
			}
		});
		
		JButton genAntCircuit = new JButton("Ant Circuit");
		genAntCircuit.setBackground(new Color(59, 89, 182));
		genAntCircuit.setForeground(Color.WHITE);
		genAntCircuit.setFocusPainted(false);
		genAntCircuit.setFont(new Font("Tahoma", Font.BOLD, 12));
		genAntCircuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//clearClicked = 1;
				setDisplayPanel("antcanvas");
				//DisplayCanvas.generate();
			}
		});


		toolPanel.add(andLabel);
		toolPanel.add(orLabel);
		toolPanel.add(notLabel);
		toolPanel.add(wireLabel);
		toolPanel.add(clearButton);
	toolPanel.add(genAntCircuit);
		
		attributePanel = new JPanel(new CardLayout());
		selectedLabel = new JLabel("Circuit : Untitled");
		
		setCombobox();
		setAttributes();
		setAttrPanels();
				
		attributePanel.add(defAttributePanel,"DEFAULT");
		attributePanel.add(andAttributePanel,"AND");
		attributePanel.add(orAttributePanel,"OR");
		attributePanel.add(notAttributePanel,"NOT");
		attributePanel.add(wireAttributePanel, "WIRE");
		
		setAttributePanel("CIRCUIT : UNTITLED", "DEFAULT", -1);
		
		sidePanel.add(toolPanel);
		sidePanel.add(selectedLabel);
		sidePanel.add(attributePanel);
		
		container.add(sidePanel, BorderLayout.WEST);
				
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public static void setDisplayPanel(String s) {
		CardLayout cl = (CardLayout)(DisplayPanel.getLayout());
		 if(s.equals("canvas")) {
			 
			 cl.show(DisplayPanel, "canvas");
		 } else if(s.equals("antcanvas")) {
			 cl.show(DisplayPanel, "antcanvas");
		 }
	}
	
	public static void setAttributePanel(String name, String s, int id) {
		System.out.println("madar");
		selectedLabel.setText(name);
		 CardLayout cl = (CardLayout)(attributePanel.getLayout());
		 if(CompFLAG == NONE) {
			 
			 cl.show(attributePanel, "DEFAULT");
			 
		 } else if(CompFLAG == COMP_AND) {
			 
			cl.show(attributePanel, "AND");
						
		} else if(CompFLAG == COMP_OR) {
			
			cl.show(attributePanel, "OR");
			
		} else if(CompFLAG == COMP_NOT) {
			
			cl.show(attributePanel, "NOT");
			
		}
	}
	
	public void setCombobox() {
		
		combo1 = new JComboBox(orientation);
		combo2 = new JComboBox(o2);
		combo3 = new JComboBox(o3);
		combo4 = new JComboBox(o4);
		combo5 = new JComboBox(o5);
		combo6 = new JComboBox(o6);
		combo7 = new JComboBox(o7);
		combo8 = new JComboBox(o8);
		combo9 = new JComboBox(o9);
		combo10 = new JComboBox(o10);
		combo11 = new JComboBox(o11);
		combo12 = new JComboBox(o12);
	}
	
	public void setAttributes() {
		
		attr1 = new JLabel("timepass1");
		attr2 = new JLabel("timepass2");
		attr3 = new JLabel("timepass3");
		attr4 = new JLabel("timepass4");
		attr5 = new JLabel("timepass5");
		attr6 = new JLabel("timepass6");
		attr7 = new JLabel("timepass7");
		attr8 = new JLabel("timepass8");
		attr9 = new JLabel("timepass9");
		attr10 = new JLabel("timepass10");
		attr11 = new JLabel("timepass11");
		attr12 = new JLabel("timepass12");
	}
	
	public void setAttrPanels() {
		
		andAttributePanel = new JPanel(new GridLayout(2,2));
		andAttributePanel.add(attr3);
		andAttributePanel.add(combo3);
		andAttributePanel.add(attr4);
		andAttributePanel.add(combo4);
	
		orAttributePanel = new JPanel(new GridLayout(2,2));
		orAttributePanel.add(attr5);
		orAttributePanel.add(combo5);
		orAttributePanel.add(attr6);
		orAttributePanel.add(combo6);
	
		notAttributePanel = new JPanel(new GridLayout(2,2));
		notAttributePanel.add(attr7);
		notAttributePanel.add(combo7);
		notAttributePanel.add(attr8);
		notAttributePanel.add(combo8);
	
		wireAttributePanel = new JPanel(new GridLayout(2,2));
		wireAttributePanel.add(attr9);
		wireAttributePanel.add(combo9);
		wireAttributePanel.add(attr10);
		wireAttributePanel.add(combo10);
	
		defAttributePanel = new JPanel(new GridLayout(2,2));
		defAttributePanel.add(attr11);
		defAttributePanel.add(combo11);
		defAttributePanel.add(attr12);
		defAttributePanel.add(combo12);
	}

	public static void main(String arg[]) {
		new AntGUI();
	}
}