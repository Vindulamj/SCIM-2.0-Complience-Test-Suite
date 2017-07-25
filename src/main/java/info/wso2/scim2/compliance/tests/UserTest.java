package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.GeneralComplianceException;
import info.wso2.scim2.compliance.httpclient.HTTPClient;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.scimcore.objects.User.SCIMUser;
import info.wso2.scim2.compliance.tests.common.ResponseValidateTest;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.wso2.charon3.core.encoder.JSONDecoder;
import org.wso2.charon3.core.exceptions.BadRequestException;
import org.wso2.charon3.core.exceptions.CharonException;
import org.wso2.charon3.core.exceptions.InternalErrorException;
import org.wso2.charon3.core.objects.User;
import org.wso2.charon3.core.schema.SCIMResourceSchemaManager;
import org.wso2.charon3.core.schema.SCIMResourceTypeSchema;

import java.util.ArrayList;

public class UserTest{

    private boolean CreateTestPerformed = false;
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
        //create user test
        HttpClient client = HTTPClient.getHttpClientWithBasicAuth();

        method = (HttpPost) HTTPClient.setAuthorizationHeader(complianceTestMetaDataHolder, method);
        method.setHeader("Accept", "application/json");
        method.setHeader("Content-Type", "application/json");

        HttpResponse response = null;
        String responseString = "";
        String headerString = "";
        String responseStatus = "";
        try {
            //create the user
            HttpEntity entity = new ByteArrayEntity(SCIMUser.getDefinedUser().getBytes("UTF-8"));
            method.setEntity(entity);
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
            CreateTestPerformed = true;

        } catch (Exception e) {
            // Read the response body.
            //get all headers
            if (!CreateTestPerformed) {
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    headerString += header.getName() + " : " + header.getValue() + "\n";
                }
                responseStatus = response.getStatusLine().getStatusCode() + " "
                        + response.getStatusLine().getReasonPhrase();
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create SCIMUser",
                        "Could not create default user at url " + url,
                        ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
            }
        }

        if (response.getStatusLine().getStatusCode() == 201) {
            //obtain the schema corresponding to user
            // unless configured returns core-user schema or else returns extended user schema)
            SCIMResourceTypeSchema schema = SCIMResourceSchemaManager.getInstance().getUserResourceSchema();

            JSONDecoder jsonDecoder = new JSONDecoder();
            User user = null;
            try {
                user = (User)jsonDecoder.decodeResource(responseString, schema, new User());
            } catch (BadRequestException | CharonException | InternalErrorException e) {
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create SCIMUser",
                        "Could not decode the server response",
                        ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
            }
            try {
                ResponseValidateTest.runValidateTests(user, schema, method,
                        responseString, headerString, responseStatus);

            } catch (BadRequestException | CharonException e) {
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create SCIMUser",
                        "Response Validation Error",
                        ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
            }
            return new TestResult
                    (TestResult.SUCCESS, "Create SCIMUser",
                            "", ComplianceUtils.getWire(method, responseString, headerString, responseStatus));
        } else {
            return new TestResult
                    (TestResult.ERROR, "Create SCIMUser",
                            "", ComplianceUtils.getWire(method, responseString, headerString, responseStatus));
        }
    }


   /*

    private TestResult GetUserTest () throws  GeneralComplianceException {

        //TODO : This need to replaced with feign
        GetMethod method = new GetMethod(url);

        try {
            //users.push(feignClient.GetUser(users.pop().getId(), url));

        } catch (Exception e) {
            //check if the service provider has sent an error message
            if (e.getCause() instanceof ErrorResponse){
                throw new GeneralComplianceException(new TestResult
                        (TestResult.ERROR, "Get SCIMUser",
                                "Error in retrieving the user " + url ,
                                ComplianceUtils.getWire(method,
                                        ((ErrorResponse) e.getCause()).getDetails(),
                                        ((ErrorResponse) e.getCause()).getHeader(),
                                        ((ErrorResponse) e.getCause()).getStatus(),
                                        ((ErrorResponse) e.getCause()).getReason())));
            }
            throw new GeneralComplianceException(new TestResult
                    (TestResult.ERROR, "Get SCIMUser",
                            "Error in retrieving the user " + url ,
                            ComplianceUtils.getWire(method,
                                    feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),
                                    feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
        try {
            return new TestResult
                    (TestResult.SUCCESS, "Get SCIMUser",
                            "", ComplianceUtils.getWire(method,
                            feignClient.getResponseBody(),
                            feignClient.getResponseHeaders(),
                            feignClient.getResponseStatus(),
                            feignClient.getResponseReason()));

        } catch (Exception e) {
            throw new GeneralComplianceException
                    (new TestResult(TestResult.ERROR, "Get SCIMUser",
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

