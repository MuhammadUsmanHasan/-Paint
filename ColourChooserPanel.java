package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




/**
 * @author TeamCopyPaste
 *
 *Initializes the colour chooser panel
 */
class ColourChooserPanel extends JPanel implements ActionListener {
	
	private View view; // So we can talk to our parent or other components of the view
	
	public ColourChooserPanel(View view) {	
		this.view=view;
		this.setSize(new Dimension(100,100));;
		this.setMaximumSize(new Dimension(100,100));
		Color[] buttonLabels = { Color.WHITE,Color.BLACK,Color.GREEN,Color.YELLOW,Color.MAGENTA,Color.RED,
				Color.ORANGE,Color.PINK,Color.GRAY,Color.cyan}; //the colours of our buttons
		this.setLayout(new GridLayout(2, 5)); //sets the xy position of this panel on the grid
		for (Color label : buttonLabels) { 
			String r =  Integer.toString(label.getRed()); //red value of colour
			String  g =  Integer.toString(label.getGreen()); //green value of colour
			String b = Integer.toString(label.getBlue()); //blue value of colour
			
			String s = r+" "+g+" "+b;
			JButton button = new JButton(s);
			this.add(button);
			button.addActionListener(this);
			button.setActionCommand(s);
			button.setText("               ");
			button.setBackground(label);
		}


		
	}

	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String [] colours = e.getActionCommand().split(" ");
		//We need RGB because colours in java is declared with the parametres red,green,blue
		int red = Integer.parseInt(colours[0]);
		int green = Integer.parseInt(colours[1]);
		int blue = Integer.parseInt(colours[2]);
		
		this.view.getPaintPanel().setNewColour(red,green,blue);
		

	}
}

	
		
		

