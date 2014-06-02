package org.woodwardbernsteinprotocol.message;

import org.woodwardbernsteinprotocol.identity.Context;
import org.woodwardbernsteinprotocol.identity.Identity;
import org.woodwardbernsteinprotocol.node.ContextCreation;

import java.io.*;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class ReputationDecorator<Node extends MessageNode> implements MessageInterface {
    Node content;

    float reputationScore = 0.0f;



    public ReputationDecorator(Node node){
        content = node;
        switch(node.type){
            case TIP:
                content.type = Types.TIP_REP;
                break;
            case ANALYSIS:
                content.type = Types.ANALYSIS_REP;
                break;
            case EVIDENCE:
                content.type = Types.EVIDENCE_REP;
                break;
        }
    }

    @Override
    public Vector<MessageInterface> getChildren() {
        return content.getChildren();
    }

    @Override
    public Types getType() {
        return content.getType();
    }

    @Override
    public void transmit(OutputStream stream) throws IOException {

        (new ObjectOutputStream(stream)).writeObject(content.getType());
        (new ObjectOutputStream(stream)).writeObject(new Float(reputationScore));
        content.transmit(stream);
    }

    @Override
    public void parse(InputStream stream) throws IOException, ClassNotFoundException {
        reputationScore = ((Float)(new ObjectInputStream(stream)).readObject()).floatValue();
        (new ObjectInputStream(stream)).readObject();
        content.parse(stream);
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
