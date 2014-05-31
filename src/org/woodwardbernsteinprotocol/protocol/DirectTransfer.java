package org.woodwardbernsteinprotocol.protocol;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnection;
import org.woodwardbernsteinprotocol.message.Tip;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.URL;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class DirectTransfer implements LinkProtocol {

    HostConfiguration config = new HostConfiguration();

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
        while((read = writer.read(buffer,0,10240))>0){
            client.write(buffer,0,read);
        }
        client.close();
    }

    @Override
    public Tip parse(InputStream input) throws IOException {
        InputStream data = new Base64InputStream(input);
        Tip tip = new Tip();
        tip.parse(data);
        return tip;
    }

}
