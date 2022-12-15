package com.kkyeer.stack.to.uml.core.helper;

import com.kkyeer.stack.to.uml.core.model.InvokeChain;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 22:57 2022/12/12
 * @Modified By:
 */
public class InvocationToImage {
    private static final ImageType imageType = ImageType.PNG;
    /**
     * @param invokeChain  invokeChain
     * @throws IOException if error occurs while write to outputStream
     * @return
     */
    public static File generateRandomFile(InvokeChain invokeChain,ImageType imageType) throws IOException {
        String plantUMLText = InvocationChainToPlantUmlHelper.generatePlantUmlText(invokeChain);
        SourceStringReader reader = new SourceStringReader(plantUMLText);
        File imgFile = new File(UUID.randomUUID()+"."+imageType.getSuffix());
        FileOutputStream fos = new FileOutputStream(imgFile);
        // Write the first image to "os"
        DiagramDescription desc = reader.outputImage(fos, new FileFormatOption(imageType.getFileFormat()));
        fos.close();
        return imgFile;
    }
}
