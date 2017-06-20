package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.CritialComplienceException;
import info.wso2.scim2.compliance.httpclient.HttpClientImpl;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;
import org.apache.commons.httpclient.methods.GetMethod;

public class ConfigTest {

    private HttpClientImpl httpClient;
    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;

    public ConfigTest(HttpClientImpl httpClient, ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
            this.httpClient = httpClient;
            this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;
    }

    public TestResult getConfiguration()
            throws Exception {

        SCIMServiceProviderConfig serviceProviderConfig;
        String url = complianceTestMetaDataHolder.getUrl() +
                complianceTestMetaDataHolder.getVersion() + "/ServiceProviderConfig";

        GetMethod method = new GetMethod(url);
        // Execute the method.
        try {
            serviceProviderConfig = httpClient.GetServiceProviderConfig();

        } catch (Exception e) {
            throw new CritialComplienceException
                    (new TestResult(TestResult.ERROR, "Read ServiceProviderConfig",
                    "Could not get ServiceProviderConfig at url " + url ,
                            ComplianceUtils.getWire(method, "")));
        }

        try {

            return new TestResult
                    (TestResult.SUCCESS, "Read ServiceProviderConfig",
                            "", ComplianceUtils.getWire(method, serviceProviderConfig.toString()));

        } catch (Exception e) {
            throw new CritialComplienceException
                    (new TestResult(TestResult.ERROR, "Parse ServiceProviderConfig",
                    "Could not parse the json format returned from ServiceProviderConfig. " + e.getMessage(),
                            ComplianceUtils.getWire(
                            method, "")));
        }
    }

}
