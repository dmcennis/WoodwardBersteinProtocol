package org.woodwardbernsteinprotocol.protocol;

import org.woodwardbernsteinprotocol.message.Tip;

import java.io.*;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class FileTransfer implements LinkProtocol {

    File dest = null;

    FileTransfer(File filename){
        dest = filename;
    }
    @Override
    public void transmit(Tip tip) throws IOException {
        FileOutputStream output = new FileOutputStream(dest);
        tip.transmit(output);
    }

    @Override
    public Tip parse(InputStream input) throws IOException{
        Tip tip = new Tip();
        tip.parse(input);
        return tip;
    }
}
