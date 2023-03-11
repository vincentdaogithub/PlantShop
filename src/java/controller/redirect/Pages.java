package controller.redirect;

import security.filter.Authentications;

public enum Pages {
    HOME("home", "/index.jsp", Authentications.OPEN),
    CART("cart", "/html/store/cart.jsp", Authentications.OPEN),
    STORE("store", "/html/store/store.jsp", Authentications.OPEN),
    PLANT("plant", "html/store/plant.jsp", Authentications.OPEN),
    LOGIN("login", "/html/login/login.jsp", Authentications.OPEN),
    REGISTER("register", "/html/login/register.jsp", Authentications.OPEN),
    PROFILE("profile", "/html/user/profile.jsp", Authentications.USER),
    MANAGE("manage", "/html/admin/manage.jsp", Authentications.ADMIN),
    ERROR("error", "/html/error/error.jsp", Authentications.SERVER);

    private final String page;
    private final String url;
    private final Authentications authentication;

    Pages(String page, String url, Authentications authentication) {
        this.page = page;
        this.url = url;
        this.authentication = authentication;
    }

    public String getPage() {
        return this.page;
    }

    public String getURL() {
        return this.url;
    }

    public final Authentications getAuthentication() {
        return this.authentication;
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
