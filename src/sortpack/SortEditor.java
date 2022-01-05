package sortpack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;

public class SortEditor implements ActionListener{
	
	JFrame f = new JFrame();
	GridBagConstraints c = new GridBagConstraints();
	
	JLabel barsLabel = new JLabel("Bar Count: ");
	JSlider barsSlider = new JSlider(JSlider.VERTICAL, 10, 100, 10); 
	
	SortEditor() {
		f.setLayout(new GridBagLayout());
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setSize(300, 400);
		f.setVisible(false);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.anchor = GridBagConstraints.EAST;
		
		c.gridx = 0;
		c.gridy = 0; 
		f.add(barsLabel, c);
		
		c.gridx = 0; 
		c.gridy = 1; 
		f.add(barsSlider, c);
		
		Timer t = new Timer(1000/60, this);
		t.start(); 
		
	}
	
	public void actionPerformed(ActionEvent e) {
		barsLabel.setText("Bar Count: " + barsSlider.getValue());
	}
	
	public int getBarsValue() {
		return barsSlider.getValue();
	}
	
	public void setVisible(boolean b) {
		f.setVisible(b);
	}
}
