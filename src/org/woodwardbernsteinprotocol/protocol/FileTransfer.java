package org.woodwardbernsteinprotocol.protocol;

import org.woodwardbernsteinprotocol.message.Tip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
    public void parse(Tip tip) throws IOException{
        FileInputStream input = new FileInputStream(dest);
        tip.parse(input);
    }
}
