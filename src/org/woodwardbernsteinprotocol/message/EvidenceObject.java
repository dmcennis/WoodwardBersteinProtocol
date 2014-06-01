package org.woodwardbernsteinprotocol.message;

import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class EvidenceObject {

    public Serializable object=null;

    public String description="";

    public EvidenceObject(){}

    public EvidenceObject(Serializable o, String d){
        object = o;
        description = d;
    }

    public void write(OutputStream stream) throws IOException {
        ObjectOutputStream s = new ObjectOutputStream(stream);
        s.writeObject(object);
        s.writeObject(description);
    }

    static public EvidenceObject read(InputStream input) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(input);
        EvidenceObject ret = new EvidenceObject();
        ret.object = (Serializable)in.readObject();
        ret.description = (String)in.readObject();
        return ret;
    }
}
