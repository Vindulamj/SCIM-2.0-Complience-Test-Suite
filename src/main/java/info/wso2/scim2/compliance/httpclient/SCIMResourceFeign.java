package info.wso2.scim2.compliance.httpclient;

import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface SCIMResourceFeign {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    SCIMServiceProviderConfig getAllBooks();
}
