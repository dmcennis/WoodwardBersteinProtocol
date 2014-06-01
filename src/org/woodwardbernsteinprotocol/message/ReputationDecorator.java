package org.woodwardbernsteinprotocol.message;

import org.woodwardbernsteinprotocol.identity.Context;
import org.woodwardbernsteinprotocol.identity.Identity;
import org.woodwardbernsteinprotocol.node.ContextCreation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class ReputationDecorator<Node extends MessageNode> implements MessageInterface {
    Node content;

    byte reputationScore = 0;



    public ReputationDecorator(Node node){
        content = node;
    }

    @Override
    public Vector<MessageInterface> getChildren() {
        return content.getChildren();
    }

    @Override
    public void transmit(OutputStream stream) throws IOException {
        content.getIdentity().transmit(stream);
        transmitContent(stream);
        for(MessageInterface message : content.getChildren()){
            message.transmit(stream);
        }
    }

    public void transmitContent(OutputStream stream) throws IOException {
        content.transmitContent(stream);
        stream.write(reputationScore);
    }

    @Override
    public void parse(InputStream stream) throws IOException, ClassNotFoundException {
        content.getIdentity().parse(stream);
        parseContent(stream);
        for(MessageInterface message: content.getChildren()){
            message.parse(stream);
        }
    }

    public void parseContent(InputStream stream) throws IOException, ClassNotFoundException {
        content.parseContent(stream);
        byte[] data = new byte[1];
        stream.read(data,0,1);
        reputationScore = data[0];
    }

    @Override
    public Identity getIdentity() {
        return content.getIdentity();
    }

    @Override
    public Context getContext(ContextCreation preference) {
        return content.getContext(preference);
    }

    @Override
    public Object getContent() {
        return content.getContent();
    }

    @Override
    public void setContent(Object o) {
        content.setContent(o);
    }

    @Override
    public void addChild(MessageInterface message) {
        content.addChild(message);
    }

    @Override
    public void addAllChildren(Vector<MessageInterface> kids) {
        content.addAllChildren(kids);
    }

    @Override
    public void setIdentity(Identity id) {
        content.setIdentity(id);
    }
}
