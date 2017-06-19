package info.wso2.scim2.compliance.httpclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface SCIMResourceFeign {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    SPC getAllBooks();
}
