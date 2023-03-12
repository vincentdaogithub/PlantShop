<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/login/login.css" />
        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis - Login</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />
        
        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>

        <main>
            <section class="login">
                <form action="/PlantShop/PageRedirect?page=home" method="post">
                    <h2 class="title">Login</h2>
                    
                    <c:if test="${loginFail}">
                        <p class="error-message">Invalid email or password.</p>
                    </c:if>

                    <div class="input-container">
                        <label for="email">Email:</label>
                        <input id="email" type="email" name="email" value="${fn:escapeXml(email)}" placeholder="email..." required />
                    </div>
                    
                    <div class="input-container">
                        <label for="password">Password:</label>
                        <input id="password" type="password" name="password" value="${fn:escapeXml(password)}" placeholder="password..." required />
                    </div>

                    <input type="hidden" name="action" value="login" />
                    <input type="submit" value="Login" />

                    <a class="register-link" href="/PlantShop/PageRedirect?page=register">
                        New to the store? Sign up instead.
                    </a>
                </form>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
