package com.kkyeer.stack.to.uml.core;

import com.kkyeer.stack.to.uml.core.helper.InvocationChainToPlantUmlHelper;
import com.kkyeer.stack.to.uml.core.model.Invocation;
import com.kkyeer.stack.to.uml.core.model.InvokeChain;
import com.kkyeer.stack.to.uml.core.helper.InvocationToSvg;
import java.io.File;
import java.io.FileOutputStream;
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
    public static void main(String[] args) throws IOException {
        // String plantUMLText = "@startuml\n" +
        //         "Alice -> Bob: Authentication Request\n" +
        //         "Bob --> Alice: Authentication Response\n" +
        //         "\n" +
        //         "Alice -> Bob: Another authentication Request\n" +
        //         "Alice <-- Bob: Another authentication Response\n" +
        //         "@enduml";
        InvokeChain invokeChain = genInvokeChain();
        File svgFile = new File("abc.svg");
        FileOutputStream fos = new FileOutputStream(svgFile);
        InvocationToSvg.printInvocationUmlToOutputStream(invokeChain, fos);
        fos.close();

        // The XML is stored into svg
        System.out.println(svgFile.getAbsolutePath());
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
