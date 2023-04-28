package controller.redirect;

import security.filter.Authentications;

public enum Pages {
    HOME("home", "/index.jsp", Authentications.OPEN),
    CART("cart", "/html/store/cart.jsp", Authentications.OPEN),
    STORE("store", "/html/store/store.jsp", Authentications.OPEN),
    PLANT("plant", "html/store/plant.jsp", Authentications.OPEN),
    ORDER("order", "html/store/order.jsp", Authentications.USER),
    LOGIN("login", "/html/login/login.jsp", Authentications.OPEN),
    REGISTER("register", "/html/login/register.jsp", Authentications.OPEN),
    PROFILE("profile", "/html/user/profile.jsp", Authentications.LOGGED_IN),
    MANAGE("manage", "/html/admin/manage.jsp", Authentications.ADMIN),
    ERROR("error", "/html/error/error.jsp", Authentications.SERVER),
    MANAGE_ACCOUNTS("manage-accounts", "html/admin/manage_accounts.jsp", Authentications.ADMIN),
    VIEW_ORDERS("view-orders", "/html/admin/view_orders.jsp", Authentications.ADMIN),
    MANAGE_PLANTS("manage-plants", "/html/admin/manage_plants.jsp", Authentications.ADMIN),
    MANAGE_CATEGORIES("manage-categories", "/html/admin/manage_categories.jsp", Authentications.ADMIN),
    CREATE_PLANT("create-plant", "/html/admin/create_plant.jsp", Authentications.ADMIN);

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
