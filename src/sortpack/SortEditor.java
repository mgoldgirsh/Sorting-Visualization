package sortpack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;

public class SortEditor implements ActionListener {

	String[] sortExamples = { 
			"Selection Sort", 
			"Bubble Sort", 
			"Insertion Sort", 
			"Quick Sort", 
			"Bogo Sort",
			"Merge Sort" };

	JFrame f = new JFrame();
	GridBagConstraints c = new GridBagConstraints();

	JLabel heading = new JLabel("Settings");

	JLabel barsLabel = new JLabel("Bar Count: ");
	JSlider barsSlider = new JSlider(JSlider.HORIZONTAL, 10, 100, 10);

	JLabel sortType = new JLabel("Sort Type: ");
	JComboBox<String> sortList = new JComboBox<String>(sortExamples);

	SortEditor() {
		f.setLayout(new GridBagLayout());
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setSize(300, 200);
		f.setVisible(false);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.weightx = 1;
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		f.add(heading, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.insets = new Insets(10, 10, 0, 0);

		c.gridx = 0;
		c.gridy = 1;
		f.add(barsLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 0, 0, 10);
		f.add(barsSlider, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(10, 10, 0, 10);
		f.add(sortType, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(10, 10, 0, 10);
		f.add(sortList, c);

		sortList.setEditable(false);
		sortList.addActionListener(this);

		Timer t = new Timer(1000 / 60, this);
		t.start();

	}

	public void actionPerformed(ActionEvent e) {
		barsLabel.setText("Bar Count: " + barsSlider.getValue());
	}

	public int getBarsValue() {
		return barsSlider.getValue();
	}

	public String getSelectedSort() {
		return (String) sortList.getSelectedItem();
	}

	public void setVisible(boolean b) {
		f.setVisible(b);
	}
}
