import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.io.BufferedWriter;
import java.io.IOException;

public class GUI implements ActionListener {
    protected   JFrame          fr;
    private     JPanel          p;
    private     JTextField      tf;
    private     JButton         b;
    private     int             lines   = 20;
    private     JLabel[]        l       = new JLabel[lines];
    private     String          title   = "";
    private     BufferedWriter  out;

    public GUI(String name, BufferedWriter bw) {
        title = name;
        out = bw;

        fr = new JFrame(title);
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        fr.setSize(400, 475);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
        fr.setVisible(true);

        Insets in = fr.getInsets();
        int width = fr.getWidth() - in.left - in.right;
        int height = fr.getHeight() - in.top - in.bottom;

        p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        fr.add(p, BorderLayout.CENTER);

        tf = new JTextField();
        tf.setHorizontalAlignment(tf.LEFT);
        tf.addActionListener(this);
        tf.setBounds(0, 400, 300, height-400);
        p.add(tf);

        b = new JButton("Send");
        b.setBounds(300, 400, width-300, height-400);
        b.addActionListener(this);
        p.add(b);

        p.validate();
        fr.validate();

        p.repaint();
        fr.repaint();

        for(int i=0;i<lines;i++) {
            l[i] = new JLabel("");
            l[i].setHorizontalAlignment(l[i].LEFT);
            l[i].setBounds(0, 400*i/lines, 500, 400/lines);
            p.add(l[i]);
        }
    }

    public void showString(String text) {
        if (text.equals("")) return;
        for(int i=0;i<lines-1;i++) {
            l[i].setText(l[i+1].getText());
        }
        l[lines-1].setText(text);
    }

    public void actionPerformed(ActionEvent ae) {
        String text = tf.getText();
        try {
            showString(text);
            out.write(text);
            out.write('\n');
            out.flush();
            tf.setText("");
        } catch (IOException ioe) {

        }
    }
}