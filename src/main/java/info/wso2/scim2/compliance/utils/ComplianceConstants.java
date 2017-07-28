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

    public static class TestConstants {
        public static final String LABEL_IMPORTANT =  "label-important";
        public static final String LABEL_SUCCESS =  "label-success";
        public static final String LABEL_INFO = "label-info";

        public static final String FAILED =  "Failed";
        public static final String SUCCESS =  "Success";
        public static final String SKIPPED = "Skipped";

        public static final String SERVICE_PROVIDER_ENDPOINT = "/ServiceProviderConfig";
        public static final String RESOURCE_TYPE_ENDPOINT = "/ResourceType";
        public static final String USERS_ENDPOINT = "/Users";
        public static final String GROUPS_ENDPOINT = "/Groups";

        public static final String SCHEMA_LIST_TEST = "Schema List Test";
        public static final String REQUIRED_ATTRIBUTE_TEST = "Required Attribute Test";
        public static final String ATTRIBUTE_MUTABILITY_TEST = "Attribute Mutability Test";
        public static final String ALL_GROUPS_IN_TEST = "All Groups In Test";
        public static final String ALL_USERS_IN_TEST = "All Users In Test";
        public static final String FILTER_CONTENT_TEST = "Filter Content Test";
    }

    public static class DefinedInstances {
        public static String DEFINED_USER =
                        "{" +
                        "  \"password\": \"7019asd84\"," +
                        "  \"userName\": \"bjensenexamplecom\"," +
                        "  \"emails\": [" +
                        "    {" +
                        "      \"value\": \"bjensen@example.com\"," +
                        "      \"type\": \"work\"," +
                        "      \"primary\": true" +
                        "    }," +
                        "    {" +
                        "      \"value\": \"babs@jensen.org\"," +
                        "      \"type\": \"home\"" +
                        "    }" +
                        "  ]," +
                        "}";

        public static String DEFINED_UPDATED_USER =
                        "{" +
                        "  \"password\": \"7019asd84\"," +
                        "  \"userName\": \"bjensenexamplecom\"," +
                        "  \"emails\": [" +
                        "    {" +
                        "      \"value\": \"bjensen@wso2.com\"," +
                        "      \"type\": \"work\"," +
                        "      \"primary\": true" +
                        "    }," +
                        "    {" +
                        "      \"value\": \"babs@wso2.org\"," +
                        "      \"type\": \"home\"" +
                        "    }" +
                        "  ]," +
                        "}";


        public static String DEFINED_PATCH_USER_PAYLOAD =
                "{\"schemas\":[\"urn:ietf:params:scim:api:messages:2.0:PatchOp\"]," +
                        "\"Operations\":[{\"op\":\"add\",\"value\":{\"nickName\":\"shaggy\"}}]}";
    }

}
