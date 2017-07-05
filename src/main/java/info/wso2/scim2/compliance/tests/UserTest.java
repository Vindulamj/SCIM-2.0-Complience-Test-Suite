package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.CriticalComplianceException;
import info.wso2.scim2.compliance.feignclient.FeignClientImpl;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.scimcore.objects.User.User;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;

public class UserTest {

    private FeignClientImpl feignClient;
    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;

    public UserTest(FeignClientImpl httpClient, ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
        this.feignClient = httpClient;
        this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;
    }

    // Test is to getX the service provider configurations from service provider
    public TestResult getConfiguration()
            throws Exception {

        // Construct the endpoint url
        String url = complianceTestMetaDataHolder.getUrl() +
                complianceTestMetaDataHolder.getVersion() +
                ComplianceConstants.TestConstants.USERS_ENDPOINT;

        PostMethod method = new PostMethod(url);

        try {
            //get the ServiceProviderConfig
            //User user = User.getDefinedUser();
            User user = new User();
            user.setUserName("Thilina");
            user.setPassword("asd");
            ArrayList<User.PhoneNumberObj> phoneNumbers = new ArrayList<User.PhoneNumberObj>(){{
                add(new User.PhoneNumberObj("555-555-5555","work"));
                add(new User.PhoneNumberObj("555-555-4444","mobile"));
            }};
            //user.setPhoneNumbers(phoneNumbers);
            feignClient.CreateUser(user, url);


        } catch (Exception e) {
            throw new CriticalComplianceException(new TestResult
                    (TestResult.ERROR, "Read ServiceProviderConfig",
                            "Could not get ServiceProviderConfig at url " + url ,
                            ComplianceUtils.getWire(method, feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
        try {
            return new TestResult
                    (TestResult.SUCCESS, "Read ServiceProviderConfig",
                            "", ComplianceUtils.getWire(method, feignClient.getResponseBody(),
                            feignClient.getResponseHeaders(),feignClient.getResponseStatus(),
                            feignClient.getResponseReason()));

        } catch (Exception e) {
            throw new CriticalComplianceException
                    (new TestResult(TestResult.ERROR, "Parse ServiceProviderConfig",
                            "Could not parse the json format returned from ServiceProviderConfig. " + e.getMessage(),
                            ComplianceUtils.getWire(method, feignClient.getResponseBody(),
                                    feignClient.getResponseHeaders(),feignClient.getResponseStatus(),
                                    feignClient.getResponseReason())));
        }
    }

}
