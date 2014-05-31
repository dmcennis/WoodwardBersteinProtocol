package org.woodwardbernsteinprotocol.protocol;

import org.woodwardbernsteinprotocol.message.Tip;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dmcennis on 5/31/2014.
 */
public interface LinkProtocol {

    public void transmit(Tip tip) throws IOException;

    public Tip parse(InputStream input) throws IOException;
}
