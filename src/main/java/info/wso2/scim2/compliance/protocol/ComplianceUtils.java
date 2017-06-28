package info.wso2.scim2.compliance.protocol;

import org.apache.commons.httpclient.*;
import org.apache.commons.lang.exception.ExceptionUtils;
import info.wso2.scim2.compliance.entities.Wire;

import java.io.IOException;

public class ComplianceUtils {

    /**
     * Gets the raw request, headers and body that's sent to the server.
     * TODO: Replace this with the output from HttpClients wire logs!
     * @param method The http method to read data from.
     * @param body The request body that was sent.
     * @return
     */
    public static Wire getWire(HttpMethodBase method, String body) {
        StringBuffer toServer = new StringBuffer();
        StringBuffer fromServer = new StringBuffer();

        toServer.append(method.getName()).append(" ");
        toServer.append(method.getPath());
        if (method.getQueryString() != null) {
            toServer.append("?").append(method.getQueryString());
        }
        toServer.append(" HTTP/1.1\n");
        for (Header header : method.getRequestHeaders()) {
            toServer.append(header.getName()).append(": ").append(header.getValue()).append("\n");
        }


        fromServer.append("\n" + body);

        return new Wire(toServer.toString(), fromServer.toString());
    }


    public static Wire getWire(Throwable e) {
        return new Wire(ExceptionUtils.getFullStackTrace(e), "");
    }
}
