<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/index/index.min.css" />
        <script src="/PlantShop/js/main.min.js" defer></script>

        <title>Viridis</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>

        <main>
            <section class="intro-container">
                <div class="img-container intro-img">
                    <img src="/PlantShop/img/resource/placeholder.jpg" alt="plant" />
                </div>

                <div class="intro-text">
                    <h1>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</h1>
                </div>
            </section>

            <section class="intro-content-container">
                <div class="intro-content">
                    <div class="img-container content-img">
                        <img src="/PlantShop/img/resource/placeholder.jpg" alt="plant" />
                    </div>

                    <div class="content-text">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                    </div>
                </div>

                <div class="intro-content">
                    <div class="img-container content-img">
                        <img src="/PlantShop/img/resource/placeholder.jpg" alt="plant" />
                    </div>

                    <div class="content-text">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                    </div>
                </div>

                <div class="intro-content">
                    <div class="img-container content-img">
                        <img src="/PlantShop/img/resource/placeholder.jpg" alt="plant" />
                    </div>

                    <div class="content-text">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                    </div>
                </div>

                <div class="intro-content">
                    <div class="img-container content-img">
                        <img src="/PlantShop/img/resource/placeholder.jpg" alt="plant" />
                    </div>

                    <div class="content-text">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
