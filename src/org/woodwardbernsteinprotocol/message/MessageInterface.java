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
public interface MessageInterface extends Serializable{
    public Object getContent();

    public Vector<MessageInterface> getChildren();

    public void transmit(OutputStream stream) throws IOException;

    public void parse(InputStream stream) throws IOException;

    public Identity getIdentity();

    public Context getContext(ContextCreation preference);

}
