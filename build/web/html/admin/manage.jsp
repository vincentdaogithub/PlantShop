<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/admin/manage.css" />
        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>

        <main>
            <section class="admin-functions">
                <a href="/PlantShop/PageRedirect?page=manage-accounts"><h2>Manage accounts</h2></a>
                <a href="/PlantShop/PageRedirect?page=view-orders"><h2>View orders</h2></a>
                <a href="/PlantShop/PageRedirect?page=manage-plants"><h2>Manage plant</h2></a>
                <a href="/PlantShop/PageRedirect?page=manage-categories"><h2>Manage categories</h2></a>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
