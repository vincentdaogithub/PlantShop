<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <section class="search-and-sort-container">
                <div class="search-container">
                    <h2>Search</h2>

                    <form class="search-name">
                        <h3>By name:</h3>

                        <div class="label-input">
                            <input type="text" name="name" placeholder="plant name..." required />
                        </div>
    
                        <input type="hidden" name="action" value="store" />
                        <input type="hidden" name="search" value="name" />
                        <input type="submit" value="Search" />
                    </form>
    
                    <form class="search-price">
                        <h3>By price:</h3>

                        <div class="label-input radio">
                            <input id="below-5" type="radio" name="price" value="below-5" checked />
                            <label for="below-5">Below 5$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="5-to-10" type="radio" name="price" value="5-to-10" />
                            <label for="5-to-10">5$ - 10$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="10-to-15" type="radio" name="price" value="10-to-15" />
                            <label for="10-to-15">10$ - 15$</label>
                        </div>
    
                        <div class="label-input radio">
                            <input id="above-15" type="radio" name="price" value="above-15" />
                            <label for="above-15">Above 15$</label>
                        </div>
    
                        <input type="hidden" name="action" value="store" />
                        <input type="hidden" name="search" value="price" />
                        <input type="submit" value="Search" />
                    </form>
                </div>
                
                <div class="sort-container">
                    <h2>Sort</h2>
    
                    <form class="sort">
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
    
                        <input type="hidden" name="action" value="store" />
                        <input type="hidden" name="sort" value="sort" />
                        <input type="submit" value="Sort" />
                    </form>
                </div>
            </section>

            <section class="plant-list-container">
                <c:forEach items="${requestScope.plants}" var="plant">
                    <div class="plant-container">
                        <div class="img-container">
                            <img src="/PlantShop/ImageRetriever?resource=plant&pid=${plant.ID}" alt="${plant.name}" />
                        </div>

                        <div class="plant-info-container">
                            <div class="info-box">
                                <p><c:out value="${plant.name}" default="" /> - <c:out value="${plant.price}$" default="" /></p>
                            </div>

                            <form class="add-to-cart-container" action="/PlantShop/Controller">
                                <div class="add-to-cart-box">
                                    <div class="decrease-quantity">-</div>
                                    <input class="add-to-cart" id="pid${plant.ID}" type="number" name="pid${plant.ID}" placeholder="quantity..." min="0" step="1" required />
                                    <div class="increase-quantity">+</div>
                                </div>
    
                                <input type="hidden" name="action" value="store" />
                                <input type="hidden" name="store" value="add-to-cart" />
                                <input class="add-button" type="submit" value="Add to cart" />
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
