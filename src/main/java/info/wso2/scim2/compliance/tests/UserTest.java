package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.GeneralComplianceException;
import info.wso2.scim2.compliance.feignclient.FeignClientImpl;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.scimcore.objects.User.User;
import info.wso2.scim2.compliance.scimcore.objects.common.ErrorResponse;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;

public class UserTest implements Test {

    private FeignClientImpl feignClient;
    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;

    public UserTest(FeignClientImpl httpClient, ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
        this.feignClient = httpClient;
        this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;
    }

    public TestResult performTest() throws GeneralComplianceException {

        // Construct the user endpoint url
        String url = complianceTestMetaDataHolder.getUrl() +
                complianceTestMetaDataHolder.getVersion() +
                ComplianceConstants.TestConstants.USERS_ENDPOINT;

        //TODO : This need to replaced with feign
        PostMethod method = new PostMethod(url);

        try {
            User user = User.getDefinedUser();
            //create user test
            feignClient.CreateUser(user, url);

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
                            ComplianceUtils.getWire(method, feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
        try {
            return new TestResult
                    (TestResult.SUCCESS, "Create User",
                            "", ComplianceUtils.getWire(method, feignClient.getResponseBody(),
                            feignClient.getResponseHeaders(),feignClient.getResponseStatus(),
                            feignClient.getResponseReason()));

        } catch (Exception e) {
            throw new GeneralComplianceException
                    (new TestResult(TestResult.ERROR, "Create User",
                            "Could not parse the json format returned from create user response. " + e.getMessage(),
                            ComplianceUtils.getWire(method, feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
    }
}
