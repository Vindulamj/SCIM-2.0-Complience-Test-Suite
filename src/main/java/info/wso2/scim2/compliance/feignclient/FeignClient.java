package info.wso2.scim2.compliance.feignclient;

import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;
import info.wso2.scim2.compliance.scimcore.objects.User.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface FeignClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    SCIMServiceProviderConfig GetServiceProviderConfig();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    User CreateUser(final User user);

}
