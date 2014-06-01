package org.woodwardbernsteinprotocol.message.Visitors;

import org.woodwardbernsteinprotocol.message.*;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class PrettyPrint {
    public String prettyPrint(Tip tip){
        String data="";
        data += "Tip: "+tip.getContent() + "\n";
        data += printChildren(tip);
        return data;
    }

    public String prettyPrint(EvidenceNode e){
        String data="";
        data += "Evidence: "+e.getContent() + "\n";
        data += printChildren(e);
        return data;
    }

    public String prettyPrint(AnalysisNode e){
        String data="";
        data += "Analysis: "+e.getContent() + "\n";
        data += printChildren(e);
        return data;
    }

    public String printChildren(MessageInterface m){
        String data = "";
        for(MessageInterface message: m.getChildren()){
            if(message instanceof AnalysisNode){
                data += prettyPrint((AnalysisNode)message);
            }else if(message instanceof EvidenceNode){
                data += prettyPrint((EvidenceNode)message);
            }
        }
        return data;
    }
}
