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

  String[] sortExamples = { "Selection Sort", "Bubble Sort", "Insertion Sort", "Quick Sort",
      "Bogo Sort", "Merge Sort", "Heap Sort" };

  JFrame f = new JFrame("Settings");
  GridBagConstraints c = new GridBagConstraints();

  JLabel barsLabel = new JLabel("Bar Count: ");
  JSlider barsSlider = new JSlider(JSlider.HORIZONTAL, 10, 30, 10);

  JLabel sortType = new JLabel("Sort Type: ");
  JComboBox<String> sortList = new JComboBox<String>(this.sortExamples);

  SortEditor() {
    this.f.setLayout(new GridBagLayout());
    this.f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.f.setSize(300, 200);
    this.f.setVisible(false);

    this.c.fill = GridBagConstraints.HORIZONTAL;
    this.c.weighty = 0;
    this.c.weightx = 1;
    this.c.insets = new Insets(10, 10, 10, 10);

    this.c.fill = GridBagConstraints.HORIZONTAL;
    this.c.weighty = 0;
    this.c.weightx = 1;
    this.c.gridwidth = 1;
    this.c.insets = new Insets(10, 10, 0, 0);

    this.c.gridx = 0;
    this.c.gridy = 0;
    this.f.add(this.barsLabel, this.c);

    this.c.gridx = 1;
    this.c.gridy = 0;
    this.c.insets = new Insets(10, 0, 0, 10);
    this.f.add(this.barsSlider, this.c);

    this.c.gridx = 0;
    this.c.gridy = 1;
    this.c.gridwidth = 2;
    this.c.insets = new Insets(30, 10, 0, 10);
    this.f.add(this.sortType, this.c);

    this.c.gridx = 0;
    this.c.gridy = 2;
    this.c.gridwidth = 2;
    this.c.insets = new Insets(10, 10, 0, 10);
    this.f.add(this.sortList, this.c);

    this.sortList.setEditable(false);
    this.sortList.addActionListener(this);

    Timer t = new Timer(1000 / 60, this);
    t.start();

  }

  public void actionPerformed(ActionEvent e) {
    this.barsLabel.setText("Bar Count: " + this.barsSlider.getValue());
  }

  public int getBarsValue() {
    return this.barsSlider.getValue();
  }

  public String getSelectedSort() {
    return (String) this.sortList.getSelectedItem();
  }

  public void setVisible(boolean b) {
    this.f.setVisible(b);
  }
}
