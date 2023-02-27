<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/store/store.min.css" />
        <title>Viridis - Login</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />
        
        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/gif/index/intro_bg_black.gif" alt="tree and lake" />
        </div>

        <main>
            <section class="plant-list-container">
                <div class="plant-container">
                    <div class="plant-img-container">
                        <img />
                    </div>

                    <div class=""></div>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>

    <script src="/PlantShop/js/main.min.js"></script>
</html>
