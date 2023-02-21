<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/index/index.min.css" />
        <title>Viridis</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/gif/index/intro_bg_black.gif" alt="tree and lake" />
        </div>

        <main>
            <section class="intro-container">
                <h1 class="intro-title">
                    Green the world, back to its once spectrum.
                </h1>
            </section>

            <section class="content-container">
                <article class="content-section">
                    <div class="content-text">
                        <h2>Factories, cars, power plants...</h2>
                    </div>

                    <div class="content-img">
                        <img src="/PlantShop/img/plants/plant_placeholder_black.jpg" alt="sprout placeholder" />
                    </div>
                </article>

                <div class="content-break-container">
                    <img class="content-break" src="/PlantShop/img/dividers/divider_black.png" alt="plant divider" />
                </div>

                <article class="content-section">
                    <div class="content-img">
                        <img src="/PlantShop/img/plants/plant_placeholder_black.jpg" alt="sprout placeholder" />
                    </div>

                    <div class="content-text">
                        <h2>... are but friendly to the green.</h2>
                    </div>
                </article>

                <div class="content-break-container">
                    <img class="content-break" src="/PlantShop/img/dividers/divider_white.png" alt="plant divider" />
                </div>

                <article class="content-section">
                    <div class="content-text">
                        <h2>
                            We paint them black.<br />
                            Smear with white.
                        </h2>
                    </div>

                    <div class="content-img">
                        <img src="/PlantShop/img/plants/plant_placeholder_black.jpg" alt="sprout placeholder" />
                    </div>
                </article>

                <div class="content-break-container">
                    <img class="content-break" src="/PlantShop/img/dividers/divider_black.png" alt="plant divider" />
                </div>

                <article class="content-section">
                    <div class="content-img">
                        <img src="/PlantShop/img/plants/plant_placeholder_black.jpg" alt="sprout placeholder" />
                    </div>

                    <div class="content-text">
                        <h2>
                            So we plant.<br />
                            And water them, do mind.
                        </h2>
                    </div>
                </article>

                <div class="content-break-container">
                    <img class="content-break" src="/PlantShop/img/dividers/divider_white.png" alt="plant divider" />
                </div>

                <article class="content-section">
                    <div class="content-text">
                        <h2>
                            For growing,<br />
                            is the reflection of the past.
                        </h2>
                    </div>

                    <div class="content-img">
                        <img src="/PlantShop/img/plants/plant_placeholder_black.jpg" alt="sprout placeholder" />
                    </div>
                </article>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>

    <script src="/PlantShop/js/main.min.js"></script>
</html>
