package org.woodwardbernsteinprotocol.identity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public interface Identity extends Serializable {

    public void parse(InputStream stream) throws IOException, ClassNotFoundException;

    public void transmit(OutputStream stream) throws IOException;

}
