package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.CriticalComplianceException;
import info.wso2.scim2.compliance.feignclient.FeignClientImpl;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.commons.httpclient.methods.GetMethod;

/*
This Class is to test the /ServiceProviderConfig Endpoint.
 */
public class ConfigTest {

    private FeignClientImpl feignClient;
    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;

    public ConfigTest(FeignClientImpl httpClient, ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
            this.feignClient = httpClient;
            this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;
    }

    // Test is to get the service provider configurations from service provider
    public TestResult getConfiguration()
            throws Exception {

        SCIMServiceProviderConfig serviceProviderConfig;

        // Construct the endpoint url
        String url = complianceTestMetaDataHolder.getUrl() +
                complianceTestMetaDataHolder.getVersion() +
                ComplianceConstants.TestConstants.SERVICE_PROVIDER_ENDPOINT;

        GetMethod method = new GetMethod(url);

        try {
            serviceProviderConfig = feignClient.GetServiceProviderConfig(url);
            System.out.println(feignClient.get());
        } catch (Exception e) {
            throw new CriticalComplianceException(new TestResult
                    (TestResult.ERROR, "Read ServiceProviderConfig",
                            "Could not get ServiceProviderConfig at url " + url ,
                            ComplianceUtils.getWire(method, "","",404,"")));
        }
        try {
            return new TestResult
                    (TestResult.SUCCESS, "Read ServiceProviderConfig",
                            "", ComplianceUtils.getWire(method, feignClient.getBody(),
                            feignClient.getHeaders(),feignClient.getStatus(), feignClient.getReason()));

        } catch (Exception e) {
            throw new CriticalComplianceException
                    (new TestResult(TestResult.ERROR, "Parse ServiceProviderConfig",
                    "Could not parse the json format returned from ServiceProviderConfig. " + e.getMessage(),
                            ComplianceUtils.getWire(method, "","",404,"")));
        }
    }

}
