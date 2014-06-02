package org.woodwardbernsteinprotocol.message;

import junit.framework.TestCase;
import org.woodwardbernsteinprotocol.identity.IdentityName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

/**
 * Created by dmcennis.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class ReputationDecoratorTest extends TestCase {

    public void testGetChildren() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        tip.addChild(new Tip("Child",new IdentityName("Anonymous")));
        tip.addChild(new Tip("Child2",new IdentityName("Anonymous")));
        assertEquals(2,tip.getChildren().size());
        assertEquals("Child",tip.getChildren().get(0).getContent());
        assertEquals("Child2",tip.getChildren().get(1).getContent());
    }

    public void testTransmit() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        tip.addChild(new Tip("Child",new IdentityName("Anonymous")));
        tip.addChild(new Tip("Child2",new IdentityName("Anonymous")));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
    }

    public void testTransmitContent() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
    }

    public void testParse() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        tip.addChild(new Tip("Child",new IdentityName("Anonymous")));
        tip.addChild(new Tip("Child2",new IdentityName("Anonymous")));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        ReputationDecorator<Tip> in = (ReputationDecorator<Tip>)MessageFactory.parse(input);
        assertEquals("Message",in.getContent());
        assertEquals(new IdentityName("Daniel"),in.getIdentity());
        assertEquals(2,in.getChildren().size());
        assertEquals("Child",in.getChildren().get(0).getContent());
        assertEquals(new IdentityName("Anonymous"),in.getChildren().get(0).getIdentity());
        assertEquals("Child2",in.getChildren().get(1).getContent());
        assertEquals(new IdentityName("Anonymous"), in.getChildren().get(1).getIdentity());
    }

    public void testParseContent() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        tip.reputationScore = 0.0f;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        tip.transmit(output);
        assertTrue(output.size() > 0);
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        ReputationDecorator<Tip> in = (ReputationDecorator<Tip>)MessageFactory.parse(input);
        assertEquals("Message",in.getContent());
        assertEquals(new IdentityName("Daniel"), in.getIdentity());
    }

    public void testGetIdentity() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        assertEquals(new IdentityName("Daniel"),tip.getIdentity());
    }

    public void testGetContext() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));

    }

    public void testGetContent() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        assertEquals("Message",tip.getContent());
    }

    public void testSetContent() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        tip.setContent("Message 2");
        assertEquals("Message 2",tip.getContent());
    }

    public void testAddChild() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        tip.addChild(new Tip("Child",new IdentityName("Anonymous")));
        assertEquals("Child",tip.getChildren().get(0).getContent());
        assertEquals(new IdentityName("Anonymous"), tip.getChildren().get(0).getIdentity());

    }

    public void testAddAllChildren() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        Vector<MessageInterface> vector = new Vector<MessageInterface>();
        vector.add(new Tip("Child",new IdentityName("Anonymous")));
        vector.add(new Tip("Child2",new IdentityName("Anonymous")));
        tip.addAllChildren(vector);
        assertEquals("Child",tip.getChildren().get(0).getContent());
        assertEquals(new IdentityName("Anonymous"),tip.getChildren().get(0).getIdentity());
        assertEquals("Child2", tip.getChildren().get(1).getContent());
        assertEquals(new IdentityName("Anonymous"), tip.getChildren().get(1).getIdentity());

    }

    public void testSetIdentity() throws Exception {
        ReputationDecorator<Tip> tip = new ReputationDecorator<Tip>(new Tip("Message",new IdentityName("Daniel")));
        tip.setIdentity(new IdentityName("Other Daniel"));
        assertEquals(new IdentityName("Other Daniel"),tip.getIdentity());
    }
}