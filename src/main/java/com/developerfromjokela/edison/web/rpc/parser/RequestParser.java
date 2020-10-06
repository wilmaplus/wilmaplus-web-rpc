package com.developerfromjokela.edison.web.rpc.parser;

import com.developerfromjokela.edison.web.rpc.requests.Request;
import com.developerfromjokela.edison.web.rpc.responses.ErrorResponse;
import com.developerfromjokela.edison.web.rpc.server.CommunicationServer;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

public class RequestParser {

    public static boolean checkAuthentication(String message) {
        try {
            JSONObject object = new JSONObject(message);
            if (object.has("authentication")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void parseResponse(CommunicationServer communicationServer, WebSocket socket, String message) {
        Request intialRequest = null;
        try {
            intialRequest = new Gson().fromJson(message, Request.class);
        } catch (Exception e) {
            socket.send(new ErrorResponse(false, 400, "Invalid JSON!").toString());
            return;
        }
        if (intialRequest.getAction() == null) {
            socket.send(new ErrorResponse(false, 400, "You must include the action!").toString());
            return;
        }
        // TODO requests
        if (true) {

        } else {
            socket.send(new ErrorResponse(false, 404, "Invalid action!").toString());
        }
    }
}
