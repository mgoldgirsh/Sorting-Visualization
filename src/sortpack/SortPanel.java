package sortpack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SortPanel extends JPanel implements ActionListener {

	int scale = 20;
	int count = 0;
	int bars = 10;

	int start = 0;

	GridBagConstraints c = new GridBagConstraints();
	ArrayList<Integer> arr = new ArrayList<Integer>();

	boolean sorted = checkSorted();

	JButton sort = new JButton("Sort");
	JButton settings = new JButton("Settings");
	JButton reset = new JButton("Reset Bars");

	SortEditor se = new SortEditor();

	public SortPanel() {
		setLayout(new GridBagLayout());
		setFocusable(true);

		// sort button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.PAGE_START;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 0;
		add(sort, c);
		sort.addActionListener(this);

		// reset button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.PAGE_START;
		c.ipadx = 0;
		c.gridx = 1;
		c.gridy = 0;
		add(reset, c);
		reset.setVisible(true);
		reset.addActionListener(this);

		// settings button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.weightx = 1.0;
		c.anchor = GridBagConstraints.PAGE_START;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 1;
		add(settings, c);
		settings.setVisible(true);
		settings.addActionListener(this);

		Timer t = new Timer(1, this);
		t.start();

		generateNumbers(bars);
	}

	public int randomInt(int min, int max) {
		return (int) ((max - min + 1) * Math.random());
	}

	public void generateNumbers(int n) {
		ArrayList<Integer> arrTen = new ArrayList<Integer>();
		for (int i = 1; i < n + 1; i++) {
			arrTen.add(i);
		}

		while (arrTen.size() >= 1) {
			int random = randomInt(1, arrTen.size());
			arr.add(scale * arrTen.get(random));
			arrTen.remove(random);
		}
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
		if (e.getActionCommand() == "Sort") {
			if (se.getSelectedSort() == "Bubble Sort") {
				bubbleSort();
			} else if (se.getSelectedSort() == "Selection Sort") {
				selectionSort2();

			} else if (se.getSelectedSort() == "Bogo Sort") {
				bogoSort();
			} else {
				// do nothing
			}
		}

		if (e.getActionCommand() == "Settings") {
			se.setVisible(true);
		}

		if (e.getActionCommand() == "Reset Bars") {
			arr.clear();
			sorted = checkSorted();
			count = 0;
			start = 0;
			generateNumbers(se.getBarsValue());
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLUE);
		drawGraph(g);
		g.drawString("Swaps Until Sorted: " + start, 10, 80);
	}

	public void drawGraph(Graphics g) {
		for (int i = 0; i < arr.size(); i++) {
			int margin = 10;
			int height = arr.get(i);
			int maxHeight = scale * arr.size();
			int width = (this.getWidth() - margin * 2) / arr.size();

			drawBar(g, margin + width * i, maxHeight - height + 125, width, height);
			Toolkit.getDefaultToolkit().sync();
		}
	}

	public void drawBar(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		Toolkit.getDefaultToolkit().sync();
	}

	public void selectionSort() {
		if (!sorted) {
			for (int i = 0; i < arr.size() - 1; i++) {
				long cur = System.currentTimeMillis();
				int min = i;
				for (int j = i + 1; j < arr.size(); j++) {
					if (arr.get(j) < arr.get(min)) {
						min = j;
					}
				}

				// swap
				int temp = arr.get(i);
				arr.set(i, arr.get(min));
				arr.set(min, temp);
				repaint();
				count += 1;
				sorted = checkSorted();
				if (sorted) { // short-circuit behavior
					break;
				}

// 				figure out how to add delay? or change code
//				delay(100);
//				System.out.println(System.currentTimeMillis());
//				while (System.currentTimeMillis() < cur) {
//					
//				}
//				cur += 10000;

			}
			sorted = true;
		}
	}

	public void selectionSort2() {
		sorted = checkSorted();
		if (!sorted) {
			int min = this.start;
			for (int j = min + 1; j < arr.size(); j++) {
				if (arr.get(j) < arr.get(min)) {
					min = j;
				}
			}
			int temp = arr.get(this.start);
			arr.set(this.start, arr.get(min));
			arr.set(min, temp);
			this.start += 1;

			// sort.doClick();
			// repaint();
		}
	}

	public void bubbleSort() {
		if (!sorted) {
			for (int i = 0; i < arr.size() - 1; i++) {
				for (int j = i + 1; j < arr.size(); j++) {
					if (arr.get(j) < arr.get(i)) {
						// swap
						int temp = arr.get(i);
						arr.set(i, arr.get(j));
						arr.set(j, temp);
						repaint();
						count += 1;
						sorted = checkSorted();
						if (sorted) {
							break;
						}
					}
				}
			}
		}
	}

	public void bogoSort() {
		sorted = checkSorted();
		if (!sorted) {
			for (int i = 0; i < this.arr.size(); i++) {
				swap(i, randomInt(0, this.arr.size() - 1));
				this.start += 1;
			}
		}

	}

	public void swap(int index, int swapIndex) {
		int temp = this.arr.get(swapIndex);
		this.arr.set(swapIndex, this.arr.get(index));
		this.arr.set(index, temp);
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean checkSorted() {
		if (arr.size() == 0) {
			return false;
		}
		for (int i = 0; i < arr.size() - 1; i++) {
			for (int j = i + 1; j < arr.size(); j++) {
				if (arr.get(i) > arr.get(j)) {
					return false;
				}
			}
		}
		return true;
	}

}
