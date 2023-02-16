<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/about/about.min.css" />
        <title>Viridis - About</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />
        
        <main>
            <section class="about-container">
                <div class="about-panel">
                    <h2>
                        Millions of years,<br />
                        older than the first human.
                    </h2>
                    <div class="img-container">
                        <img src="/PlantShop/gif/index/intro_bg_black.gif" />
                    </div>
                </div>

                <div class="about-panel">
                    <h2>
                        Among the first things we see.
                    </h2>

                    <div class="img-container">
                        <img src="/PlantShop/gif/index/intro_bg_black.gif" />
                    </div>
                </div>

                <div class="about-panel">
                    <h2>
                        Among the first things we live.
                    </h2>

                    <div class="img-container">
                        <img src="/PlantShop/gif/index/intro_bg_black.gif" />
                    </div>
                </div>

                <div class="about-panel">
                    <h2>
                        So we give you a chance,<br />
                        to look back.
                    </h2>

                    <div class="img-container">
                        <img src="/PlantShop/gif/index/intro_bg_black.gif" />
                    </div>
                </div>
            </section>

            <section class="author-container">
                <div class="center-container">
                    <h2>About author</h2>

                    <div class="poem">
                        My journey to exceed the "before".<br />
                        Give it time. Age. Old.<br />
                        If there's any left,<br />
                        that is, all "me" have left.
                    </div>
    
                    <img src="/PlantShop/gif/about/lain.gif" />
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>

    <script src="/PlantShop/js/main.min.js"></script>
</html>
