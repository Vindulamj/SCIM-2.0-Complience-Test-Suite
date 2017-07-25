package info.wso2.scim2.compliance.tests;

import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.CriticalComplianceException;
import info.wso2.scim2.compliance.httpclient.HTTPClient;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.objects.SCIMServiceProviderConfig;
import info.wso2.scim2.compliance.utils.ComplianceConstants;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/*
This Class is to test the /ServiceProviderConfig Endpoint.
 */
public class ConfigTest {

    private boolean GetConfigTestPerformed = false;
    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;

    public ConfigTest(ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
            this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;
    }

    // Test is to get the service provider configurations from service provider
    public TestResult performTest() throws CriticalComplianceException {
        return getServiceProviderConfigTest();
    }

    private TestResult getServiceProviderConfigTest () throws CriticalComplianceException {
        // Construct the endpoint url
        String url = complianceTestMetaDataHolder.getUrl() +
                complianceTestMetaDataHolder.getVersion() +
                ComplianceConstants.TestConstants.SERVICE_PROVIDER_ENDPOINT;

        // specify the get request
        HttpGet method = new HttpGet(url);

        HttpClient client = HTTPClient.getHttpClientWithBasicAuth();

        method = (HttpGet) HTTPClient.setAuthorizationHeader(complianceTestMetaDataHolder, method);
        method.setHeader("Accept", "application/json");
        method.setHeader("Content-Type", "application/json");

        HttpResponse response = null;
        String responseString = "";
        String headerString = "";
        String responseStatus = "";
        ArrayList<String> subTests =  new ArrayList<>();
        try {
            //get the Objects
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
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    headerString += header.getName() + " : " + header.getValue() + "\n";
                }
                responseStatus = response.getStatusLine().getStatusCode() + " "
                        + response.getStatusLine().getReasonPhrase();

                throw new CriticalComplianceException(new TestResult
                        (TestResult.ERROR, "Read ServiceProviderConfig",
                                "Could not get ServiceProviderConfig at url " + url,
                                ComplianceUtils.getWire(method, responseString,
                                        headerString, responseStatus, subTests)));
        }
        try {
            SCIMServiceProviderConfig scimServiceProviderConfig = new SCIMServiceProviderConfig();
            JSONObject jsonObj = new JSONObject(responseString);
            JSONObject tmp;

            tmp = jsonObj.optJSONObject("patch");
            scimServiceProviderConfig.setPatch(tmp == null ? false : tmp.getBoolean("supported"));

            tmp = jsonObj.optJSONObject("bulk");
            scimServiceProviderConfig.setBulk(tmp == null ? false : tmp.optBoolean("supported"),
                    tmp == null ? -1 : tmp.optInt("maxOperations"),
                    tmp == null ? -1 : tmp.optInt("maxPayloadSize"));

            tmp = jsonObj.optJSONObject("filter");
            scimServiceProviderConfig.setFilter(tmp == null ? false : tmp.optBoolean("supported"),
                    tmp == null ? -1 : tmp.optInt("maxResults"));

            tmp = jsonObj.optJSONObject("changePassword");
            scimServiceProviderConfig.setChangePassword(tmp == null ? false : tmp.optBoolean("supported"));

            tmp = jsonObj.optJSONObject("sort");
            scimServiceProviderConfig.setSort(tmp == null ? false : tmp.getBoolean("supported"));

            tmp = jsonObj.optJSONObject("etag");
            scimServiceProviderConfig.setEtag(tmp == null ? false : tmp.getBoolean("supported"));

            String documentationUri = jsonObj.optString("documentationUri");
            scimServiceProviderConfig.setDocumentationUri(tmp == null ? "" : documentationUri);

            JSONArray authArray = jsonObj.getJSONArray("authenticationSchemes");
            for (int i = 0; i < authArray.length(); i++) {
                tmp = authArray.getJSONObject(i);
                String name = tmp.optString("name");
                String description = tmp.optString("description");
                String type = tmp.optString("type");
                String specURI = tmp.optString("specURI");
                boolean primary = false;
                if(!jsonObj.isNull("primary")) {
                    primary = tmp.optBoolean("primary");
                }
                scimServiceProviderConfig.setAuthenticationSchemes(name, description, specURI, type, primary);
            }

            return new TestResult
                    (TestResult.SUCCESS, "Read ServiceProviderConfig",
                            "", ComplianceUtils.getWire(method, responseString,
                            headerString, responseStatus, subTests));

        } catch (Exception e) {
            throw new CriticalComplianceException
                    (new TestResult(TestResult.ERROR, "Parse ServiceProviderConfig",
                            "Could not parse the json format returned from /ServiceProviderConfig. " + e.getMessage(),
                            ComplianceUtils.getWire(method, responseString, headerString,
                                    responseStatus, subTests)));
        }
    }


}
