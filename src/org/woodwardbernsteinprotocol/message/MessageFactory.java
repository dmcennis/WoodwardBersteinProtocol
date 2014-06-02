package org.woodwardbernsteinprotocol.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by dmcennis on 6/1/2014.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class MessageFactory {
    public static MessageInterface parse(InputStream input) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(input);
        MessageInterface.Types type = (MessageInterface.Types)in.readObject();
        MessageInterface ret = null;
        switch(type){
            case TIP:
                ret = new Tip();
                ret.parse(input);
                break;
            case ANALYSIS:
                ret = new AnalysisNode();
                ret.parse(input);
                break;
            case EVIDENCE:
                ret = new EvidenceNode();
                ret.parse(input);
                break;
            case TIP_REP:
                ret = new ReputationDecorator<Tip>(new Tip());
                ret.parse(input);
                break;
            case ANALYSIS_REP:
                ret = new ReputationDecorator<AnalysisNode>(new AnalysisNode());
                ret.parse(input);
                break;
            case EVIDENCE_REP:
                ret = new ReputationDecorator<EvidenceNode>(new EvidenceNode());
                ret.parse(input);
                break;
            default:
                throw new ClassNotFoundException();
        }
        return ret;
    }
}
