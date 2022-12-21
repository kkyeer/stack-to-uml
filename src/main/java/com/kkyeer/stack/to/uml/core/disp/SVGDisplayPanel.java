package com.kkyeer.stack.to.uml.core.disp;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 21:21 2022/12/18
 * @Modified By:
 */
public class SVGDisplayPanel {
    private File imgFile;

    private JSVGCanvas canvas;

    private Container parent;



    public SVGDisplayPanel(File imgFile) {
        this.imgFile = imgFile;
    }

    public JComponent createPanel(Container parent){
        this.parent = parent;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setSize(1000,500);
        JSVGCanvas jsvgCanvas = new JSVGCanvas();
        jsvgCanvas.setSize(200,200);

        jsvgCanvas.setURI(imgFile.toURI().toString());
        this.canvas = jsvgCanvas;
        // jsvgCanvas.getInteractors().clear();
        // jsvgCanvas.getInteractors().add(new ZoomInInteraction());
        panel.add(jsvgCanvas, BorderLayout.CENTER);



        JMenuBar menuBar = new JMenuBar();
        configureMenuBar(menuBar);
        panel.add(menuBar, BorderLayout.NORTH);
        parent.add(panel);
        return panel;
    }

    private void configureMenuBar(JMenuBar menuBar) {
        JButton saveBtn = new JButton();
        saveBtn.setText("Save");
        saveBtn.setVisible(true);
        saveBtn.addActionListener(
                e -> {
                    configureFileChooser(parent);
                }
        );
        menuBar.add(saveBtn);

        JButton resetZoomBtn = new JButton();
        resetZoomBtn.setText("ResetZoom");
        resetZoomBtn.setVisible(true);
        AtomicReference<JSVGCanvas> canvasRef = new AtomicReference<>(this.canvas);
        resetZoomBtn.addActionListener(
                e ->{
                    AffineTransform initialTransform = canvasRef.get().getInitialTransform();
                    // paintingTransform.setToScale(1.0, 1.0);
                    canvasRef.get().setPaintingTransform(initialTransform);
                }
        );
        menuBar.add(resetZoomBtn);
    }


    private void configureFileChooser(Component parent) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setVisible(true);
        jFileChooser.setCurrentDirectory(new File("./"));
        int code = jFileChooser.showSaveDialog(parent);
        if (JFileChooser.APPROVE_OPTION == code) {
            File selectedFile = jFileChooser.getSelectedFile();
            try {
                FileUtils.copyFile(imgFile, selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
