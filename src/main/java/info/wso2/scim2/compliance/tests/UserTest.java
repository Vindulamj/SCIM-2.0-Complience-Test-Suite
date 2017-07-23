package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.CriticalComplianceException;
import info.wso2.scim2.compliance.exception.GeneralComplianceException;
import info.wso2.scim2.compliance.httpclient.HTTPClient;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.scimcore.objects.User.User;
import info.wso2.scim2.compliance.scimcore.objects.common.ErrorResponse;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;

import java.util.ArrayList;

public class UserTest{

    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;
    private String url;


    public UserTest(ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {

        this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;

        url = complianceTestMetaDataHolder.getUrl() +
                complianceTestMetaDataHolder.getVersion() +
                ComplianceConstants.TestConstants.USERS_ENDPOINT;
    }

    public ArrayList<TestResult> performTest()  {
        ArrayList<TestResult> testResults = new ArrayList<>();
        try {
            testResults.add(CreateUserTest());
        } catch (GeneralComplianceException e) {
            testResults.add(e.getResult());
        }
        return testResults;
    }

    private TestResult CreateUserTest () throws  GeneralComplianceException {

        HttpPost method = new HttpPost(url);

        User definedUser = User.getDefinedUser();
        //create user test
        HttpClient client = HTTPClient.getHttpClientWithBasicAuth();

        method = (HttpPost) HTTPClient.setAuthorizationHeader(complianceTestMetaDataHolder, method);
        method.setHeader("Accept", "application/json");
        method.setHeader("Content-Type", "application/json");

        HttpResponse response = null;
        String responseString = null;
        String headerString = "";
        String responseStatus = null;
        try {
            //create the user
            response = client.execute(method);
            // Read the response body.
            responseString = new BasicResponseHandler().handleResponse(response);
            //get all headers
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                headerString += header.getName() + " : " + header.getValue() + "\n";
            }
            responseStatus = response.getStatusLine().getStatusCode() + " "
                    + response.getStatusLine().getReasonPhrase();

        } catch (Exception e) {
            throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create User",
                    "Could not create default user at url " + url,
                    ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
        }
        return null;
    }
        /*
    } catch (Exception e) {
        //check if the service provider has sent an error message
        if (e.getCause() instanceof ErrorResponse){
            throw new GeneralComplianceException(new TestResult
                    (TestResult.ERROR, "Create User",
                            "Error in creating the user at " + url ,
                            ComplianceUtils.getWire(method,
                                    ((ErrorResponse) e.getCause()).getDetails(),
                                    ((ErrorResponse) e.getCause()).getHeader(),
                                    ((ErrorResponse) e.getCause()).getStatus(),
                                    ((ErrorResponse) e.getCause()).getReason())));
        }
        throw new GeneralComplianceException(new TestResult
                (TestResult.ERROR, "Create User",
                        "Could not create the user at " + url ,
                        ComplianceUtils.getWire(method,
                                    feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),
                                    feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
        try {
            return new TestResult
                    (TestResult.SUCCESS, "Create User",
                            "", ComplianceUtils.getWire(method,
                            feignClient.getResponseBody(),
                            feignClient.getResponseHeaders(),
                            feignClient.getResponseStatus(),
                            feignClient.getResponseReason()));

        } catch (Exception e) {
            throw new GeneralComplianceException
                    (new TestResult(TestResult.ERROR, "Create User",
                            "Could not parse the json format " +
                                    "returned from create user response. " + e.getMessage(),
                            ComplianceUtils.getWire(method,
                                    feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),
                                    feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
    }

    private TestResult GetUserTest () throws  GeneralComplianceException {

        //TODO : This need to replaced with feign
        GetMethod method = new GetMethod(url);

        try {
            //users.push(feignClient.GetUser(users.pop().getId(), url));

        } catch (Exception e) {
            //check if the service provider has sent an error message
            if (e.getCause() instanceof ErrorResponse){
                throw new GeneralComplianceException(new TestResult
                        (TestResult.ERROR, "Get User",
                                "Error in retrieving the user " + url ,
                                ComplianceUtils.getWire(method,
                                        ((ErrorResponse) e.getCause()).getDetails(),
                                        ((ErrorResponse) e.getCause()).getHeader(),
                                        ((ErrorResponse) e.getCause()).getStatus(),
                                        ((ErrorResponse) e.getCause()).getReason())));
            }
            throw new GeneralComplianceException(new TestResult
                    (TestResult.ERROR, "Get User",
                            "Error in retrieving the user " + url ,
                            ComplianceUtils.getWire(method,
                                    feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),
                                    feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
        try {
            return new TestResult
                    (TestResult.SUCCESS, "Get User",
                            "", ComplianceUtils.getWire(method,
                            feignClient.getResponseBody(),
                            feignClient.getResponseHeaders(),
                            feignClient.getResponseStatus(),
                            feignClient.getResponseReason()));

        } catch (Exception e) {
            throw new GeneralComplianceException
                    (new TestResult(TestResult.ERROR, "Get User",
                            "Could not parse the json format " +
                                    "returned from get user response. " + e.getMessage(),
                            ComplianceUtils.getWire(method,
                                    feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),
                                    feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
    }
    */
}

