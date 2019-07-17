package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



/**
 * @author TeamCopyPaste
 *
 *Initializes the buttons to choose shapes, constructs their shape.
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	
	private View view; // So we can talk to our parent or other components of the view
	private static JButton first = new JButton();
	public ShapeChooserPanel(View view) {	
		this.view=view;
		
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };
		this.setLayout(new GridLayout(buttonLabels.length, 1));
		for (String label : buttonLabels) {
			JButton button = new JButton();
			Image img;
			try {
				img = ImageIO.read(getClass().getResource(label+".bmp"));
				ImageIcon pic = new ImageIcon(img);
				button = new JButton(pic);
				button.setActionCommand(label);
			} catch (IOException e) {
			}
			this.add(button);
			button.addActionListener(this);
		}
	}
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setMode(e.getActionCommand());
		JButton b = (JButton) e.getSource();
		if(first.equals(b)){
			
		}
		else{
			first.setEnabled(true);
			b.setEnabled(false);
			first = (JButton) e.getSource();
		}
		
		
		System.out.println(e.getActionCommand());
	}

	
}
