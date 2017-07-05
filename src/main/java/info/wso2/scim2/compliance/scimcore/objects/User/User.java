package info.wso2.scim2.compliance.scimcore.objects.User;

import java.util.ArrayList;

public class User {

    private String id;
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

    public User() {
    }

    public User(String id, String userName, String password, String externalId,
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

    public User(String id, String userName, String password, String externalId,
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
    }

    public static class PhoneNumberObj{
        private String value;
        private String type;

        public PhoneNumberObj(String value, String type) {
            this.value = value;
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
    }

    private static class PhotosObj{
        private String value;
        private String type;

        public PhotosObj(String value, String type) {
            this.value = value;
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
    }

    private static class X509CertificatesObj{
        private String value;

        public X509CertificatesObj(String value) {
            this.value = value;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    private static User user;
    public static User getDefinedUser(){
        if (user == null){
            ArrayList<String> schemas = new ArrayList<String>() {{add("urn:ietf:params:scim:schemas:core:2.0:User");}};

            NameObj name = new NameObj("Ms. Barbara J Jensen, III","Jensen","Barbara",
                    "Jane","Ms.","III");

            ArrayList<EmailObj> emails = new ArrayList<EmailObj>() {{
                add(new EmailObj("bjensen@example.com","work",true));
                add(new EmailObj("babs@jensen.org","home"));
            }};

            ArrayList<AddressObj> addresses = new ArrayList<AddressObj>() {{
                add(new AddressObj("100 Universal City Plaza","Hollywood",
                        "CA","91608","USA",
                        "100 Universal City Plaza Hollywood, CA 91608 USA","work", true));
                add(new AddressObj("456 Hollywood Blvd","Hollywood",
                        "CA","91608","USA",
                        "456 Hollywood Blvd Hollywood, CA 91608 USA","home"));
            }};

            ArrayList<PhoneNumberObj> phoneNumbers = new ArrayList<PhoneNumberObj>(){{
                    add(new PhoneNumberObj("555-555-5555","work"));
                    add(new PhoneNumberObj("555-555-4444","mobile"));
            }};

            ArrayList<ImsObj> ims = new ArrayList<ImsObj>(){{
                add(new ImsObj("someaimhandle","aim"));
            }};

            ArrayList<PhotosObj> photos = new ArrayList<PhotosObj>(){{
                add(new PhotosObj("https://photos.example.com/profilephoto/72930000000Ccne/F","photo"));
                add(new PhotosObj("photos.example.com/profilephoto/72930000000Ccne/T","thumbnail"));
            }};

            ArrayList<GroupsObj> groups = new ArrayList<GroupsObj>(){{
                add(new GroupsObj("e9e30dba-f08f-4109-8486-d5c6a331660a",
                        "../Groups/e9e30dba-f08f-4109-8486-d5c6a331660a","Tour Guides"));
                add(new GroupsObj("fc348aa8-3835-40eb-a20b-c726e15c55b5",
                        "../Groups/fc348aa8-3835-40eb-a20b-c726e15c55b5","Employees"));
                add(new GroupsObj("71ddacd2-a8e7-49b8-a5db-ae50d0a5bfd7",
                        "../Groups/71ddacd2-a8e7-49b8-a5db-ae50d0a5bfd7","US Employees"));
            }};

            ArrayList<X509CertificatesObj> x509Certificates = new ArrayList<X509CertificatesObj>(){{
                add(new X509CertificatesObj("MIIDQzCCAqygAwIBAgICEAAwDQYJKoZIhvcNAQEFBQAwTjELMAkGA1UEBhMCVVMxEz" +
                        "ARBgNVBAgMCkNhbGlmb3JuaWExFDASBgNVBAoMC2V4YW1wbGUuY29tMRQwEgYDVQQDDAtleGFtcGxlLmNvbTAe" +
                        "Fw0xMTEwMjIwNjI0MzFaFw0xMjEwMDQwNjI0MzFaMH8xCzAJBgNVBAYTAlVTMRMwEQYDVQQIDApDYWxpZm9ybml" +
                        "hMRQwEgYDVQQKDAtl eGFtcGxlLmNvbTEhMB8GA1UEAwwYTXMuIEJhcmJhcmEgSiBKZW5zZW4gSUlJMSIw IAYJ" +
                        "KoZIhvcNAQkBFhNiamVuc2VuQGV4YW1wbGUuY29tMIIBIjANBgkqhkiG9w0B AQEFAAOCAQ8AMIIBCgKCAQEA7K" +
                        "r+Dcds/JQ5GwejJFcBIP682X3xpjis56AK02bc 1FLgzdLI8auoR+cC9/Vrh5t66HkQIOdA4unHh0AaZ4xL5PhVb" +
                        "XIPMB5vAPKpzz5i PSi8xO8SL7I7SDhcBVJhqVqr3HgllEG6UClDdHO7nkLuwXq8HcISKkbT5WFTVfFZ zidPl8H" +
                        "Z7DhXkZIRtJwBweq4bvm3hM1Os7UQH05ZS6cVDgweKNwdLLrT51ikSQG3 DYrl+ft781UQRIqxgwqCfXEuDiinPh" +
                        "0kkvIi5jivVu1Z9QiwlYEdRbLJ4zJQBmDr SGTMYn4lRc2HgHO4DqB/bnMVorHB0CC6AV1QoFK4GPe1LwIDAQABo" +
                        "3sweTAJBgNV HRMEAjAAMCwGCWCGSAGG+EIBDQQfFh1PcGVuU1NMIEdlbmVyYXRlZCBDZXJ0aWZp Y2F0ZTAdBgN" +
                        "VHQ4EFgQU8pD0U0vsZIsaA16lL8En8bx0F/gwHwYDVR0jBBgwFoAU dGeKitcaF7gnzsNwDx708kqaVt0wDQYJKo" +
                        "ZIhvcNAQEFBQADgYEAA81SsFnOdYJt Ng5Tcq+/ByEDrBgnusx0jloUhByPMEVkoMZ3J7j1ZgI8rAbOkNngX8+pKf" +
                        "TiDz1R C4+dx8oU6Za+4NJXUjlL5CvV6BEYb1+QAEJwitTVvxB/A67g42/vzgAtoRUeDov1+GFiBZ+GNF/cAYKcMt" +
                        "Gcrs2i97ZkJMo"));
            }};

            return new User("2819c223-7f76-453a-919d-413861904646", "bjensen@example.com",
                    "t1meMa$heen","701984", schemas, name,"Babs Jensen",
                    "Babs","https://login.example.com/bjensen",emails, addresses, phoneNumbers,
                    ims, photos, "Employee","Tour Guide","en-US","en-US",
                    "America/Los_Angeles",true, x509Certificates);
        }
        return user;
    }
}
