package info.wso2.scim2.compliance.scimcore.objects.common;


import java.util.Collection;
import java.util.Map;

public class ErrorResponse extends Exception {

    private int status;
    private String details;
    private String header;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ErrorResponse(int status, String reason, String details, String headers) {
        this.status = status;
        this.details = details;
        this.header = header;
        this.reason = reason;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }
}
