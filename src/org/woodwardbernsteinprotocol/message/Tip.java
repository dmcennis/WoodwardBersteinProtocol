package org.woodwardbernsteinprotocol.message;

import org.woodwardbernsteinprotocol.identity.Identity;
import org.woodwardbernsteinprotocol.identity.IdentityName;

import java.io.*;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class Tip extends MessageNode {

    String message;

    public Tip(){
        type = Types.TIP;
    }

    public Tip(String message, IdentityName id){
        super();
        this.message = message;
        this.id = id;
        type = Types.TIP;
    }

    public Tip(String message,IdentityName id, Vector<MessageInterface> children){
        super();
        this.message = message;
        this.id = id;
        this.children.addAll(children);
    }


    @Override
    public void transmitContent(OutputStream stream) throws IOException{
        (new ObjectOutputStream(stream)).writeObject(message);
    }

    public static Tip staticParseContent(InputStream stream) throws IOException, ClassNotFoundException{
        String message = (String)(new ObjectInputStream(stream)).readObject();
        return new Tip(new String(message),new IdentityName("Anonymous"));
    }

    @Override
    public void parseContent(InputStream stream) throws IOException, ClassNotFoundException {
        message = (String)(new ObjectInputStream(stream)).readObject();
    }

    @Override
    public String getContent() {
        return message;
    }

    @Override
    public void setContent(Object o) {
        message = (String)o;
    }
}
