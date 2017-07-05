package info.wso2.scim2.compliance.utils;

public class ComplianceConstants {

    public static class RequestCodeConstants {
        public static final String URL = "url";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String CLIENT_ID = "clientId";
        public static final String CLIENT_SECRET = "clientSecret";
        public static final String AUTHORIZATION_SERVER = "authorizationServer";
        public static final String AUTHORIZATION_HEADER = "authorizationHeader";
        public static final String AUTHORIZATION_METHOD = "authMethod";

        public static final String HTTP = "http";
        public static final String HTTPS = "https";
    }

    public static class ResponseCodeConstants {
        public static final int CODE_BAD_REQUEST = 400;

    }

    public static class TestConstants {
        public static final String LABEL_IMPORTANT =  "label-important";
        public static final String LABEL_SUCCESS =  "label-success";
        public static final String LABEL_INFO = "label-info";

        public static final String FAILED =  "Failed";
        public static final String SUCCESS =  "Success";
        public static final String SKIPPED = "Skipped";

        public static final String SERVICE_PROVIDER_ENDPOINT = "/ServiceProviderConfig";
        public static final String USERS_ENDPOINT = "/Users";

    }
}
