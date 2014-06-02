package org.woodwardbernsteinprotocol;

import org.apache.commons.codec.binary.Base64InputStream;

import java.io.InputStream;

/**
 * Created by dmcennis on 5/31/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class Utilities {
    public InputStream base64(InputStream input){
        return new Base64InputStream(input);
    }

}
