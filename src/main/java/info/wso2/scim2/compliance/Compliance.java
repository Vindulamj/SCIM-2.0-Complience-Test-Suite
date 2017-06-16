package info.wso2.scim2.compliance;

import info.wso2.scim2.compliance.entities.Result;
import info.wso2.scim2.compliance.entities.Statistics;
import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.ComplianceException;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.commons.validator.routines.UrlValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/test")
public class Compliance extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Result runTests(@FormParam(ComplianceConstants.RequestCodeConstants.URL) String url,
                           @FormParam(ComplianceConstants.RequestCodeConstants.USERNAME) String username,
                           @FormParam(ComplianceConstants.RequestCodeConstants.PASSWORD) String password,
                           @FormParam(ComplianceConstants.RequestCodeConstants.CLIENT_ID) String clientId,
                           @FormParam(ComplianceConstants.RequestCodeConstants.CLIENT_SECRET)
                                       String clientSecret,
                           @FormParam(ComplianceConstants.RequestCodeConstants.AUTHORIZATION_SERVER)
                                       String authorizationServer,
                           @FormParam(ComplianceConstants.RequestCodeConstants.AUTHORIZATION_HEADER)
                                       String authorizationHeader,
                           @FormParam(ComplianceConstants.RequestCodeConstants.AUTHORIZATION_METHOD)
                                       String authMethod)
            throws InterruptedException, ServletException {

        // TODO: remove when done coding!
        if (url == null || url.isEmpty()) {
            url = "http://127.0.0.1:9443";
        }

        ArrayList<TestResult> results = new ArrayList<TestResult>();

        String[] schemes = { ComplianceConstants.RequestCodeConstants.HTTP,
                ComplianceConstants.RequestCodeConstants.HTTPS };

        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(url)) {

            ComplianceException BadRequestException = new ComplianceException();
            BadRequestException.setDetail("Invalid Service Provider URL.");
            BadRequestException.setStatus(ComplianceConstants.ResponseCodeConstants.CODE_BAD_REQUEST);
            results.add(new TestResult(TestResult.ERROR, BadRequestException));

            Statistics statistics = new Statistics();
            statistics.incFailed();

            return new Result(statistics, results);
        }

        return null;
    }
}
