package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.ComplianceException;
import info.wso2.scim2.compliance.exception.GeneralComplianceException;
import info.wso2.scim2.compliance.httpclient.HTTPClient;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.tests.common.ResponseValidateTest;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;
    private String url;
    private User user = null;


    public UserTest(ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {

        this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;

        url = complianceTestMetaDataHolder.getUrl() +
                complianceTestMetaDataHolder.getVersion() +
                ComplianceConstants.TestConstants.USERS_ENDPOINT;
    }

    public ArrayList<TestResult> performTest() throws ComplianceException {
        ArrayList<TestResult> testResults = new ArrayList<>();
        try {
            //perform create user test
            testResults.add(CreateUserTest());
            //perform get user test
            testResults.add(GetUserTest());
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
        ArrayList<String> subTests =  new ArrayList<>();
        try {
            //create the user
            HttpEntity entity = new ByteArrayEntity
                    (ComplianceConstants.DefinedInstances.DEFINED_USER.getBytes("UTF-8"));
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

        } catch (Exception e) {
            // Read the response body.
            //get all headers
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                headerString += header.getName() + " : " + header.getValue() + "\n";
            }
            responseStatus = response.getStatusLine().getStatusCode() + " "
                    + response.getStatusLine().getReasonPhrase();
            throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create User",
                    "Could not create default user at url " + url,
                    ComplianceUtils.getWire(method, responseString, headerString, responseStatus, subTests)));
        }

        if (response.getStatusLine().getStatusCode() == 201) {
            //obtain the schema corresponding to user
            // unless configured returns core-user schema or else returns extended user schema
            SCIMResourceTypeSchema schema = SCIMResourceSchemaManager.getInstance().getUserResourceSchema();

            JSONDecoder jsonDecoder = new JSONDecoder();
            try {
                user = (User)jsonDecoder.decodeResource(responseString, schema, new User());
            } catch (BadRequestException | CharonException | InternalErrorException e) {
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create User",
                        "Could not decode the server response",
                        ComplianceUtils.getWire(method, responseString, headerString, responseStatus, subTests)));
            }
            try {
                ResponseValidateTest.runValidateTests(user, schema, method,
                        responseString, headerString, responseStatus, subTests);

            } catch (BadRequestException | CharonException e) {
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create User",
                        "Response Validation Error",
                        ComplianceUtils.getWire(method, responseString, headerString, responseStatus, subTests)));
            }
            return new TestResult
                    (TestResult.SUCCESS, "Create User",
                            "", ComplianceUtils.getWire(method, responseString,
                            headerString, responseStatus, subTests));
        } else {
            return new TestResult
                    (TestResult.ERROR, "Create User",
                            "", ComplianceUtils.getWire(method, responseString,
                            headerString, responseStatus, subTests));
        }
    }

    private TestResult GetUserTest () throws GeneralComplianceException, ComplianceException {

        String getUserURL = null;
        try {
            getUserURL = url + "/" + user.getId();
        } catch (CharonException e) {
            throw new ComplianceException(e.getStatus(),"Error in reading the id of the created user.");
        }
        HttpGet method = new HttpGet(getUserURL);
        //create user test
        HttpClient client = HTTPClient.getHttpClientWithBasicAuth();

        method = (HttpGet) HTTPClient.setAuthorizationHeader(complianceTestMetaDataHolder, method);
        method.setHeader("Accept", "application/json");

        HttpResponse response = null;
        String responseString = "";
        String headerString = "";
        String responseStatus = "";
        ArrayList<String> subTests =  new ArrayList<>();
        try {
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
            // Read the response body.
            //get all headers
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                headerString += header.getName() + " : " + header.getValue() + "\n";
            }
            responseStatus = response.getStatusLine().getStatusCode() + " "
                    + response.getStatusLine().getReasonPhrase();
            throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Get User",
                    "Could not get the default user from url " + url,
                    ComplianceUtils.getWire(method, responseString, headerString, responseStatus, subTests)));
        }

        if (response.getStatusLine().getStatusCode() == 200) {
            //obtain the schema corresponding to user
            // unless configured returns core-user schema or else returns extended user schema)
            SCIMResourceTypeSchema schema = SCIMResourceSchemaManager.getInstance().getUserResourceSchema();

            JSONDecoder jsonDecoder = new JSONDecoder();
            try {
                user = (User)jsonDecoder.decodeResource(responseString, schema, new User());

            } catch (BadRequestException | CharonException | InternalErrorException e) {
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Get User",
                        "Could not decode the server response",
                        ComplianceUtils.getWire(method, responseString, headerString, responseStatus, subTests)));
            }
            try {
                ResponseValidateTest.runValidateTests(user, schema, method,
                        responseString, headerString, responseStatus, subTests);

            } catch (BadRequestException | CharonException e) {
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Get User",
                        "Response Validation Error",
                        ComplianceUtils.getWire(method, responseString, headerString, responseStatus, subTests)));
            }
            return new TestResult
                    (TestResult.SUCCESS, "Get User",
                            "", ComplianceUtils.getWire(method, responseString, headerString,
                            responseStatus, subTests));
        } else {
            return new TestResult
                    (TestResult.ERROR, "Get User",
                            "", ComplianceUtils.getWire(method, responseString, headerString,
                            responseStatus, subTests));
        }
    }
}

