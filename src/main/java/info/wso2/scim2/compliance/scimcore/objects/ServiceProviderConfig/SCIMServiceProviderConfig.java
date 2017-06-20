package info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig;


import java.util.List;

public class SCIMServiceProviderConfig {

    private PatchObj patch;
    private List<String> schemas;
    private FilterObj filter;
    private String documentationUri;
    private EtagObj etag;
    private SortObj sort;
    private ChangePasswordObj changePassword;
    private BulkObj bulk;
    private List<AuthSchemasObj> authenticationSchemes;

    @Override
    public String toString() {
        return "SCIMServiceProviderConfig{" +
                "patch=" + patch.getSupported() +
                ", schemas=" + schemas.get(0) +
                ", filter=" + filter.getMaxResults() +
                ", documentationUri='" + documentationUri + '\'' +
                ", etag=" + etag.getSupported() +
                ", sort=" + sort.getSupported() +
                ", changePassword=" + changePassword.getSupported() +
                ", bulk=" + bulk.getMaxOperations() +
                ", authenticationSchemes=" + authenticationSchemes.get(0).getName() +
                '}';
    }

    private class PatchObj {

       private boolean supported;

       public boolean getSupported() {
           return supported;
       }

       public void setSupported(boolean supported) {
           this.supported = supported;
       }
   }

    private class FilterObj {

        private boolean supported;
        private int maxResults;

        public int getMaxResults() {
            return maxResults;
        }

        public void setMaxResults(int maxResults) {
            this.maxResults = maxResults;
        }

        public boolean getSupported() {
            return supported;
        }

        public void setSupported(boolean supported) {
            this.supported = supported;
        }
    }

    private class AuthSchemasObj {
        private String name;
        private String description;
        private String specURI;
        private String type;
        private boolean primary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSpecURI() {
            return specURI;
        }

        public void setSpecURI(String specURI) {
            this.specURI = specURI;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean getPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }


    private class EtagObj {

        private boolean supported;

        public boolean getSupported() {
            return supported;
        }

        public void setSupported(boolean supported) {
            this.supported = supported;
        }
    }

    private class SortObj {

        private boolean supported;

        public boolean getSupported() {
            return supported;
        }

        public void setSupported(boolean supported) {
            this.supported = supported;
        }
    }

    private class ChangePasswordObj {

        private boolean supported;

        public boolean getSupported() {
            return supported;
        }

        public void setSupported(boolean supported) {
            this.supported = supported;
        }
    }

    private class BulkObj {

        private int maxPayloadSize;
        private int maxOperations;

        public int getMaxPayloadSize() {
            return maxPayloadSize;
        }

        public void setMaxPayloadSize(int maxPayloadSize) {
            this.maxPayloadSize = maxPayloadSize;
        }

        public int getMaxOperations() {
            return maxOperations;
        }

        public void setMaxOperations(int maxOperations) {
            this.maxOperations = maxOperations;
        }
    }


}