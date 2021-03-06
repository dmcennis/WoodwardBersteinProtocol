package org.woodwardbernsteinprotocol.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class EvidenceNode extends MessageNode {

    EvidenceObject object;

    public EvidenceNode(){type = Types.EVIDENCE;}

    @Override
    public void transmitContent(OutputStream stream) throws IOException {
        object.write(stream);
    }

    @Override
    public void parseContent(InputStream stream) throws IOException, ClassNotFoundException{
        object = EvidenceObject.read(stream);
    }

    @Override
    public EvidenceObject getContent() {
        return object;
    }

    @Override
    public void setContent(Object evidence){
        object = (EvidenceObject)evidence;
    }

}
