package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author CopyPaste
 *
 *Initializes the outline or fill buttons
 */
class FillChooserPanel extends JPanel implements ActionListener {
	
	private View view;
	private static JButton first = new JButton();
	
	public FillChooserPanel(View view) {	
		this.view=view;

		String[] buttonLabels = {"Outline","Inside"};
		this.setLayout(new GridLayout(2, 1));
		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			this.add(button);
			button.addActionListener(this);
		}
	}
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setColourArea(e.getActionCommand());
		System.out.println(e.getActionCommand());
		JButton b = (JButton) e.getSource();
		if(first.equals(b)){
			
		}
		else{
			first.setEnabled(true);
			b.setEnabled(false);
			first = (JButton) e.getSource();
		}
	}

	
}