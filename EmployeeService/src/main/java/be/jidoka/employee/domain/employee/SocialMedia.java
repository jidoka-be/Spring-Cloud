package be.jidoka.employee.domain.employee;

public final class SocialMedia {

    private final SocialMediaType type;
    private final String account;

    private SocialMedia(SocialMediaType type, String account) {
        this.type = type;
        this.account = account;
    }

    public static SocialMedia linkedIn(String account) {
        return new SocialMedia(SocialMediaType.LINKEDIN, account);
    }

    public static SocialMedia skype(String account) {
        return new SocialMedia(SocialMediaType.SKYPE, account);
    }

    public static SocialMedia twitter(String account) {
        return new SocialMedia(SocialMediaType.TWITTER, account);
    }

    public String getAccount() {
        return account;
    }

    public SocialMediaType getType() {
        return type;
    }

}
