package com.kkyeer.stack.to.uml.core;

import com.kkyeer.stack.to.uml.core.disp.SVGDisplayPanel;
import com.kkyeer.stack.to.uml.core.disp.SwingNativeDisplayPanel;
import com.kkyeer.stack.to.uml.core.helper.ImageType;
import com.kkyeer.stack.to.uml.core.model.Invocation;
import com.kkyeer.stack.to.uml.core.model.InvokeChain;
import com.kkyeer.stack.to.uml.core.helper.InvocationToImage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 下午4:25 2022/12/11
 * @Modified By:
 */
public class POC {
    public static void main(String[] args) throws IOException, InterruptedException {
        // String plantUMLText = "@startuml\n" +
        //         "Alice -> Bob: Authentication Request\n" +
        //         "Bob --> Alice: Authentication Response\n" +
        //         "\n" +
        //         "Alice -> Bob: Another authentication Request\n" +
        //         "Alice <-- Bob: Another authentication Response\n" +
        //         "@enduml";
        InvokeChain invokeChain = genInvokeChain();
        File imgFile = InvocationToImage.generateRandomFile(invokeChain, ImageType.SVG);
        JFrame jFrame = new JFrame();
        SVGDisplayPanel umlDisplay = new SwingNativeDisplayPanel(imgFile, jFrame.getContentPane());
        jFrame.setSize(1000,1000);
        JComponent panel = umlDisplay.createPanel();
        jFrame.getContentPane().add(panel);
        jFrame.setVisible(true);
        Thread.sleep(1000000);
        // The XML is stored into svg
        System.out.println(imgFile.getAbsolutePath());
    }

    private static InvokeChain genInvokeChain(){
        InvokeChain invokeChain = new InvokeChain();
        List<Invocation> invocationList = new ArrayList<>();
        Invocation invocation = Invocation.Builder.builder()
                .invokerName("Spawner")
                .invokeDesc("sp()")
                .invokedName("ooo")
                .build();
        Invocation invocation2 =  Invocation.Builder.builder()
                .invokerName("Spawner")
                .invokeDesc("selfCall")
                .invokedName("Spawner")
                .followInvocationList(Collections.singletonList(invocation))
                .build();
        invocationList.add(invocation2);
        invokeChain.setInvokeList(invocationList);
        return invokeChain;
    }
}
