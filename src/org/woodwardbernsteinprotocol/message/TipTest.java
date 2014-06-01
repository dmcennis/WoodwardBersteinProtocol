package org.woodwardbernsteinprotocol.message;

import junit.framework.TestCase;
import org.woodwardbernsteinprotocol.identity.IdentityName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

public class TipTest extends TestCase {

    public void testTransmitContentSimple() throws Exception {
        Tip tip = new Tip();
        tip.setContent("A tip");
        tip.setIdentity(new IdentityName("Daniel"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
    }

    public void testParseContentSimple() throws Exception {
        Tip tip = new Tip();
        tip.setContent("A tip");
        IdentityName name = new IdentityName("Daniel");
        tip.setIdentity(name);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        Tip in = new Tip();
        in.parse(input);
        assertEquals(in.getContent(),"A tip");
        assertEquals(tip.getIdentity(),name);
    }

    public void testTransmitContentComplex() throws Exception {
        Tip tip = new Tip();
        tip.setContent("A tip");
        Tip child = new Tip();
        child.setContent("Child");
        child.setIdentity(new IdentityName("WhiteTigerPup"));
        tip.setIdentity(new IdentityName("Daniel"));
        tip.addChild(child);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
    }

    public void testParseContentComplex() throws Exception {
        Tip tip = new Tip();
        tip.setContent("A tip");
        IdentityName name = new IdentityName("Daniel");
        tip.setIdentity(name);
        Tip child = new Tip();
        child.setContent("Child");
        IdentityName childName = new IdentityName("WhiteTigerPup");
        child.setIdentity(childName);
        tip.addChild(child);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        Tip in = new Tip();
        in.parse(input);
        assertEquals("A tip",in.getContent());
        assertEquals(name,tip.getIdentity());
        assertEquals(1,tip.getChildren().size());
        MessageInterface m = tip.getChildren().get(0);
        assertTrue(m instanceof Tip);
        Tip t = (Tip)m;
        assertEquals("Child",t.getContent());
        assertEquals(childName,t.getIdentity());
    }

    public void testGetContent() throws Exception {
        Tip tip = new Tip();
        tip.setContent("message");
        tip.setIdentity(new IdentityName("Daniel"));
        assertEquals("message",tip.getContent());
    }

    public void testGetChildren() throws Exception {
        Tip tip = new Tip("message",new IdentityName("Daniel"));
        tip.addChild(new Tip("Child1",new IdentityName("ChildA")));
        tip.addChild(new Tip("Child2",new IdentityName("ChildB")));
        assertEquals(2,tip.getChildren().size());
        assertEquals("Child1",tip.getChildren().get(0).getContent());
        assertEquals("Child2",tip.getChildren().get(1).getContent());
    }

    public void testGetIdentity() throws Exception {
        Tip tip = new Tip("Message",new IdentityName("Daniel"));
        assertEquals(new IdentityName("Daniel"),tip.getIdentity());
    }

    public void testGetContext() throws Exception {
        Tip tip = new Tip("Message",new IdentityName("Daniel"));
        assertEquals("Message",tip.getContent());
    }

    public void testSetID() throws Exception {
        Tip tip = new Tip("Message",new IdentityName("Other"));
        tip.setIdentity(new IdentityName("Daniel"));
        assertEquals(new IdentityName("Daniel"),tip.getIdentity());
    }

    public void testAddChild() throws Exception {
        Tip tip = new Tip("Message",new IdentityName("Daniel"));
        Tip child = new Tip("Next Message", new IdentityName("McEnnis"));
        tip.addChild(child);
        assertEquals(1,tip.getChildren().size());
        assertEquals(child.getContent(),tip.getChildren().get(0).getContent());
        assertEquals(child.getIdentity(),tip.getChildren().get(0).getIdentity());
    }

    public void testAddAllChildren() throws Exception{
        Tip tip = new Tip("Message",new IdentityName("Daniel"));
        Vector<MessageInterface> vector  = new Vector<MessageInterface>();
        vector.add(new Tip("Message1",new IdentityName("Child1")));
        vector.add(new Tip("Message2",new IdentityName("Child2")));
        tip.addAllChildren(vector);
        assertEquals(2,tip.getChildren().size());
        assertEquals("Message1",tip.getChildren().get(0).getContent());
        assertEquals("Message2",tip.getChildren().get(1).getContent());

    }
}