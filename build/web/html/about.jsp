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
        
        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/gif/index/intro_bg_black.gif" alt="tree and lake" />
        </div>

        <main>
            <section class="about-container">
                <div class="about-content">
                    <div class="poem">
                        Millions of years,<br />
                        older than the first human.<br />
                        Among the first things we see.<br />
                        Among the first things we live.<br />
                        So we give you the mean,<br />
                        to look back.
                    </div>
                </div>
            </section>

            <section class="author-container">
                <div class="author-content">
                    <h2>About author</h2>

                    <div class="poem">
                        My journey to exceed the "before".<br />
                        Give it time. Age. Old.<br />
                        If there's any left,<br />
                        that is, all "me" have left.
                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>

    <script src="/PlantShop/js/main.min.js"></script>
</html>
