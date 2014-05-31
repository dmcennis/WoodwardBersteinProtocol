package org.woodwardbernsteinprotocol.node;

import org.woodwardbernsteinprotocol.identity.Context;
import org.woodwardbernsteinprotocol.message.MessageNode;

/**
 * Created by dmcennis on 5/31/2014.
 */
public interface ContextCreation {
    public Context analyzeMessage(MessageNode node);
}
