<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="business.sort.PriceRanges" %>
<%@ page import="business.sort.Sorts" %>
<%@ page import="controller.redirect.Pages" %>
<%@ page import="obj.order.OrderStatuses" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/store/store.css" />

        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis - Order</title>
    </head>

    <c:set value="${fn:escapeXml(searchQuery)}${fn:escapeXml(sortQuery)}" var="query" />

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>

        <main>
            <section class="menu">
                <c:choose>
                    <c:when test="${page.page == Pages.CART.page}">
                        <div class="current-page"><h2>Cart</h2></div>
                    </c:when>

                    <c:otherwise>
                        <a href="/PlantShop/PageRedirect?page=cart"><h2>Cart</h2></a>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${page.page == Pages.ORDER.page}">
                        <div class="current-page"><h2>Order</h2></div>
                    </c:when>

                    <c:otherwise>
                        <a href="/PlantShop/PageRedirect?page=order"><h2>Order</h2></a>
                    </c:otherwise>
                </c:choose>
            </section>

            <section class="display">
                <section class="list-function">
                    <div class="search-function">
                        <h2>Search</h2>

                        <form class="search" action="/PlantShop/PageRedirect?page=order" method="post">
                            <h3>By name:</h3>

                            <div class="input">
                                <input type="text" name="name" value="${fn:escapeXml(searchName)}" placeholder="plant name..." />
                            </div>

                            <input type="hidden" name="search" value="name" />
                            <input type="submit" value="Search" />
                        </form>

                        <form class="search" action="/PlantShop/PageRedirect?page=order" method="post">
                            <h3>By price:</h3>

                            <div class="input radio">
                                <input id="below-5" type="radio" name="price" value="below-5" ${requestScope.searchPrice == PriceRanges.BELOW_5.range ? 'checked' : ''} />
                                <label for="below-5">Below 5$</label>
                            </div>

                            <div class="input radio">
                                <input id="5-to-10" type="radio" name="price" value="5-10" ${requestScope.searchPrice == PriceRanges.FIVE_TO_10.range ? 'checked' : ''} />
                                <label for="5-to-10">5$ - 10$</label>
                            </div>

                            <div class="input radio">
                                <input id="10-to-15" type="radio" name="price" value="10-15" ${requestScope.searchPrice == PriceRanges.TEN_TO_15.range ? 'checked' : ''} />
                                <label for="10-to-15">10$ - 15$</label>
                            </div>

                            <div class="input radio">
                                <input id="above-15" type="radio" name="price" value="above-15" ${requestScope.searchPrice == PriceRanges.ABOVE_15.range ? 'checked' : ''} />
                                <label for="above-15">Above 15$</label>
                            </div>

                            <input type="hidden" name="search" value="price" />
                            <input type="submit" value="Search" />
                        </form>
                    </div>

                    <div class="sort-function">
                        <h2>Sort</h2>

                        <form class="sort" action="/PlantShop/PageRedirect?page=order${fn:escapeXml(searchQuery)}" method="post">
                            <div class="input radio">
                                <input id="order-time" type="radio" name="sort" value="order-time" ${requestScope.sortCheck == Sorts.NAME_ASC.sort ? 'checked' : ''} />
                                <label for="order-time">By order time</label>
                            </div>

                            <div class="input radio">
                                <input id="name-asc" type="radio" name="sort" value="name-asc" ${requestScope.sortCheck == Sorts.NAME_ASC.sort ? 'checked' : ''} />
                                <label for="name-asc">By name (ASC)</label>
                            </div>

                            <div class="input radio">
                                <input id="name-dsc" type="radio" name="sort" value="name-dsc" ${requestScope.sortCheck == Sorts.NAME_DSC.sort ? 'checked' : ''} />
                                <label for="name-dsc">By name (DSC)</label>
                            </div>

                            <div class="input radio">
                                <input id="price-asc" type="radio" name="sort" value="price-asc" ${requestScope.sortCheck == Sorts.PRICE_ASC.sort ? 'checked' : ''} />
                                <label for="price-asc">By price (ASC)</label>
                            </div>

                            <div class="input radio">
                                <input id="price-dsc" type="radio" name="sort" value="price-dsc" ${requestScope.sortCheck == Sorts.PRICE_DSC.sort ? 'checked' : ''} />
                                <label for="price-dsc">By price (DSC)</label>
                            </div>

                            <input type="submit" value="Sort" />
                        </form>
                    </div>
                </section>

                <section class="list">
                    <h2>Order list:</h2>

                    <c:choose>
                        <c:when test="${requestScope.orders == null || empty requestScope.orders}">
                            <h2>The order list is empty. <a href="/PlantShop/PageRedirect?page=store">Add one now!</a></h2>
                        </c:when>

                        <c:otherwise>
                            <fmt:parseNumber var="listSize" integerOnly="true" value="${size % 5 != 0 ? size / 5 + 1 : size / 5}" />

                            <div class="list-index">
                                <a class="begin-list" href="/PlantShop/PageRedirect?page=order&amp;index=0${query}">&lt;&lt;</a>
                                <a class="decrease-index" href="/PlantShop/PageRedirect?page=order&amp;index=${index - 1}${query}">&lt;</a>
                                <div class="index">page ${index + 1} of ${listSize}</div>
                                <a class="increase-index" href="/PlantShop/PageRedirect?page=order&amp;index=${index + 1 >= listSize ? listSize - 1 : index + 1}${query}">&gt;</a>
                                <a class="end-list" href="/PlantShop/PageRedirect?page=order&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                            </div>

                            <c:forEach items="${requestScope.orders}" var="order" begin="${index * 5}" end="${index * 5 + 4}">
                                <div class="list-item">
                                    <div class="img-container item-img square-img-height">
                                        <img src="/PlantShop/ImageRetriever?resource=plant&pid=${order.value.plant.ID}" alt="plant ${order.value.plant.ID}" />
                                    </div>

                                    <div class="info order-info">
                                        <a href="/PlantShop/PageRedirect?page=plant&amp;pid=${order.value.plant.ID}">
                                            <p>${fn:escapeXml(order.value.plant.name)} - ${order.value.plant.price}</p>
                                        </a>

                                        <p>Order ID: ${order.key.orderID}</p>
                                        <p>Pay: ${order.value.quantity} * ${order.value.plant.price}$ = ${order.value.quantity * order.value.plant.price}$</p>
                                        <p>Status: ${order.key.status.status}</p>
                                        <p>Order date: ${order.key.orderDate}</p>
                                        <p>Shipping date: ${order.key.shipDate}</p>

                                        <c:choose>
                                            <c:when test="${order.key.status == OrderStatuses.PROCESSING}">
                                                <form class="order-update" action="/PlantShop/PageRedirect?page=order" method="post">
                                                    <input type="hidden" name="action" value="order-update" />
                                                    <input type="hidden" name="update" value="cancel" />
                                                    <input type="hidden" name="order-id" value="${order.key.orderID}" />

                                                    <input type="submit" value="Cancel" />
                                                </form>
                                            </c:when>

                                            <c:when test="${order.key.status == OrderStatuses.CANCELLED}">
                                                <form class="order-update" action="/PlantShop/PageRedirect?page=order" method="post">
                                                    <input type="hidden" name="action" value="order-update" />
                                                    <input type="hidden" name="update" value="order-again" />
                                                    <input type="hidden" name="order-id" value="${order.key.orderID}" />

                                                    <input type="submit" value="Order again" />
                                                </form>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="list-index">
                                <a class="begin-list" href="/PlantShop/PageRedirect?page=order&amp;index=0${query}">&lt;&lt;</a>
                                <a class="decrease-index" href="/PlantShop/PageRedirect?page=order&amp;index=${index - 1}${query}">&lt;</a>
                                <div class="index">page ${index + 1} of ${listSize}</div>
                                <a class="increase-index" href="/PlantShop/PageRedirect?page=order&amp;index=${index + 1 >= listSize ? listSize - 1 : index + 1}${query}">&gt;</a>
                                <a class="end-list" href="/PlantShop/PageRedirect?page=order&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </section>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
