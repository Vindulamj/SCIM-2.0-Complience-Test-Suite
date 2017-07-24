package info.wso2.scim2.compliance.scimcore.objects.User;

import info.wso2.scim2.compliance.scimcore.objects.common.SCIMResource;

import java.util.ArrayList;

public class SCIMUser extends SCIMResource {

    private String userName;
    private String password;
    private String externalId;
    private ArrayList<String> schemas;
    private NameObj name;
    private String displayName;
    private String nickName;
    private String profileUrl;
    private ArrayList<EmailObj> emails;
    private ArrayList<AddressObj> addresses;
    private ArrayList<PhoneNumberObj> phoneNumbers;
    private ArrayList<ImsObj> ims;
    private ArrayList<PhotosObj> photos;
    private String userType;
    private String title;
    private String preferredLanguage;
    private String locale;
    private String timezone;
    private boolean active;
    private ArrayList<GroupsObj> groups;
    private ArrayList<X509CertificatesObj> x509Certificates;

    public SCIMUser() {
    }

    public SCIMUser(String id, String userName, String password, String externalId,
                    ArrayList<String> schemas, NameObj name, String displayName,
                    String nickName, String profileUrl, ArrayList<EmailObj> emails,
                    ArrayList<AddressObj> addresses, ArrayList<PhoneNumberObj> phoneNumbers,
                    ArrayList<ImsObj> ims, ArrayList<PhotosObj> photos, String userType,
                    String title, String preferredLanguage, String locale, String timezone,
                    boolean active, ArrayList<GroupsObj> groups,
                    ArrayList<X509CertificatesObj> x509Certificates) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.externalId = externalId;
        this.schemas = schemas;
        this.name = name;
        this.displayName = displayName;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
        this.emails = emails;
        this.addresses = addresses;
        this.phoneNumbers = phoneNumbers;
        this.ims = ims;
        this.photos = photos;
        this.userType = userType;
        this.title = title;
        this.preferredLanguage = preferredLanguage;
        this.locale = locale;
        this.timezone = timezone;
        this.active = active;
        this.groups = groups;
        this.x509Certificates = x509Certificates;
    }

    public SCIMUser(String id, String userName, String password, String externalId,
                    ArrayList<String> schemas, NameObj name, String displayName,
                    String nickName, String profileUrl, ArrayList<EmailObj> emails,
                    ArrayList<AddressObj> addresses, ArrayList<PhoneNumberObj> phoneNumbers,
                    ArrayList<ImsObj> ims, ArrayList<PhotosObj> photos, String userType,
                    String title, String preferredLanguage, String locale, String timezone,
                    boolean active, ArrayList<X509CertificatesObj> x509Certificates) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.externalId = externalId;
        this.schemas = schemas;
        this.name = name;
        this.displayName = displayName;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
        this.emails = emails;
        this.addresses = addresses;
        this.phoneNumbers = phoneNumbers;
        this.ims = ims;
        this.photos = photos;
        this.userType = userType;
        this.title = title;
        this.preferredLanguage = preferredLanguage;
        this.locale = locale;
        this.timezone = timezone;
        this.active = active;
        this.x509Certificates = x509Certificates;
    }

    private static class NameObj{

        private String formatted;
        private String familyName;
        private String givenName;
        private String middleName;
        private String honorificPrefix;
        private String honorificSuffix;

        public NameObj(String formatted, String familyName, String givenName,
                       String middleName, String honorificPrefix, String honorificSuffix) {
            this.formatted = formatted;
            this.familyName = familyName;
            this.givenName = givenName;
            this.middleName = middleName;
            this.honorificPrefix = honorificPrefix;
            this.honorificSuffix = honorificSuffix;
        }

        public String getFormatted() {
            return formatted;
        }

        public void setFormatted(String formatted) {
            this.formatted = formatted;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getHonorificPrefix() {
            return honorificPrefix;
        }

        public void setHonorificPrefix(String honorificPrefix) {
            this.honorificPrefix = honorificPrefix;
        }

        public String getHonorificSuffix() {
            return honorificSuffix;
        }

        public void setHonorificSuffix(String honorificSuffix) {
            this.honorificSuffix = honorificSuffix;
        }
    }

    private static class EmailObj{
        private String value;
        private String type;
        private boolean primary;

        public EmailObj(String value, String type, boolean primary) {
            this.value = value;
            this.type = type;
            this.primary = primary;
        }

        public EmailObj(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }

    private static class AddressObj{
        private String streetAddress;
        private String locality;
        private String region;
        private String postalCode;
        private String country;
        private String formatted;
        private String type;
        private boolean primary;

        public AddressObj(String streetAddress, String locality, String region, String postalCode,
                          String country, String formatted, String type, boolean primary) {
            this.streetAddress = streetAddress;
            this.locality = locality;
            this.region = region;
            this.postalCode = postalCode;
            this.country = country;
            this.formatted = formatted;
            this.type = type;
            this.primary = primary;
        }

        public AddressObj(String streetAddress, String locality, String region, String postalCode,
                          String country, String formatted, String type) {
            this.streetAddress = streetAddress;
            this.locality = locality;
            this.region = region;
            this.postalCode = postalCode;
            this.country = country;
            this.formatted = formatted;
            this.type = type;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getFormatted() {
            return formatted;
        }

        public void setFormatted(String formatted) {
            this.formatted = formatted;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }

    public static class PhoneNumberObj{
        private String value;
        private String type;

        public PhoneNumberObj(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    private static class ImsObj{
        private String value;
        private String type;

        public ImsObj(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


    private static class PhotosObj{
        private String value;
        private String type;

        public PhotosObj(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    private static class GroupsObj{
        private String value;
        private String $ref;
        private String display;

        public GroupsObj(String value, String $ref, String display) {
            this.value = value;
            this.$ref = $ref;
            this.display = display;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String get$ref() {
            return $ref;
        }

        public void set$ref(String $ref) {
            this.$ref = $ref;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }
    }

    private static class X509CertificatesObj{
        private String value;

        public X509CertificatesObj(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public ArrayList<String> getSchemas() {
        return schemas;
    }

    public void setSchemas(ArrayList<String> schemas) {
        this.schemas = schemas;
    }

    public NameObj getName() {
        return name;
    }

    public void setName(NameObj name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public ArrayList<EmailObj> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<EmailObj> emails) {
        this.emails = emails;
    }

    public ArrayList<AddressObj> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<AddressObj> addresses) {
        this.addresses = addresses;
    }

    public ArrayList<PhoneNumberObj> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<PhoneNumberObj> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList<ImsObj> getIms() {
        return ims;
    }

    public void setIms(ArrayList<ImsObj> ims) {
        this.ims = ims;
    }

    public ArrayList<PhotosObj> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<PhotosObj> photos) {
        this.photos = photos;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<GroupsObj> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<GroupsObj> groups) {
        this.groups = groups;
    }

    public ArrayList<X509CertificatesObj> getX509Certificates() {
        return x509Certificates;
    }

    public void setX509Certificates(ArrayList<X509CertificatesObj> x509Certificates) {
        this.x509Certificates = x509Certificates;
    }

    public static String getDefinedUser(){
        return  "{\n" +
                "  \"password\": \"7019asd84\",\n" +
                "  \"userName\": \"bjensenexamplecom\",\n" +
                "}";

    }
}
