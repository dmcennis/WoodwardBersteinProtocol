package org.woodwardbernsteinprotocol.message;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

/**
 * Created by dmcennis.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class EvidenceObjectTest extends TestCase {

    public void testWrite() throws Exception {
        EvidenceObject URLMessage = new EvidenceObject();
        URLMessage.object = new URL("http://www.yahoo.com");
        URLMessage.description = "My website evidence";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URLMessage.write(output);
    }

    public void testRead() throws Exception {
        EvidenceObject URLMessage = new EvidenceObject();
        URLMessage.object = new URL("http://www.yahoo.com");
        URLMessage.description = "My website evidence";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URLMessage.write(output);
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        EvidenceObject o = EvidenceObject.read(input);
        assertEquals(URLMessage.object,o.object);
        assertEquals(URLMessage.description,o.description);

    }
}