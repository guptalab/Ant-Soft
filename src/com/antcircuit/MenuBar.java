package com.antcircuit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MenuBar implements ActionListener, ItemListener {

	static MenuBar myMenu;

	public static JMenuBar createMenuBar() {

		myMenu = new MenuBar();

		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("New", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(myMenu);

		menuItem = new JMenuItem("Open");
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);
		menuItem.addActionListener(myMenu);

		submenu = new JMenu("Open Recent");
		submenu.setMnemonic(KeyEvent.VK_S);
		menuItem.addActionListener(myMenu);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.ALT_MASK));
		submenu.add(menuItem);
		menuItem.addActionListener(myMenu);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);
		menuItem.addActionListener(myMenu);

		menuItem = new JMenuItem("Save");
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
		menuItem.addActionListener(myMenu);

		menuItem = new JMenuItem("Save As");
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
		menuItem.addActionListener(myMenu);

		// a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();

		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);
		rbMenuItem.addItemListener(myMenu);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);
		rbMenuItem.addItemListener(myMenu);

		// a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);
		cbMenuItem.addItemListener(myMenu);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);
		cbMenuItem.addItemListener(myMenu);

		// Build second menu in the menu bar.
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu);
		menuItem.addActionListener(myMenu);

		menuItem = new JMenuItem("Undo");
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);
		menuItem.addActionListener(myMenu);

		menuItem = new JMenuItem("Redo");
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);
		menuItem.addActionListener(myMenu);

		// Build third menu in the menu bar.
		menu = new JMenu("Project");
		menu.setMnemonic(KeyEvent.VK_P);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Add Circuit");
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);
		menuItem.addActionListener(myMenu);

		return menuBar;
	}

	protected static ImageIcon createImageIcon(String path) {
		return null;

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		System.out.println("bakchodi");
		JMenuItem source = (JMenuItem)(a.getSource());
		if(source.getText().equals("Undo")) {
			AntGUI.undoCaller();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		System.out.println("ollalaalaa");
	}
}