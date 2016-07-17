package be.jidoka.employee.application.api.service.employee;

public class ContactInformationDTO {

    private String email;
    private String mobile;
    private String linkedIn;
    private String skype;
    private String twitter;

    private ContactInformationDTO() { }

    private ContactInformationDTO(ContactInformationDTOBuilder builder) {
        this.email = builder.email;
        this.mobile = builder.mobile;
        this.linkedIn = builder.linkedIn;
        this.skype = builder.skype;
        this.twitter = builder.twitter;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getSkype() {
        return skype;
    }

    public String getTwitter() {
        return twitter;
    }

    public static class ContactInformationDTOBuilder {
        private String email;
        private String mobile;
        private String linkedIn;
        private String skype;
        private String twitter;

        private ContactInformationDTOBuilder() { }

        public static ContactInformationDTOBuilder contactInformationDTOWith() {
            return new ContactInformationDTOBuilder();
        }

        public ContactInformationDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ContactInformationDTOBuilder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public ContactInformationDTOBuilder linkedIn(String linkedIn) {
            this.linkedIn = linkedIn;
            return this;
        }

        public ContactInformationDTOBuilder skype(String skype) {
            this.skype = skype;
            return this;
        }

        public ContactInformationDTOBuilder twitter(String twitter) {
            this.twitter = twitter;
            return this;
        }

        public ContactInformationDTO build() {
            return new ContactInformationDTO(this);
        }
    }

}
