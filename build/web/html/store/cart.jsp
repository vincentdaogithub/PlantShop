<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="business.sort.PriceRanges" %>
<%@ page import="business.sort.Sorts" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/store/store.css" />

        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis - Cart</title>
    </head>
    
    <c:set value="${fn:escapeXml(searchQuery)}${fn:escapeXml(sortQuery)}" var="query" />

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />
        
        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>
        
        <main>
            <section class="list-function-container">
                <div class="search-container">
                    <h2>Search</h2>

                    <form class="search-name" action="/PlantShop/PageRedirect?page=cart" method="post">
                        <h3>By name:</h3>

                        <div class="label-input">
                            <input type="text" name="name" value="${fn:escapeXml(searchName)}" placeholder="plant name..." />
                        </div>
    
                        <input type="hidden" name="search" value="name" />
                        <input type="submit" value="Search" />
                    </form>
    
                    <form class="search-price" action="/PlantShop/PageRedirect?page=cart" method="post">
                        <h3>By price:</h3>

                        <div class="label-input radio">
                            <input id="below-5" type="radio" name="price" value="below-5" ${requestScope.searchPrice == PriceRanges.BELOW_5.range ? 'checked' : ''} />
                            <label for="below-5">Below 5$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="5-to-10" type="radio" name="price" value="5-10" ${requestScope.searchPrice == PriceRanges.FIVE_TO_10.range ? 'checked' : ''} />
                            <label for="5-to-10">5$ - 10$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="10-to-15" type="radio" name="price" value="10-15" ${requestScope.searchPrice == PriceRanges.TEN_TO_15.range ? 'checked' : ''} />
                            <label for="10-to-15">10$ - 15$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="above-15" type="radio" name="price" value="above-15" ${requestScope.searchPrice == PriceRanges.ABOVE_15.range ? 'checked' : ''} />
                            <label for="above-15">Above 15$</label>
                        </div>
    
                        <input type="hidden" name="search" value="price" />
                        <input type="submit" value="Search" />
                    </form>
                </div>
                
                <div class="sort-container">
                    <h2>Sort</h2>
    
                    <form class="sort" action="/PlantShop/PageRedirect?page=cart${fn:escapeXml(searchQuery)}" method="post">
                        <div class="label-input radio">
                            <input id="order-time" type="radio" name="sort" value="order-time" ${requestScope.sortCheck == Sorts.NAME_ASC.sort ? 'checked' : ''} />
                            <label for="order-time">By order time</label>
                        </div>

                        <div class="label-input radio">
                            <input id="name-asc" type="radio" name="sort" value="name-asc" ${requestScope.sortCheck == Sorts.NAME_ASC.sort ? 'checked' : ''} />
                            <label for="name-asc">By name (ASC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="name-dsc" type="radio" name="sort" value="name-dsc" ${requestScope.sortCheck == Sorts.NAME_DSC.sort ? 'checked' : ''} />
                            <label for="name-dsc">By name (DSC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="price-asc" type="radio" name="sort" value="price-asc" ${requestScope.sortCheck == Sorts.PRICE_ASC.sort ? 'checked' : ''} />
                            <label for="price-asc">By price (ASC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="price-dsc" type="radio" name="sort" value="price-dsc" ${requestScope.sortCheck == Sorts.PRICE_DSC.sort ? 'checked' : ''} />
                            <label for="price-dsc">By price (DSC)</label>
                        </div>

                        <input type="submit" value="Sort" />
                    </form>
                </div>
            </section>

            <section class="list-container">
                <c:choose>
                    <c:when test="${requestScope.cart == null || empty requestScope.cart}">
                        <div class="list-item-container">
                            <h2>The cart is empty. <a href="/PlantShop/PageRedirect?page=store">Add one now!</a></h2>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <fmt:parseNumber var="listSize" integerOnly="true" value="${size % 5 != 0 ? size / 5 + 1 : size / 5}" />

                        <div class="list-index">
                            <a class="begin-list" href="/PlantShop/PageRedirect?page=cart&amp;index=0${query}">&lt;&lt;</a>
                            <a class="decrease-index" href="/PlantShop/PageRedirect?page=cart&amp;index=${index - 1}${query}">&lt;</a>
                            <div class="index">page ${index + 1} of ${listSize}</div>
                            <a class="increase-index" href="/PlantShop/PageRedirect?page=cart&amp;index=${index + 1 >= listSize ? listSize - 1 : index + 1}${query}">&gt;</a>
                            <a class="end-list" href="/PlantShop/PageRedirect?page=cart&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                        </div>

                        <c:forEach items="${requestScope.cart}" var="order" begin="${index * 5}" end="${index * 5 + 4}">
                            <div class="list-item-container">
                                <div class="img-container plant-img square-img-height">
                                    <img src="/PlantShop/ImageRetriever?resource=plant&pid=${order.key.ID}" />
                                </div>

                                <div class="list-item-info-container">
                                    <a href="/PlantShop/PageRedirect?page=plant&amp;pid=${order.key.ID}">
                                        <p>${fn:escapeXml(order.key.name)} - ${order.key.price}$</p>
                                    </a>

                                    <p>
                                        Quantity: <c:out value="${order.value}" /> - 
                                        <span class="quantity-update-link" tabindex="0" onclick="toggleUpdate('quantity-${order.key.ID}', 'quantity-update')">
                                            Change
                                        </span>
                                    </p>

                                    <form id="quantity-${order.key.ID}" class="quantity-update" data-toggle="off" action="/PlantShop/PageRedirect?page=cart${query}" method="post" style="display: none;">
                                        <div class="add-to-cart-box">
                                            <div class="decrease-quantity" onclick="setQuantity(this, '-')" tabindex="0"><p class="scr-reader">Decrease quantity</p>-</div>
                                            <label class="scr-reader" for="plant-quantity-${order.key.ID}">Plant quantity for ${fn:escapeXml(order.key.name)}" /></label>
                                            <input id="plant-quantity-${order.key.ID}" class="add-to-cart" type="number" name="quantity" placeholder="quantity..." min="0" step="1" required />
                                            <div class="increase-quantity" onclick="setQuantity(this, '+')" tabindex="0"><p class="scr-reader">Increase quantity</p>+</div>
                                        </div>
        
                                        <input type="hidden" name="action" value="cart-update" />
                                        <input type="hidden" name="update" value="update" />
                                        <input type="hidden" name="pid" value="${order.key.ID}" />
                                        <input class="add-button" type="submit" value="Update" />
                                    </form>

                                    <p>
                                        Total: ${order.value} * ${order.key.price} = ${order.value * order.key.price}$
                                    </p>
                                </div>
                            </div>
                        </c:forEach>

                        <div class="list-index">
                            <a class="begin-list" href="/PlantShop/PageRedirect?page=cart&amp;index=0${query}">&lt;&lt;</a>
                            <a class="decrease-index" href="/PlantShop/PageRedirect?page=cart&amp;index=${index - 1}${query}">&lt;</a>
                            <div class="index">page ${index + 1} of ${listSize}</div>
                            <a class="increase-index" href="/PlantShop/PageRedirect?page=cart&amp;index=${index + 1 >= listSize ? listSize - 1 : index + 1}${query}">&gt;</a>
                            <a class="end-list" href="/PlantShop/PageRedirect?page=cart&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
