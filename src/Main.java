import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {

    JFrame f = new JFrame("Sort Simulation");
    f.setLayout(new BorderLayout());
    SortPanel p = new SortPanel();
    p.setVisible(true);
    f.add(p, BorderLayout.CENTER);
    f.setSize(300, 400);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
