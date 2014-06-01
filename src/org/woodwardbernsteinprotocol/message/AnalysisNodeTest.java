package org.woodwardbernsteinprotocol.message;

import junit.framework.TestCase;
import org.woodwardbernsteinprotocol.identity.IdentityName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class AnalysisNodeTest extends TestCase {

    public void testTransmitContent() throws Exception {
        AnalysisNode node = new AnalysisNode();
        node.setIdentity(new IdentityName("Daniel"));
        node.setContent("My analysis");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        node.transmit(output);
    }

    public void testParseContent() throws Exception {
        AnalysisNode node = new AnalysisNode();
        node.setIdentity(new IdentityName("Daniel"));
        node.setContent("My analysis");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        node.transmit(output);
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        AnalysisNode in = new AnalysisNode();
        in.parse(input);
    }

    public void testGetContent() throws Exception {

    }

    public void testSetContent(String getContent){

    }
}