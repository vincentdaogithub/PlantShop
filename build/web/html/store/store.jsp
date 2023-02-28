<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/store/store.min.css" />
        <script src="/PlantShop/js/main.min.js" defer></script>

        <title>Viridis - Store</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />
        
        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/gif/main_bg.gif" alt="background" />
        </div>
        
        <main>
            <section class="plant-list-container">
                <div class="plant-list-header">
                    <div class="plant-img-header">Image</div>
                    <div class="plant-info-header">Info</div>
                </div>

                <c:forEach items="${requestScope.plants}" var="plant">
                    <div class="plant-container">
                        <div class="plant-img-container">
                            <div class="img-container">
                                <img class="plant-img" src="/PlantShop/img/plants/plant_placeholder_black.jpg" alt="${plant.name}" />
                            </div>
                        </div>

                        <div class="plant-info-container">
                            <div class="info-box">
                                <p><b>Name: </b><c:out value="${plant.name}" default="" /></p>
                            </div>

                            <div class="info-box">
                                <p><b>Price: </b><c:out value="${plant.price}$" default="" /></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
