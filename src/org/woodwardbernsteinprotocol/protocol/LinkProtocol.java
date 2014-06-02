package org.woodwardbernsteinprotocol.protocol;

import org.woodwardbernsteinprotocol.message.Tip;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public interface LinkProtocol {

    public void transmit(Tip tip) throws IOException;

    public Tip parse() throws IOException, ClassNotFoundException;
}
