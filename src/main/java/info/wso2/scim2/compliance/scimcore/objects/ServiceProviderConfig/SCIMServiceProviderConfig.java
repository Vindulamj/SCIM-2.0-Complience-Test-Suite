package info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig;

import java.util.ArrayList;
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
    private List<AuthSchemasObj> authenticationSchemes = new ArrayList<>();

    public void setPatch(boolean supported) {
        PatchObj patchObj = new PatchObj();
        patchObj.setSupported(supported);
        this.patch = patchObj;
    }

    public void setSchemas(List<String> schemas) {
        this.schemas = schemas;
    }

    public void setFilter(boolean supported, int maxResults) {
        FilterObj filterObj = new FilterObj();
        filterObj.setSupported(supported);
        filterObj.setMaxResults(maxResults);
        this.filter = filterObj;
    }

    public void setDocumentationUri(String documentationUri) {
        this.documentationUri = documentationUri;
    }

    public void setEtag(boolean supported) {
        EtagObj etagObj = new EtagObj();
        etagObj.setSupported(supported);
        this.etag = etagObj;
    }

    public void setSort(boolean supported) {
        SortObj sortObj = new SortObj();
        sortObj.setSupported(supported);
        this.sort = sortObj;
    }

    public void setChangePassword(boolean supported) {
        ChangePasswordObj changePasswordObj = new ChangePasswordObj();
        changePasswordObj.setSupported(supported);
        this.changePassword = changePasswordObj;
    }

    public void setBulk(boolean supported, int maxPayloadSize, int maxOperations) {
        BulkObj bulkObj = new BulkObj();
        bulkObj.setMaxOperations(maxOperations);
        bulkObj.setMaxPayloadSize(maxPayloadSize);
        bulkObj.setSupported(supported);
        this.bulk = bulkObj;
    }

    public void setAuthenticationSchemes(String name, String description, String specURI, String type, boolean primary) {
        AuthSchemasObj authSchemasObj = new AuthSchemasObj();
        authSchemasObj.setName(name);
        authSchemasObj.setDescription(description);
        authSchemasObj.setSpecURI(specURI);
        authSchemasObj.setType(type);
        authSchemasObj.setPrimary(primary);
        authenticationSchemes.add(authSchemasObj);
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

        private boolean supported;
        private int maxPayloadSize;
        private int maxOperations;

        public boolean isSupported() {
            return supported;
        }

        public void setSupported(boolean supported) {
            this.supported = supported;
        }

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