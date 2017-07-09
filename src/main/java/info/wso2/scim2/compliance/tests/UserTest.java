package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.GeneralComplianceException;
import info.wso2.scim2.compliance.feignclient.FeignClientImpl;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.scimcore.objects.User.User;
import info.wso2.scim2.compliance.scimcore.objects.common.ErrorResponse;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;
import java.util.Stack;

public class UserTest{

    private FeignClientImpl feignClient;
    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;
    private String url;
    private Stack<User> users = new Stack<>();


    public UserTest(FeignClientImpl httpClient, ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
        this.feignClient = httpClient;
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
        //reset the decoder
        feignClient.getCustomDecoder().decoderReset();
        try {
            testResults.add(GetUserTest());
        } catch (GeneralComplianceException e) {
            testResults.add(e.getResult());
        }
        return testResults;
    }

    private TestResult CreateUserTest () throws  GeneralComplianceException {
        //TODO : This need to replaced with feign
        PostMethod method = new PostMethod(url);

        try {
            User definedUser = User.getDefinedUser();
            //create user test
            users.push(feignClient.CreateUser(definedUser, url));
            // ResponseValidateTest responseValidateTest = new ResponseValidateTest(returnedUser);
            //responseValidateTest.runValidateTests();
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
            users.push(feignClient.GetUser(users.pop().getId(), url));

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
}
