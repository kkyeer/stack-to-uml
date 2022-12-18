package com.kkyeer.stack.to.uml.core.disp;

import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 21:21 2022/12/18
 * @Modified By:
 */
public class SVGDisplayPanel {
    private File imgFile;

    public SVGDisplayPanel(File imgFile) {
        this.imgFile = imgFile;
    }

    public JComponent createPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setSize(20,20);

        // JLabel imgLabel = new JLabel(new ImageIcon("path_to_image.png"));
        // panel.add(imgLabel, -1);
        // JTextArea jTextArea = new JTextArea();
        // // jTextArea.setSize(30,30);
        // panel.add(jTextArea,BorderLayout.CENTER);
        JLabel imgLabel = new JLabel(new ImageIcon(this.imgFile.getAbsolutePath()));
        imgLabel.setSize(20,20);
        panel.add(imgLabel,BorderLayout.CENTER);

        JSVGCanvas jsvgCanvas = new JSVGCanvas();
        jsvgCanvas.setURI(imgFile.toURI().toString());
        panel.add(jsvgCanvas, BorderLayout.CENTER);
        return panel;
    }

    public void show(){
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(300,300);
        jFrame.add(createPanel(),BorderLayout.CENTER);
        jFrame.setVisible(true);
    }
}
