package org.woodwardbernsteinprotocol.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class Tip extends MessageNode {

    String message;

    @Override
    public void transmitContent(OutputStream stream) throws IOException{
        stream.write(message.getBytes(),0,message.getBytes().length);
        stream.write('\0');
    }

    @Override
    public void parseContent(InputStream stream) throws IOException{
        int data;
        int read = 0;
        Vector<Byte> content = new Vector<Byte>();
        while((data = stream.read())!= '\0'){
            content.add((byte)data);
        }
        byte[] c = new byte[content.size()];
        for( int i=0;i<content.size();++i){
            c[i] = content.get(i);
        }
        message = new String(c);
    }

    @Override
    public String getContent() {
        return message;
    }

}
