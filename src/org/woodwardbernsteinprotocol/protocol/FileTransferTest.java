package org.woodwardbernsteinprotocol.protocol;

import junit.framework.TestCase;
import org.woodwardbernsteinprotocol.identity.IdentityName;
import org.woodwardbernsteinprotocol.message.EvidenceNode;
import org.woodwardbernsteinprotocol.message.EvidenceObject;
import org.woodwardbernsteinprotocol.message.Tip;

import java.io.File;
import java.net.URL;

public class FileTransferTest extends TestCase {

    public void testTransmit() throws Exception {
        FileTransfer file = new FileTransfer(new File("C:\\Users\\dmcennis\\temp\\test.wbp"));
        Tip tip = new Tip("Message", new IdentityName("Daniel"));
        EvidenceNode evidence = new EvidenceNode();
        evidence.setIdentity(new IdentityName("WhiteTigerPup"));
        EvidenceObject object = new EvidenceObject(new URL("http://www.yahoo.com"),"Evidence URL");
        evidence.setContent(object);
        tip.addChild(evidence);
        file.transmit(tip);
    }

    public void testParse() throws Exception {
        FileTransfer file = new FileTransfer(new File("C:\\Users\\dmcennis\\temp\\test.wbp"));
        Tip tip = new Tip("Message", new IdentityName("Daniel"));
        EvidenceNode evidence = new EvidenceNode();
        evidence.setIdentity(new IdentityName("WhiteTigerPup"));
        EvidenceObject object = new EvidenceObject(new URL("http://www.yahoo.com"),"Evidence URL");
        evidence.setContent(object);
        tip.addChild(evidence);
        file.transmit(tip);

        Tip in = file.parse();
        assertEquals("Message",in.getContent());
        assertEquals(new IdentityName("Daniel"),in.getIdentity());
        assertEquals(1,in.getChildren().size());
        assertEquals(new IdentityName("WhiteTigerPup"),in.getChildren().get(0).getIdentity());
        EvidenceObject o = (EvidenceObject)in.getChildren().get(0).getContent();
        assertEquals(new URL("http://www.yahoo.com"),o.object);
        assertEquals("Evidence URL",o.description);
    }
}