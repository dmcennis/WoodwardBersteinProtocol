package org.woodwardbernsteinprotocol.message;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class EvidenceNode extends MessageNode {

    Object evidenceObject;

    String evidenceDescription;

    @Override
    public void transmitContent(OutputStream stream) {

    }

    @Override
    public void parseContent(InputStream stream) {

    }

    @Override
    public Object getContent() {
        return null;
    }

}
