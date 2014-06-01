package org.woodwardbernsteinprotocol.identity;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class IdentityNameTest extends TestCase {

    public void testTransmit() throws Exception {
        IdentityName id=new IdentityName();
        id.name = "Daniel";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        id.transmit(output);

    }

    public void testParse() throws Exception {
        IdentityName id=new IdentityName();
        id.name = "Daniel";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        id.transmit(output);
        IdentityName n = new IdentityName();
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        n.parse(input);
    }
}