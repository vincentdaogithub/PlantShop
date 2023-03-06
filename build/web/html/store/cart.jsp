<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.Map" %>
<%@page import="obj.plant.Plant"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/store/store.min.css" />
        <script src="/PlantShop/js/main.min.js" defer></script>

        <title>Viridis - About</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />
        
        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>
        
        <main>
            <section class="list-function-container">
                <div class="sort-container">
                    <h2>Sort</h2>

                    <form class="sort">
                        <div class="label-input radio">
                            <input id="order" type="radio" name="sort" value="order" checked />
                            <label for="order">By order time</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="name-asc" type="radio" name="sort" value="name-asc" checked />
                            <label for="name-asc">By name (ASC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="name-dsc" type="radio" name="sort" value="name-dsc" />
                            <label for="name-dsc">By name (DSC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="price-asc" type="radio" name="sort" value="price-asc" />
                            <label for="price-asc">By price (ASC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="price-dsc" type="radio" name="sort" value="price-dsc" />
                            <label for="price-dsc">By price (DSC)</label>
                        </div>
    
                        <input type="hidden" name="action" value="cart" />
                        <input type="hidden" name="cart" value="sort" />
                        <input type="submit" value="Sort" />
                    </form>
                </div>
            </section>

            <section class="list-container">
                <c:choose>
                    <c:when test="${requestScope.cart == null}">
                        <div class="list-item-container">
                            <h2>The cart is empty. <a href="/PlantShop/PageRedirect?page=store">Add one now!</a></h2>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${requestScope.cart}" var="order">
                            <div class="list-item-container">
                                <div class="img-container">
                                    <img src="/PlantShop/ImageRetriever?resource=plant&pid=${order.key.ID}" />
                                </div>

                                <div class="list-item-info-container">
                                    <h3><c:out value="${order.key.name}" /> - <c:out value="${order.key.price}$" /></h3>
                                    <p>Quantity: <c:out value="${order.value}" /></p>
                                    <p>Total: <c:out value="${order.value}" /> * <c:out value="${order.key.price}$" /> = <c:out value="${order.value * order.key.price}$" /></p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
