<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page import="business.sort.PriceRanges" %>
<%@page import="business.sort.Sorts" %>
<%@page import="obj.account.AccountStatuses" %>
<%@page import="obj.account.Accounts" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/admin/manage_list.css" />

        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis - Manage accounts</title>
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

                        <form class="search" action="/PlantShop/PageRedirect?page=manage-accounts" method="post">
                            <h3>By ID:</h3>

                            <div class="input">
                                <input type="text" name="id" placeholder="id..." required />
                            </div>

                            <input type="hidden" name="search" value="id" />
                            <input type="submit" value="Search" />
                        </form>

                        <form class="search" action="/PlantShop/PageRedirect?page=manage-accounts" method="post">
                            <h3>By name:</h3>

                            <div class="input">
                                <input type="text" name="name" value="${fn:escapeXml(searchName)}" placeholder="account name..." />
                            </div>

                            <input type="hidden" name="search" value="name" />
                            <input type="submit" value="Search" />
                        </form>
                    </div>

                    <div class="sort-function">
                        <h2>Sort</h2>

                        <form class="sort" action="/PlantShop/PageRedirect?page=manage-accounts${fn:escapeXml(searchQuery)}" method="post">
                            <div class="input radio">
                                <input id="name-asc" type="radio" name="sort" value="name-asc" />
                                <label for="name-asc">By name (ASC)</label>
                            </div>

                            <div class="input radio">
                                <input id="name-dsc" type="radio" name="sort" value="name-dsc" />
                                <label for="name-dsc">By name (DSC)</label>
                            </div>

                            <div class="input radio">
                                <input id="id-asc" type="radio" name="sort" value="id-asc" />
                                <label for="name-dsc">By ID (ASC)</label>
                            </div>

                            <div class="input radio">
                                <input id="id-dsc" type="radio" name="sort" value="id-dsc" />
                                <label for="name-dsc">By ID (DSC)</label>
                            </div>

                            <input type="submit" value="Sort" />
                        </form>
                    </div>
                </div>

                <div class="list">
                    <h2>Plant lists:</h2>

                    <c:choose>
                        <c:when test="${requestScope.accounts == null || empty requestScope.accounts}">
                            <h2>No accounts were found.</h2>
                        </c:when>

                        <c:otherwise>
                            <fmt:parseNumber var="listSize" integerOnly="true" value="${size % 5 != 0 ? size / 5 + 1 : size / 5}" />

                            <div class="list-index">
                                <a class="begin-list" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=0${query}">&lt;&lt;</a>
                                <a class="decrease-index" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=${index - 1}${query}">&lt;</a>
                                <div class="index">page ${index + 1} of ${listSize}</div>
                                <a class="increase-index" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=${index + 1 >= listSize ? listSize - 1 : index + 1}${query}">&gt;</a>
                                <a class="end-list" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                            </div>

                            <c:forEach items="${requestScope.accounts}" var="account" begin="${index * 5}" end="${index * 5 + 4}">
                                <c:if test="${sessionScope.account.ID != account.ID}">
                                    <div class="list-item">
                                        <div class="img-container item-img square-img-height">
                                            <img src="/PlantShop/ImageRetriever?resource=avatar&amp;id=${account.ID}" alt="avatar" />
                                        </div>

                                        <div class="info">
                                            <p>ID: ${account.ID}</p>
                                            <p>Email: ${fn:escapeXml(account.email)}</p>
                                            <p>Full name: ${fn:escapeXml(account.fullname)}</p>
                                            <p>Phone: ${fn:escapeXml(account.phone)}</p>
                                            <p>Status: ${account.status == AccountStatuses.ACTIVE.status ? 'active' : 'inactive'}</p>
                                            <p>Role: ${account.role == Accounts.ADMIN.role ? 'admin' : 'user'}</p>

                                            <form class="update-account" action="/PlantShop/PageRedirect?page=manage-accounts" method="post">
                                                <input type="hidden" name="action" value="admin" />
                                                <input type="hidden" name="admin-action" value="update" />
                                                <input type="hidden" name="update" value="account-${account.ID}-status-${account.status == AccountStatuses.ACTIVE.status ? AccountStatuses.INACTIVE.status : AccountStatuses.ACTIVE.status}" />
                                                <input type="submit" value="${account.status == AccountStatuses.ACTIVE.status ? 'Block' : 'Unblock'}" />
                                            </form>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                            <div class="list-index">
                                <a class="begin-list" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=0${query}' default='' />">&lt;&lt;</a>

                                <a class="decrease-index" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=${index - 1}${query}">&lt;</a>

                                <div class="index">page ${index + 1} of ${listSize}</div>

                                <a class="increase-index" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=${index + 1 >= listSize ? listSize - 1 : index + 1}${query}">&gt;</a>

                                <a class="end-list" href="/PlantShop/PageRedirect?page=manage-accounts&amp;index=${listSize - 1}${query}">&gt;&gt;</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>
