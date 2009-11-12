package ui;

import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;

public class TestMiglayout {

    private enum TEST {A,B,C}

    public static void main(String[] args) {
        TestMiglayout test = new TestMiglayout();
        test.test();
    }

    private void test() {
        JPanel panel = new JPanel(new MigLayout("wrap 4", "grow", "grow"));
        panel.add(new JButton("1"), "span 3 4, grow");
        panel.add(new JButton("2"), "span 1 1, wrap, grow");
        panel.add(new JButton("3"), "span 1 2, wrap, grow");
        panel.add(new JButton("4"), "span 1 1, wrap, grow");
        show( panel );
    }

    private void show(JComponent comp) {
        JFrame f = new JFrame();
        f.getContentPane().add(comp);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
