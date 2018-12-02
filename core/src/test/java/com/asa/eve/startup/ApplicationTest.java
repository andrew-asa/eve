package com.asa.eve.startup;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

/**
 * @author andrew_asa
 * @date 2018/11/25.
 */
public class ApplicationTest {

    public static void main(String[]args) throws Exception {

        JFrame frame = new JFrame();
        JPanel panel ;
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setBackground(Color.black);
        frame.setResizable(false);
        frame.setTitle("test");
        frame.setType(java.awt.Window.Type.UTILITY);

        panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JPanel inpanel = new JPanel();
        inpanel.setLayout(new GridBagLayout());
        inpanel.setOpaque(false);
        JTextPane input = new JTextPane();
        int inputWidth = (int) (600 * 0.9);
        int inputHeight = (int) (700 * 0.8);
        input.setPreferredSize(new Dimension((inputWidth), inputHeight));
        inpanel.setBackground(Color.decode("#333333"));
        input.setBackground(Color.decode("#616161"));
        input.setForeground(Color.decode( "#ffffff"));
        input.setCaretColor(Color.decode("#ffffff"));
        inpanel.setPreferredSize(new Dimension(600, 700));
        inpanel.add(input);
        panel.add(inpanel);

        frame.getContentPane().add(panel);
        //frame.setBounds(0,0,600,700);
        frame.setVisible(true);
        frame.pack();
    }

}