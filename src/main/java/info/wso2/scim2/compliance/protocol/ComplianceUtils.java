package info.wso2.scim2.compliance.protocol;

import org.apache.commons.lang.exception.ExceptionUtils;
import info.wso2.scim2.compliance.entities.Wire;
import org.apache.http.client.methods.HttpRequestBase;

public class ComplianceUtils {

    public static Wire getWire(HttpRequestBase method, String responseBody,
                               String headerString, String responseStatus) {

        StringBuffer toServer = new StringBuffer();
        StringBuffer fromServer = new StringBuffer();

        toServer.append(method.getRequestLine().getMethod()).append(" ");
        toServer.append(method.getRequestLine().getUri()+"\n");

        toServer.append(method.getRequestLine().getProtocolVersion().getProtocol());
        for (org.apache.http.Header header : method.getAllHeaders()) {
            toServer.append(header.getName()).append(": ").append(header.getValue()).append("\n");
        }
        fromServer.append("\n" + "Headers : ");
        fromServer.append(headerString + "\n");
        fromServer.append("\n" + "Status : ");
        fromServer.append(responseStatus + "\n");
        fromServer.append("\n" + responseBody);

        return new Wire(toServer.toString(), fromServer.toString());
    }


    public static Wire getWire(Throwable e) {
        return new Wire(ExceptionUtils.getFullStackTrace(e), "");
    }
}
