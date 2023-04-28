<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/login/login.css" />
        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis - Login</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>

        <main>
            <section class="form">
                <form action="/PlantShop/PageRedirect?page=manage-plants" method="post">
                    <h2 class="title">Create plant</h2>

                    <div class="input">
                        <label for="name">Name:</label>
                        <input id="name" type="text" name="name" required />
                    </div>

                    <div class="input">
                        <label for="price">Price</label>
                        <input id="price" type="number" name="price" required />
                    </div>

                    <div class="input">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description"></textarea>
                    </div>

                    <div class="input">
                        <label for="status">Status:</label>
                        <select id="status" name="status">
                            <option value="1">Active</option>
                            <option value="0">Inactive</option>
                        </select>
                    </div>

                    <div class="input">
                        <label for="category">Category:</label>
                        <select id="category" name="category">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.key}">${fn:escapeXml(category.value)}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="hidden" name="action" value="admin" />
                    <input type="hidden" name="admin-action" value="update" />
                    <input type="hidden" name="update" value="plant-new" />
                    <input type="submit" value="Create" />
                </form>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
