package swingy.dot.com.view;

import swingy.dot.com.controller.SwingyAppController;

import javax.swing.*;

public class SwingyFrame extends JFrame {

    private SwingyPanel basePanel;

    public SwingyFrame(SwingyAppController baseController) {
        basePanel = new SwingyPanel(baseController);
        setUpFrame();
    }

    private void setUpFrame(){
        this.setContentPane(basePanel);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("swingyRPG@azulu");
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
