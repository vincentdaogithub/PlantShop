package controller.redirect;

public enum Pages {
    HOME("home", "/PlantShop/index.jsp"),
    ABOUT("about", "/PlantShop/html/about/about.jsp"),
    STORE("store", "/PlantShop/html/store/store.jsp"),
    LOGIN("login", "/PlantShop/html/login/login.jsp"),
    REGISTER("register", "/PlantShop/html/login/register.jsp"),
    ACCOUNT("account", "/PlantShop/html/account/account.jsp"),
    MANAGE("manage", "/PlantShop/html/manage/manage.jsp"),
    ERROR("error", "/PlantShop/html/error/error.jsp");

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
