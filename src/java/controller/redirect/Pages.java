package controller.redirect;

public enum Pages {
    HOME("home", "/index.jsp"),
    ABOUT("about", "/html/about/about.jsp"),
    STORE("store", "/html/store/store.jsp"),
    LOGIN("login", "/html/login/login.jsp"),
    REGISTER("register", "/html/login/register.jsp"),
    ACCOUNT("account", "/html/account/account.jsp"),
    MANAGE("manage", "/html/manage/manage.jsp"),
    ERROR("error", "/html/error/error.jsp");

    private final String page;
    private final String url;

    Pages(String page, String url) {
        this.page = page;
        this.url = url;
    }

    public String getPage() {
        return this.page;
    }

    public String getURL() {
        return this.url;
    }
}
