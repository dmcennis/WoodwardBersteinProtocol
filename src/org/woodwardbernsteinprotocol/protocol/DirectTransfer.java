package org.woodwardbernsteinprotocol.protocol;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.http.params.HttpConnectionParams;
import org.woodwardbernsteinprotocol.message.Tip;

import java.io.*;
import java.net.URL;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class DirectTransfer implements LinkProtocol {

    HostConfiguration config = new HostConfiguration();

    InputStream reader=null;

    public void setInputStream(InputStream stream){
        reader = stream;
    }

    public DirectTransfer(){
        config = null;
    }

    public DirectTransfer(String host, int port){
        config.setHost("127.0.0.1/postTip",9870);
    }

    @Override
    public void transmit(Tip tip) throws IOException {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        Base64OutputStream stream = new Base64OutputStream(data);
        tip.transmit(stream);
        InputStream writer = new ByteArrayInputStream(data.toByteArray());
        HttpConnection client = new HttpConnection(config);
        client.open();
        byte[] buffer = new byte[10240];
        int read = 0;
        byte[] start = new byte[]{'t','i','p','='};
        client.write(start);
        while((read = writer.read(buffer,0,10240))>0){
            client.write(buffer,0,read);
        }
        client.close();
    }

    @Override
    public Tip parse() throws IOException, ClassNotFoundException {
        InputStream data = new Base64InputStream(reader);
        Tip tip = new Tip();
        tip.parse(data);
        return tip;
    }

}
