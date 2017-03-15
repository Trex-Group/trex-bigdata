package hadoop.homework.useragent;

/**
 * Responsible for handling all parsed browser details since there may
 * be many of these in one browser UserAgent string.
 *
 * @author Glen Smith (glen@bytecode.com.au)
 */
public class UserAgentDetails {

    private String browserName;
    private String browserVersion;
    private String browserComments;

    /**
     * Constructor.
     *
     * @param browserName     the name of the browser
     * @param browserVersion  the version of the browser
     * @param browserComments the operating system the browser is running on
     */
    UserAgentDetails(String browserName, String browserVersion, String browserComments) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.browserComments = browserComments;
    }

    public String getBrowserComments() {
        return browserComments;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

}
