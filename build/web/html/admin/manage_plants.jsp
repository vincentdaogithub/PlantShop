<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="business.sort.PriceRanges" %>
<%@ page import="business.sort.Sorts" %>
<%@page import="obj.plant.PlantStatuses" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/admin/manage_list.css" />

        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis - Store</title>
    </head>

    <c:set value="${fn:escapeXml(searchQuery)}${fn:escapeXml(sortQuery)}" var="query" />

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>

        <main>
            <section class="display">
                <div class="list-function">
                    <div class="search-function">
                        <h2>Search</h2>

                        <form class="function" action="/PlantShop/PageRedirect?page=manage-plants" method="post">
                            <h3>By name:</h3>

                            <div class="input">
                                <input type="text" name="name" value="${fn:escapeXml(searchName)}" placeholder="plant name..." />
                            </div>

                            <input type="hidden" name="search" value="name" />
                            <input type="submit" value="Search" />
                        </form>

                        <form class="function" action="/PlantShop/PageRedirect?page=manage-plants" method="post">
                            <h3>By price:</h3>

                            <div class="input radio">
                                <input id="below-5" type="radio" name="price" value="below-5" ${searchPrice == PriceRanges.BELOW_5.range ? 'checked' : ''} />
                                <label for="below-5">Below 5$</label>
                            </div>

                            <div class="input radio">
                                <input id="5-to-10" type="radio" name="price" value="5-10" ${searchPrice == PriceRanges.FIVE_TO_10.range ? 'checked' : ''} />
                                <label for="5-to-10">5$ - 10$</label>
                            </div>

                            <div class="input radio">
                                <input id="10-to-15" type="radio" name="price" value="10-15" ${searchPrice == PriceRanges.TEN_TO_15.range ? 'checked' : ''} />
                                <label for="10-to-15">10$ - 15$</label>
                            </div>

                            <div class="input radio">
                                <input id="above-15" type="radio" name="price" value="above-15" ${searchPrice == PriceRanges.ABOVE_15.range ? 'checked' : ''} />
                                <label for="above-15">Above 15$</label>
                            </div>

                            <input type="hidden" name="search" value="price" />
                            <input type="submit" value="Search" />
                        </form>
                    </div>

                    <div class="sort-function">
                        <h2>Sort</h2>

                        <form class="function" action="/PlantShop/PageRedirect?page=store${fn:escapeXml(searchQuery)}" method="post">
                            <div class="input radio">
                                <input id="name-asc" type="radio" name="sort" value="name-asc" ${sortCheck == Sorts.NAME_ASC.sort ? 'checked' : ''} />
                                <label for="name-asc">By name (ASC)</label>
                            </div>

                            <div class="input radio">
                                <input id="name-dsc" type="radio" name="sort" value="name-dsc" ${sortCheck == Sorts.NAME_DSC.sort ? 'checked' : ''} />
                                <label for="name-dsc">By name (DSC)</label>
                            </div>

                            <div class="input radio">
                                <input id="price-asc" type="radio" name="sort" value="price-asc" ${sortCheck == Sorts.PRICE_ASC.sort ? 'checked' : ''} />
                                <label for="price-asc">By price (ASC)</label>
                            </div>

                            <div class="input radio">
                                <input id="price-dsc" type="radio" name="sort" value="price-dsc" ${sortCheck == Sorts.PRICE_DSC.sort ? 'checked' : ''} />
                                <label for="price-dsc">By price (DSC)</label>
                            </div>

                            <input type="submit" value="Sort" />
                        </form>
                    </div>

                    <a href="/PlantShop/PageRedirect?page=create-plant">Create plant</a>
                </div>

                <div class="list">
                    <h2>Plant lists:</h2>

                    <c:choose>
                        <c:when test="${requestScope.plants == null || empty requestScope.plants}">
                            <h2>No plants were found.</h2>
                        </c:when>

                        <c:otherwise>
                            <fmt:parseNumber var="listSize" integerOnly="true" value="${size % 5 != 0 ? size / 5 + 1 : size / 5}" />

                            <div class="list-index">
                                <a class="begin-list" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=0${query}">&lt;&lt;</a>
                                <a class="decrease-index" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=${index - 1 < 0 ? 0 : index - 1}${query}">&lt;</a>
                                <div class="index">page ${index + 1} of ${listSize}</div>
                                <a class="increase-index" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=${index + 1 > listSize ? listSize - 1 : index + 1}${query}">&gt;</a>
                                <a class="end-list" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                            </div>

                            <c:forEach items="${requestScope.plants}" var="plant" begin="${index * 5}" end="${index * 5 + 4}">
                                <div class="list-item">
                                    <div class="img-container item-img square-img-height">
                                        <img src="/PlantShop/ImageRetriever?resource=plant&amp;pid=${plant.ID}" alt="${fn:escapeXml(plant.name)}" />
                                    </div>

                                    <div class="info">
                                        <p>ID: ${plant.ID}</p>
                                        <p>Name: ${fn:escapeXml(plant.name)}</p>
                                        <p>Price: ${plant.price}$</p>
                                        <p>Description: ${plant.description}</p>
                                        <p>Status: ${plant.status}</p>
                                        <p>Category: ${plant.categoryName}</p>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="list-index">
                                <a class="begin-list" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=0${query}' default='' />">&lt;&lt;</a>

                                <a class="decrease-index" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=${index - 1}${query}">&lt;</a>

                                <div class="index">page ${index + 1} of ${listSize}</div>

                                <a class="increase-index" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=${index + 1 >= listSize ? listSize - 1 : index + 1}${query}">&gt;</a>

                                <a class="end-list" href="/PlantShop/PageRedirect?page=manage-plants&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
