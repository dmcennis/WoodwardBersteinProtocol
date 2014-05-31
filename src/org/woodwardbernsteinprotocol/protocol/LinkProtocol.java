package org.woodwardbernsteinprotocol.protocol;

import org.woodwardbernsteinprotocol.message.Tip;

import java.io.IOException;

/**
 * Created by dmcennis on 5/31/2014.
 */
public interface LinkProtocol {

    public void transmit(Tip tip) throws IOException;

    public void parse(Tip tip) throws IOException;
}
