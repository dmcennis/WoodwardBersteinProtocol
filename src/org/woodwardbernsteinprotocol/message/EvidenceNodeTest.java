package org.woodwardbernsteinprotocol.message;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import junit.framework.TestCase;
import org.woodwardbernsteinprotocol.identity.IdentityName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

public class EvidenceNodeTest extends TestCase {

    public void testTransmitContent() throws Exception {
        EvidenceNode node = new EvidenceNode();
        EvidenceObject URLMessage = new EvidenceObject();
        URLMessage.object = new URL("http://www.yahoo.com");
        URLMessage.description = "My website evidence";
        node.setContent(URLMessage);
        node.setIdentity(new IdentityName("Daniel"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        node.transmit(output);
    }

    public void testParseContent() throws Exception {
        EvidenceNode node = new EvidenceNode();
        EvidenceObject object = new EvidenceObject();
        object.object = new URL("http://www.yahoo.com");
        object.description = "My website evidence";
        node.setContent(object);
        node.setIdentity(new IdentityName("Daniel"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        node.transmit(output);
        ByteArrayInputStream input  = new ByteArrayInputStream(output.toByteArray());
        EvidenceNode in = (EvidenceNode)MessageFactory.parse(input);
        EvidenceObject e = in.getContent();
        assertEquals(object.object,e.object);
        assertEquals(object.description,e.description);
        assertEquals(node.getIdentity(),in.getIdentity());
    }

    public void testGetContent() throws Exception {
        EvidenceNode node = new EvidenceNode();
        EvidenceObject object = new EvidenceObject();
        object.object = new URL("http://www.yahoo.com");
        object.description = "My website evidence";
        node.setContent(object);
        node.setIdentity(new IdentityName("Daniel"));
    }

    public void testSetContent() throws Exception {
        EvidenceNode node = new EvidenceNode();
        EvidenceObject old = new EvidenceObject();
        old.object = "";
        old.description = "";
        node.setContent(old);
        EvidenceObject object = new EvidenceObject();
        object.object = new URL("http://www.yahoo.com");
        object.description = "My website evidence";
        node.setIdentity(new IdentityName("Daniel"));
        node.setContent(object);
        assertEquals(object.object,node.getContent().object);
        assertEquals(object.description,node.getContent().description);
    }
}