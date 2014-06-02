package org.woodwardbernsteinprotocol.protocol;

import org.woodwardbernsteinprotocol.message.MessageFactory;
import org.woodwardbernsteinprotocol.message.Tip;

import java.io.*;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
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
        output.close();
    }

    @Override
    public Tip parse() throws IOException, ClassNotFoundException{
        Tip tip = (Tip)MessageFactory.parse(new FileInputStream(dest));
        return tip;
    }
}
