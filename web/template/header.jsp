<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav>
        <div class="banner">
            <a href="/PlantShop/PageRedirect?page=home">
                <img src="/PlantShop/img/logo/logo_white.png" alt="website logo" />
            </a>
        </div>

        <div class="nav-bar">
            <c:choose>
                <c:when test="${sessionScope.page == 'home' || sessionScope.page == null}">
                    <p>Home</p>
                </c:when>
                    
                <c:otherwise>
                    <a href="/PlantShop/PageRedirect?page=home">Home</a>
                </c:otherwise>
            </c:choose>
                    
            <c:choose>
                <c:when test="${sessionScope.page == 'about'}">
                    <p>About</p>
                </c:when>
                    
                <c:otherwise>
                    <a href="/PlantShop/PageRedirect?page=about">About</a>
                </c:otherwise>
            </c:choose>
                    
            <c:choose>
                <c:when test="${sessionScope.page == 'store'}">
                    <p>Store</p>
                </c:when>
                    
                <c:otherwise>
                    <a href="/PlantShop/PageRedirect?page=store">Store</a>
                </c:otherwise>
            </c:choose>
            
            
            <c:choose>
                <c:when test="${sessionScope.account.accountRole == 0}">
                    <c:choose>
                        <c:when test="${sessionScope.page == 'account'}">
                            <p>Account</p>
                        </c:when>

                        <c:otherwise>
                            <a href="/PlantShop/PageRedirect?page=account">Account</a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                    
                <c:when test="${sessionScope.account.accountRole == 1}">
                    <c:choose>
                        <c:when test="${sessionScope.page == 'manage'}">
                            <p>Manage</p>
                        </c:when>

                        <c:otherwise>
                            <a href="/PlantShop/PageRedirect?page=manage">Manage</a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                    
                <c:otherwise>
                    <c:choose>
                        <c:when test="${sessionScope.page == 'login'}">
                            <p>Login</p>
                        </c:when>

                        <c:otherwise>
                            <a href="/PlantShop/PageRedirect?page=login">Login</a>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</header>