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

    public void setContent(Object o);

    public Object getContent();

    public Vector<MessageInterface> getChildren();

    public void addChild(MessageInterface message);

    public void addAllChildren(Vector<MessageInterface> kids);

    public void transmit(OutputStream stream) throws IOException;

    public void parse(InputStream stream) throws IOException, ClassNotFoundException;

    public Identity getIdentity();

    public void setIdentity(Identity id);

    public Context getContext(ContextCreation preference);

    public Types getType();

    public static enum Types {TIP,TIP_REP,ANALYSIS,ANALYSIS_REP,EVIDENCE,EVIDENCE_REP};
}
