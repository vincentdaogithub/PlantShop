<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ page import="controller.redirect.Pages" %>

<header>
    <nav>
        <div class="banner-container">
            <a class="banner" href="/PlantShop/PageRedirect?page=home">
                <img src="/PlantShop/img/logo/logo.png" alt="website logo" />
            </a>
        </div>

        <div class="nav-bar">
            <c:choose>
                <c:when test="${requestScope.page == null || requestScope.page.page == Pages.HOME.page}">
                    <p class="nav-link">Home</p>
                </c:when>
                    
                <c:otherwise>
                    <a class="nav-link" href="/PlantShop/PageRedirect?page=home">Home</a>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${requestScope.page.page == Pages.CART.page}">
                    <p class="nav-link">Cart</p>
                </c:when>
                    
                <c:otherwise>
                    <a class="nav-link" href="/PlantShop/PageRedirect?page=cart">Cart</a>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${requestScope.page.page == Pages.STORE.page}">
                    <p class="nav-link">Store</p>
                </c:when>
                    
                <c:otherwise>
                    <a class="nav-link" href="/PlantShop/Controller?action=store&index=0">Store</a>
                </c:otherwise>
            </c:choose>
            
            <c:choose>
                <c:when test="${sessionScope.account.role == 0}">
                    <c:choose>
                        <c:when test="${requestScope.page.page == Pages.PROFILE.page}">
                            <p class="nav-link">Profile</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=profile">Profile</a>
                        </c:otherwise>
                    </c:choose>
                </c:when>

                <c:when test="${sessionScope.account.role == 1}">
                    <c:choose>
                        <c:when test="${requestScope.page.page == Pages.MANAGE.page}">
                            <p class="nav-link">Manage</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=manage">Manage</a>
                        </c:otherwise>
                    </c:choose>
                </c:when>

                <c:otherwise>
                    <c:choose>
                        <c:when test="${requestScope.page.page == Pages.LOGIN.page}">
                            <p class="nav-link">Login</p>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/PlantShop/PageRedirect?page=login">Login</a>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="nav-bar-toggle-container" data-toggle="off" onclick="toggleNavBarMobile()">
            <img class="top" src="/PlantShop/img/icons/menu_close_white.png" alt="menu off" />
            <img class="bottom" src="/PlantShop/img/icons/menu_open_white.png" alt="menu on" />
        </div>
    </nav>
</header>