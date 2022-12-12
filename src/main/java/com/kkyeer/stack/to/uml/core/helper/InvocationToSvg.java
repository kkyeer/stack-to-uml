package com.kkyeer.stack.to.uml.core.helper;

import com.kkyeer.stack.to.uml.core.model.InvokeChain;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 22:57 2022/12/12
 * @Modified By:
 */
public class InvocationToSvg {
    /**
     *
     * @param invokeChain invokeChain
     * @param outputStream stream to which svg will write
     * @throws IOException if error occurs while write to outputStream
     */
    public static void printInvocationUmlToOutputStream(InvokeChain invokeChain, OutputStream outputStream) throws IOException {
        String plantUMLText = InvocationChainToPlantUmlHelper.generatePlantUmlText(invokeChain);
        SourceStringReader reader = new SourceStringReader(plantUMLText);
        File svgFile = new File("abc.svg");
        FileOutputStream fos = new FileOutputStream(svgFile);
        // Write the first image to "os"
        DiagramDescription desc = reader.outputImage(fos, new FileFormatOption(FileFormat.SVG));
        fos.close();
        System.out.println(desc.getDescription());
    }
}
