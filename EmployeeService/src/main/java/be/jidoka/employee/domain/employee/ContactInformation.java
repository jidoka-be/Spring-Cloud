package be.jidoka.employee.domain.employee;

import java.util.List;

public class ContactInformation {

    private final String email;
    private final String mobile;
    private final List<SocialMedia> socialMedia;

    private ContactInformation(String email, String mobile, List<SocialMedia> socialMedia) {
        this.email = email;
        this.mobile = mobile;
        this.socialMedia = socialMedia;
    }

    public static ContactInformation of(String email, String mobile, List<SocialMedia> socialMedia) {
        return new ContactInformation(email, mobile, socialMedia);
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public List<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public String getSocialMediaAccount(SocialMediaType socialMediaType) {
        return socialMedia.stream()
                .filter(media -> media.getType() == socialMediaType)
                .map(SocialMedia::getAccount)
                .findFirst()
                .orElse("");
    }

}
