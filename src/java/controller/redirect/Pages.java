package controller.redirect;

public enum Pages {
    HOME("home", "/index.jsp", Authentications.OPEN),
    ABOUT("about", "/html/about.jsp", Authentications.OPEN),
    STORE("store", "/html/store/store.jsp", Authentications.OPEN),
    LOGIN("login", "/html/login/login.jsp", Authentications.OPEN),
    REGISTER("register", "/html/login/register.jsp", Authentications.OPEN),
    PROFILE("account", "/html/user/profile.jsp", Authentications.USER),
    MANAGE("manage", "/html/admin/manage.jsp", Authentications.ADMIN),
    ERROR("error", "/html/error.jsp", Authentications.SERVER);

    private final String page;
    private final String url;
    private final Authentications authen;

    Pages(String page, String url, Authentications authen) {
        this.page = page;
        this.url = url;
        this.authen = authen;
    }

    public String getPage() {
        return this.page;
    }

    public String getURL() {
        return this.url;
    }

    public final Authentications getAuthentication() {
        return this.authen;
    }

    public static final Pages convertStringToPage(String pageToConvert) {
        if (pageToConvert == null) {
            return null;
        }

        for (Pages pageElement : Pages.values()) {
            if (pageToConvert.equals(pageElement.getPage())) {
                return pageElement;
            }
        }

        return null;
    }
}
