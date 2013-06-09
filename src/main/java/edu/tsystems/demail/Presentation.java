package edu.tsystems.demail;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 06.06.13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
class Presentation
{
    private JButton button1;
    private JPanel panel1;
    private JLabel label1;

    public Presentation() {
        button1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                   //System.out.print("goood");//To change body of overridden methods use File | Settings | File Templates.
                label1.setText("derr");
            }
        });
        button1.addComponentListener(new ComponentAdapter() {
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                label1.setText("fdjkh");
            }
        });
    }


    public void start () {
        createUIComponents();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        JFrame frame = new JFrame("DeMail");
        frame.setContentPane(new Presentation().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
