<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/login/login.min.css" />
        <title>Viridis - Login</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />
        
        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/gif/main_bg.gif" alt="tree and lake" />
        </div>

        <main>
            <section class="login-container">
                <form action="/PlantShop/Controller" method="post">
                    <h2 class="login-title">Login</h2>

                    <div class="input-container">
                        <label for="email">Email:</label><br />
                        <input id="email" type="email" name="email" placeholder="email..." required />
                    </div>
                    
                    <div class="input-container">
                        <label for="password">Password:</label><br />
                        <input id="password" type="password" name="password" placeholder="password..." required />
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

    <script src="/PlantShop/js/main.min.js"></script>
</html>
