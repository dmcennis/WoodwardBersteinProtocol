package org.woodwardbernsteinprotocol.message;

import org.apache.avro.Schema;
import org.woodwardbernsteinprotocol.identity.Context;
import org.woodwardbernsteinprotocol.identity.Identity;
import org.woodwardbernsteinprotocol.identity.IdentityName;
import org.woodwardbernsteinprotocol.node.ContextCreation;

import java.io.*;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 */
public abstract class MessageNode implements MessageInterface, Serializable {

    Types type;

    Vector<MessageInterface> children = new Vector<MessageInterface>();
    MessageInterface parent;
    IdentityName id = new IdentityName("Anonymous");

    @Override
    public Vector<MessageInterface> getChildren() {
        return children;
    }

    @Override
    public void transmit(OutputStream stream) throws IOException {
        (new ObjectOutputStream(stream)).writeObject(type);
        id.transmit(stream);
        transmitContent(stream);
        stream.write(children.size());
        for(MessageInterface message : children){
            message.transmit(stream);
        }
    }

    public abstract void transmitContent(OutputStream stream) throws IOException;

    @Override
    public void parse(InputStream stream) throws IOException, ClassNotFoundException {
        id = new IdentityName();
        id.parse(stream);
        parseContent(stream);
        int count = stream.read();
        for(int i=0;i<count;++i){
            children.add(MessageFactory.parse(stream));
        }
    }

    public abstract void parseContent(InputStream stream) throws IOException, ClassNotFoundException;

    @Override
    public Identity getIdentity() {
        return id;
    }

    @Override
    public void addChild(MessageInterface message) {
        children.add(message);
    }

    @Override
    public void addAllChildren(Vector<MessageInterface> kids) {
        children.addAll(kids);
    }

    @Override
    public Types getType() {
        return type;
    }

    @Override
    public void setIdentity(Identity id) {
        this.id = (IdentityName)id;
    }

    @Override
    public Context getContext(ContextCreation preference) {
        return preference.analyzeMessage(this);
    }
}
