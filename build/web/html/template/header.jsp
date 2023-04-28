<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="controller.redirect.Pages" %>
<%@page import="security.filter.Authentications" %>

<header>
    <nav>
        <div class="banner-container">
            <c:choose>
                <c:when test="${sessionScope.account.role == Authentications.ADMIN.code}">
                    <a class="banner" href="/PlantShop/PageRedirect?page=manage">
                        <img src="/PlantShop/img/logo/logo.png" alt="website logo" />
                    </a>
                </c:when>

                <c:otherwise>
                    <a class="banner" href="/PlantShop/PageRedirect?page=home">
                        <img src="/PlantShop/img/logo/logo.png" alt="website logo" />
                    </a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="nav-bar">
            <c:choose>
                <c:when test="${sessionScope.account.role == Authentications.ADMIN.code}">
                    <c:choose>
                        <c:when test="${page == null || page.page == Pages.MANAGE.page}">
                            <p class="nav-link" tabindex="0">Manage</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=manage">Manage</a>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${page.page == Pages.PROFILE.page}">
                            <p class="nav-link" tabindex="0">Profile</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=profile">Profile</a>
                        </c:otherwise>
                    </c:choose>
                </c:when>

                <c:otherwise>
                    <c:choose>
                        <c:when test="${page == null || page.page == Pages.HOME.page}">
                            <p class="nav-link" tabindex="0">Home</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=home">Home</a>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${page.page == Pages.CART.page}">
                            <p class="nav-link">Cart</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=cart">Cart</a>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${page.page == Pages.STORE.page}">
                            <p class="nav-link" tabindex="0">Store</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=store">Store</a>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${sessionScope.account.role >= Authentications.USER.code}">
                            <c:choose>
                                <c:when test="${page.page == Pages.PROFILE.page}">
                                    <p class="nav-link">Profile</p>
                                </c:when>

                                <c:otherwise>
                                    <a class="nav-link" href="/PlantShop/PageRedirect?page=profile">Profile</a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>

                        <c:otherwise>
                            <c:choose>
                                <c:when test="${page.page == Pages.LOGIN.page}">
                                    <p class="nav-link">Login</p>
                                </c:when>

                                <c:otherwise>
                                    <a class="nav-link" href="/PlantShop/PageRedirect?page=login">Login</a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="nav-bar-toggle-container" data-toggle="off" tabindex="0" onclick="toggleNavBarMobile()">
            <img class="top" src="/PlantShop/img/icons/menu_close_white.png" alt="menu off" style="opacity: 1;" />
            <img class="bottom" src="/PlantShop/img/icons/menu_open_white.png" alt="menu on" style="opacity: 0;" />
        </div>
    </nav>
</header>