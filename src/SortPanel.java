import java.awt.Color;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SortPanel extends JPanel implements ActionListener {

  /**
   * Default Auto-generated serialize version ID
   */
  private static final long serialVersionUID = 1L;

  final int SCALE = 20;
  int count = 0;
  int start = 0;
  int bars = 10;

  boolean startSorting = false;

  GridBagConstraints c = new GridBagConstraints();
  ArrayList<Integer> arr = new ArrayList<Integer>();

  boolean sorted = this.checkSorted();

  JButton sort = new JButton("Sort");
  JButton settings = new JButton("Settings");
  JButton reset = new JButton("Reset Bars");

  SortEditor se = new SortEditor();

  public SortPanel() {
    this.setLayout(new GridBagLayout());
    this.setFocusable(true);

    // sort button
    this.c.fill = GridBagConstraints.HORIZONTAL;
    this.c.weighty = 0;
    this.c.weightx = 0.5;
    this.c.anchor = GridBagConstraints.PAGE_START;
    this.c.ipadx = 0;
    this.c.gridx = 0;
    this.c.gridy = 0;
    this.add(this.sort, this.c);
    this.sort.addActionListener(this);

    // reset button
    this.c.fill = GridBagConstraints.HORIZONTAL;
    this.c.weighty = 0;
    this.c.weightx = 0.5;
    this.c.anchor = GridBagConstraints.PAGE_START;
    this.c.ipadx = 0;
    this.c.gridx = 1;
    this.c.gridy = 0;
    this.add(this.reset, this.c);
    this.reset.setVisible(true);
    this.reset.addActionListener(this);

    // settings button
    this.c.fill = GridBagConstraints.HORIZONTAL;
    this.c.weighty = 1;
    this.c.weightx = 1.0;
    this.c.anchor = GridBagConstraints.PAGE_START;
    this.c.ipadx = 0;
    this.c.gridx = 0;
    this.c.gridwidth = 2;
    this.c.gridy = 1;
    this.add(this.settings, this.c);
    this.settings.setVisible(true);
    this.settings.addActionListener(this);

    // start the timer with 100 ms tick
    Timer t = new Timer(100, this);
    t.start();

    // generates this arraylist with the certain number of bars specified.
    this.generateNumbers(this.bars);
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
      int random = this.randomInt(1, arrTen.size());
      this.arr.add(this.SCALE * arrTen.get(random));
      arrTen.remove(random);
    }
  }

  public void actionPerformed(ActionEvent e) {

    if (this.startSorting) {
      if (this.se.getSelectedSort() == "Selection Sort") {
        this.selectionSort();
        this.sleep(100);
      }
      else if (this.se.getSelectedSort() == "Bubble Sort") {
        this.bubbleSort();
        this.sleep(100);
      }
      else if (this.se.getSelectedSort() == "Bogo Sort") {
        this.bogoSort();
        this.sleep(100);
      }
      else if (this.se.getSelectedSort() == "Insertion Sort") {
        ArrayList<Integer> newArr = new ArrayList<>();
        this.insertionSort(newArr);
        this.sleep(100);
      }
      else if (this.se.getSelectedSort() == "Quick Sort") {
        this.quickSort();
        this.sleep(100);
      }
      else if (this.se.getSelectedSort() == "Merge Sort") {
        this.mergeSort();
        this.sleep(100);
      }
      else if (this.se.getSelectedSort() == "Heap Sort") {
        this.heapSort();
        this.sleep(100);
      }

      if (this.sorted) {
        this.startSorting = false;
      }
    }

    if (e.getActionCommand() == "Sort") {
      this.startSorting = true;
    }

    if (e.getActionCommand() == "Settings") {
      this.se.setVisible(true);
    }

    if (e.getActionCommand() == "Reset Bars") {
      this.arr.clear();
      this.sorted = this.checkSorted();
      this.count = 0;
      this.start = 0;
      this.startSorting = false;
      this.generateNumbers(this.se.getBarsValue());
    }

    this.repaint();
  }

  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.BLUE);
    this.drawGraph(g, this.sorted);
    g.drawString("Swaps Until Sorted: " + this.count, 10, 80);
  }

  public void drawGraph(Graphics g, boolean color) {
    for (int i = 0; i < this.arr.size(); i++) {
      int margin = 10;
      int height = this.arr.get(i);
      int maxHeight = this.getHeight();
      int width = (this.getWidth() - margin * 2) / this.arr.size();

      this.drawBar(g, margin + width * i, maxHeight - height - margin, width, height, color);
    }
  }

  public void drawBar(Graphics g, int x, int y, int width, int height, boolean color) {
    if (!color) {
      g.setColor(Color.BLUE);
      g.fillRect(x, y, width, height);
      g.setColor(Color.BLACK);
      g.drawRect(x, y, width, height);
    }
    else {
      g.setColor(Color.GREEN);
      g.fillRect(x, y, width, height);
      g.setColor(Color.BLACK);
      g.drawRect(x, y, width, height);
    }
  }

  public void selectionSort() {
    this.sorted = this.checkSorted();
    if (!this.sorted) {
      int min = this.count;
      for (int j = min + 1; j < this.arr.size(); j++) {
        if (this.arr.get(j) < this.arr.get(min)) {
          min = j;
        }
      }
      this.swap(this.count, min);
      this.count += 1;

    }
  }

  public void bubbleSort() {
    if (!this.sorted) {
      if (this.start >= this.arr.size() - 1) {
        this.start = 0;
      }

      if (this.arr.get(this.start) > this.arr.get(this.start + 1)) {
        this.swap(this.start, this.start + 1);
        this.count += 1;
      }
      this.start += 1;
      this.sorted = this.checkSorted();
    }
  }

  public void bogoSort() {
    this.sorted = this.checkSorted();
    if (!this.sorted) {
      for (int i = 0; i < this.arr.size(); i++) {
        this.swap(i, this.randomInt(0, this.arr.size() - 1));
        this.count += 1;
        this.sorted = this.checkSorted();
        if (this.sorted) {
          break;
        }
      }
    }
  }

  public void insertionSort(ArrayList<Integer> newArr) {
    this.sorted = this.checkSorted();
    if (!this.sorted) {
      // unsure how to visualize this
      // need to figure it out
      return;
    }
  }

  public void heapSort() {
    this.sorted = this.checkSorted();
    if (!this.sorted) {
      // need to add upheap and downheap
      // and the resultant list is sorted
      return;
    }
  }

  public void quickSort() {
    this.sorted = this.checkSorted();
    if (!this.sorted) {
      // add pivot element
      return;
    }
  }

  public void mergeSort() {
    this.sorted = this.checkSorted();
    if (!this.sorted) {
      // add merge of two lists
      return;
    }
  }

  // swaps two elements at certain indecies in the
  // arraylist
  public void swap(int index, int swapIndex) {
    int temp = this.arr.get(swapIndex);
    this.arr.set(swapIndex, this.arr.get(index));
    this.arr.set(index, temp);
  }

  // delays the task from running for a certain number of
  // milliseconds
  public void sleep(int millis) {
    try {
      Thread.sleep(millis);
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }

  // determines whether this arr is sorted
  // and produces the boolean result
  public boolean checkSorted() {
    if (this.arr.size() == 0) {
      return false;
    }
    for (int i = 0; i < this.arr.size() - 1; i++) {
      for (int j = i + 1; j < this.arr.size(); j++) {
        if (this.arr.get(i) > this.arr.get(j)) {
          return false;
        }
      }
    }
    return true;
  }
}