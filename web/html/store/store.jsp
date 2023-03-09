<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="business.store.PriceRanges" %>
<%@ page import="business.store.Sorts" %>

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
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>
        
        <main>
            <section class="list-function-container">
                <div class="search-container">
                    <h2>Search</h2>

                    <form class="search-name" action="/PlantShop/PageRedirect?page=store" method="post">
                        <h3>By name:</h3>

                        <div class="label-input">
                            <input type="text" name="name" value="<c:out value='${requestScope.searchName}' default='' />" placeholder="plant name..." required />
                        </div>
    
                        <input type="hidden" name="search" value="name" />
                        <input type="submit" value="Search" />
                    </form>
    
                    <form class="search-price" action="/PlantShop/PageRedirect?page=store" method="post">
                        <h3>By price:</h3>

                        <div class="label-input radio">
                            <input id="below-5" type="radio" name="price" value="below-5" <c:out value="${requestScope.searchPrice == PriceRanges.BELOW_5.range ? 'checked' : ''}" default="" /> />
                            <label for="below-5">Below 5$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="5-to-10" type="radio" name="price" value="5-10" <c:out value="${requestScope.searchPrice == PriceRanges.FIVE_TO_10.range ? 'checked' : ''}" default="" /> />
                            <label for="5-to-10">5$ - 10$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="10-to-15" type="radio" name="price" value="10-15" <c:out value="${requestScope.searchPrice == PriceRanges.TEN_TO_15.range ? 'checked' : ''}" default="" /> />
                            <label for="10-to-15">10$ - 15$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="above-15" type="radio" name="price" value="above-15" <c:out value="${requestScope.searchPrice == PriceRanges.ABOVE_15.range ? 'checked' : ''}" default="" /> />
                            <label for="above-15">Above 15$</label>
                        </div>
    
                        <input type="hidden" name="search" value="price" />
                        <input type="submit" value="Search" />
                    </form>
                </div>
                
                <div class="sort-container">
                    <h2>Sort</h2>
    
                    <form class="sort" action="/PlantShop/PageRedirect?page=store<c:out value='${requestScope.searchQuery}' default='' />" method="post">
                        <div class="label-input radio">
                            <input id="name-asc" type="radio" name="sort" value="name-asc" <c:out value="${requestScope.sortCheck == Sorts.NAME_ASC.sort ? 'checked' : ''}" default="" /> />
                            <label for="name-asc">By name (ASC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="name-dsc" type="radio" name="sort" value="name-dsc" <c:out value="${requestScope.sortCheck == Sorts.NAME_DSC.sort ? 'checked' : ''}" default="" /> />
                            <label for="name-dsc">By name (DSC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="price-asc" type="radio" name="sort" value="price-asc" <c:out value="${requestScope.sortCheck == Sorts.PRICE_ASC.sort ? 'checked' : ''}" default="" /> />
                            <label for="price-asc">By price (ASC)</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="price-dsc" type="radio" name="sort" value="price-dsc" <c:out value="${requestScope.sortCheck == Sorts.PRICE_DSC.sort ? 'checked' : ''}" default="" /> />
                            <label for="price-dsc">By price (DSC)</label>
                        </div>

                        <input type="submit" value="Sort" />
                    </form>
                </div>
            </section>

            <section class="list-container">
                <c:choose>
                    <c:when test="${requestScope.plants == null}">
                        <div class="list-item-container">
                            <h2>No plants were found. Try again?</h2>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${requestScope.plants}" var="plant">
                            <div class="list-item-container">
                                <div class="img-container">
                                    <img src="/PlantShop/ImageRetriever?resource=plant&pid=${plant.ID}" alt="${plant.name}" />
                                </div>
        
                                <div class="list-item-info-container">
                                    <div class="info-box">
                                        <p><c:out value="${plant.name}" default="" /> - <c:out value="${plant.price}$" default="" /></p>
                                    </div>
        
                                    <form class="add-to-cart-container" action="/PlantShop/Controller?<c:out value='${requestScope.searchQuery}' default='' /><c:out value='${requestScope.sortQuery}' default='' />" method="post">
                                        <div class="add-to-cart-box">
                                            <div class="decrease" onclick="setQuantity(this, '-')">-</div>
                                            <input class="add-to-cart" type="number" name="quantity" placeholder="quantity..." min="0" step="1" required />
                                            <div class="increase" onclick="setQuantity(this, '+')">+</div>
                                        </div>
        
                                        <input type="hidden" name="action" value="add-to-cart" />
                                        <input type="hidden" name="pid" value="${plant.ID}" />
                                        <input class="add-button" type="submit" value="Add to cart" />
                                    </form>
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
