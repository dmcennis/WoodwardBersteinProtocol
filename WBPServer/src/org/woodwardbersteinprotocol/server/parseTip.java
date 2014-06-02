package org.woodwardbersteinprotocol.server;

import org.woodwardbernsteinprotocol.message.Tip;
import org.woodwardbernsteinprotocol.protocol.DirectTransfer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmcennis.
 * Licensed under the Apache 2.0 license.  See license.txt
 */
public class parseTip extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DirectTransfer transfer = new DirectTransfer();
        transfer.setInputStream(request.getPart("tip").getInputStream());
        try{
            Tip tip = transfer.parse();
        }catch (ClassNotFoundException e){}

        // send tip to archive...

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
