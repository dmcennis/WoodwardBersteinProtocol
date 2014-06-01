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
        stream.write(description.getBytes(),0,description.getBytes().length);
    }

    static public EvidenceObject read(InputStream input) throws IOException{
        ObjectInputStream in = new ObjectInputStream(input);
        EvidenceObject ret = new EvidenceObject();
        try {
            ret.object = (Serializable)in.readObject();
        }catch(ClassNotFoundException c){
            Logger.getLogger(EvidenceObject.class.getName()).log(Level.SEVERE,"Object type not found.");
        }
        int data;
        int read = 0;
        Vector<Byte> content = new Vector<Byte>();
        while((data = input.read())!= '\0'){
            content.add((byte)data);
        }
        byte[] c = new byte[content.size()];
        for( int i=0;i<content.size();++i){
            c[i] = content.get(i);
        }
        ret.description = new String(c);
        return ret;
    }
}
