package info.wso2.scim2.compliance;

import info.simplecloud.scimproxy.compliance.enteties.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Compliance extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Result runTests(@FormParam("url") String url,
                           @FormParam("username") String username,
                           @FormParam("password") String password,
                           @FormParam("clientId") String clientId,
                           @FormParam("clientSecret") String clientSecret,
                           @FormParam("authorizationServer") String authorizationServer,
                           @FormParam("authorizationHeader") String authorizationHeader,
                           @FormParam("authMethod") String authMethod) throws InterruptedException, ServletException {

        return null;
    }
}
