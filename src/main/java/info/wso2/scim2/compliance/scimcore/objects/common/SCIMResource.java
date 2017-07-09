package info.wso2.scim2.compliance.scimcore.objects.common;

public class SCIMResource {

    protected String id;
    protected SCIMResource.MetaObj meta;

    private static class MetaObj {
        private String resourceType;
        private String created;
        private String lastModified;
        private String version;
        private String location;

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getLastModified() {
            return lastModified;
        }

        public void setLastModified(String lastModified) {
            this.lastModified = lastModified;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MetaObj getMeta() {
        return meta;
    }

    public void setMeta(MetaObj meta) {
        this.meta = meta;
    }
}
