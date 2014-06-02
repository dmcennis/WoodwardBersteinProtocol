package org.woodwardbernsteinprotocol.message;

import java.io.*;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class AnalysisNode extends MessageNode {

    String analysisContent;

    // References (EvidenceNode list) - parsing issue

    public AnalysisNode(){type = Types.ANALYSIS;}

    @Override
    public void transmitContent(OutputStream stream) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(stream);
        output.writeObject(analysisContent);
    }

    @Override
    public void parseContent(InputStream stream) throws IOException, ClassNotFoundException{
        analysisContent = (String)(new ObjectInputStream(stream)).readObject();
    }

    @Override
    public String getContent() {
        return analysisContent;
    }

    @Override
    public void setContent(Object content){
        this.analysisContent = (String)content;
    }
}
