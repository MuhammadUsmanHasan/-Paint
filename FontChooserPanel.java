package ca.utoronto.utm.paint;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;



/**
 * @author TeamCopyPaste
 * 
 * Initializes the JComboBox button, used to select a font size.
 *
 */
class FontChooserPanel extends JPanel implements ActionListener {
	
	private View view; // So we can talk to our parent or other components of the view
	private String[] buttonLabels = {"1","2","3","4","5","6","7","8","9","10","20","30","40","50"};
	private JComboBox font = new JComboBox(buttonLabels);
	private String size;
	public FontChooserPanel(View view) {	
		this.view=view;
		
		this.setLayout(new GridLayout(1,1));
		font.setEditable(true);
		this.add(font);
		font.addActionListener(this);
		
	}
	
	/**
	 * Controller aspect of this
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (font.getSelectedItem() != null){
			size = ((String)font.getSelectedItem());
			try{
			this.view.getPaintPanel().setSize(Integer.parseInt(size));
			}
			catch(NumberFormatException d){
				
			}
		}

		
	}

	
}