<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/user/profile.min.css" />
        <title>Viridis</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <main>
            <section class="profile-container">
                <div class="profile-img-container">
                    <div class="profile-img-content">
                        <h2 class="hello-user">Hello, ${sessionScope.account.fullname}</h2>
                        <div class="img-container">
                            <img class="profile-img" src="https://dummyimage.com/600x400/ffffff/000000" alt="profile image" />
                        </div>
                    </div>
                </div>

                <div class="profile-information-container">
                    <form class="profile-information">
                        <div class="profile-field">
                            <label for="email">Email: </label>
                            <input id="email" type="text" value="${sessionScope.account.email}" readonly />
                        </div>

                        <div class="profile-field">
                            <label for="password">Password: </label>
                            <input id="password" type="password" value="${sessionScope.account.password}" readonly />
                        </div>

                        <div class="profile-field">
                            <label for="fullname">Full name: </label>
                            <input id="fullname" type="text" value="${sessionScope.account.fullname}" readonly />
                        </div>

                        <div class="profile-field">
                            <label for="phone">Phone number: </label>
                            <input id="phone" type="tel" value="${sessionScope.account.phone}" readonly />
                        </div>
                    </form>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>

    <script src="/PlantShop/js/main.min.js"></script>

</html>