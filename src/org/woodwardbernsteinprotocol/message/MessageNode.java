package org.woodwardbernsteinprotocol.message;

import org.woodwardbernsteinprotocol.identity.Context;
import org.woodwardbernsteinprotocol.identity.Identity;
import org.woodwardbernsteinprotocol.node.ContextCreation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 */
public abstract class MessageNode implements MessageInterface, Serializable {

    Vector<MessageInterface> children = new Vector<MessageInterface>();
    MessageInterface parent;
    Identity id;

    @Override
    public Vector<MessageInterface> getChildren() {
        return children;
    }

    @Override
    public void transmit(OutputStream stream) throws IOException {
        id.transmit(stream);
        transmitContent(stream);
        for(MessageInterface message : children){
            message.transmit(stream);
        }
    }

    public abstract void transmitContent(OutputStream stream);

    @Override
    public void parse(InputStream stream) throws IOException {
        id.parse(stream);
        parseContent(stream);
        for(MessageInterface message: children){
            message.parse(stream);
        }
    }

    public abstract void parseContent(InputStream stream);

    @Override
    public Identity getIdentity() {
        return id;
    }

    @Override
    public Context getContext(ContextCreation preference) {
        return preference.analyzeMessage(this);
    }
}
