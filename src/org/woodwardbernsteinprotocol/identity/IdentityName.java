package org.woodwardbernsteinprotocol.identity;

import java.io.*;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class IdentityName implements Identity {
    String name;

    @Override
    public void transmit(OutputStream stream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(stream);
        writer.write(name);
        writer.write('\0');
    }

    @Override
    public void parse(InputStream stream) throws IOException {
        InputStreamReader reader = new InputStreamReader(stream);
        StringBuffer buffer = new StringBuffer();
        char[] i = new char[1];
        while(reader.read(i,0,1) > 0){
            if(i[0] != '\0'){
                buffer.append(i[0]);
            }else{
                break;
            }
        }
    }
}
