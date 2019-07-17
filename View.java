package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private PaintModel model;
	
	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private FontChooserPanel fontChooserPanel;
	private ColourChooserPanel colourChooserPanel;
	private FillChooserPanel fillChooserPanel;
	
	
	/**
	 * @param model
	 */
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		Container c=this.getContentPane();
		JPanel panel = new JPanel();
		this.colourChooserPanel = new ColourChooserPanel(this);
		panel.add(this.colourChooserPanel,BorderLayout.WEST);
		this.fillChooserPanel = new FillChooserPanel(this);
		panel.add(this.fillChooserPanel,BorderLayout.EAST);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.shapeChooserPanel,BorderLayout.WEST);
		
		//adds the JComboBox for choosing font size
		this.fontChooserPanel = new FontChooserPanel(this);
		c.add(this.fontChooserPanel,BorderLayout.NORTH);
	
		this.model=model;
		
		this.paintPanel = new PaintPanel(model, this);
		c.add(this.paintPanel, BorderLayout.CENTER);
		c.add(panel, BorderLayout.SOUTH);
	
		this.pack();
		// this.setSize(200,200);
		this.setVisible(true);
	}

	/**
	 * Adds the painting area to the frame
	 * @return paintPanel
	 */
	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	/**
	 * Adds the shapePanel to the frame
	 * @return shapeChooserPanel
	 */
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	/**
	 * Adds the FontPanel to the frame
	 * @return FontChooserPanel
	 */
	public FontChooserPanel getFontChooserPanel(){
		return fontChooserPanel;
	}
	
	/**
	 * Adds the ColourPanel to the frame
	 * @return ColourChooserPanel
	 */
	public ColourChooserPanel getColourChooserPanel(){
		return colourChooserPanel;
	}

	/**
	 * Adds the menubar to the frame
	 * @return menubar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}
}
