<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@ page isErrorPage="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/error/error.css" />
        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis - Error</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>
        
        <main>
            <section class="error-container">
                <h1>Oops. Something's wrong here.</h1>
                <p>Error code: ${requestScope.error.errorCode}</p>
                <p>Error detail: ${requestScope.error.errorDetail}</p>
                <p>${requestScope.error.errorMessage}</p>

                <p>Full stack trace:</p>
                <div>${fn:escapeXml(exception)}</div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
