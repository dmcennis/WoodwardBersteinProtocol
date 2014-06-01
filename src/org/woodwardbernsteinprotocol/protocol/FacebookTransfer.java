package org.woodwardbernsteinprotocol.protocol;

import org.woodwardbernsteinprotocol.message.Tip;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dmcennis on 6/1/2014.
 */
public class FacebookTransfer implements LinkProtocol {
    @Override
    public void transmit(Tip tip) throws IOException {

    }

    @Override
    public Tip parse() throws IOException, ClassNotFoundException {
        return null;
    }
}
