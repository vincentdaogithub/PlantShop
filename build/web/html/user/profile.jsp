<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="/PlantShop/css/main.min.css" />
        <link rel="stylesheet" href="/PlantShop/css/user/profile.min.css" />
        <title>Viridis</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/gif/main_bg.gif" alt="tree and lake" />
        </div>

        <main>
            <section class="profile-ava-container">
                <h2 class="hello-user">Hello, <c:out value="${sessionScope.account.fullname}" /></h2>

                <div class="ava-container">
                    <img class="profile-ava" src="/PlantShop/ImageRetriever?resource=avatar" alt="profile image" />
                </div>

                <form action="/PlantShop/Controller" method="post">
                    <input type="hidden" name="action" value="logout" />
                    <input class="logout" type="submit" value="Log out" />
                </form>
            </section>

            <section class="profile-info-container">
                <div class="info-section">
                    <div class="info-label-container">
                        <div class="info-label">Email:</div>
                    </div>

                    <div class="info-container">
                        <div class="info"><c:out value="${sessionScope.account.email}" /></div>
                        <div class="info-update-link" onclick="toggleUpdateUserInfo('update-email')">Update your email</div>

                        <form id="update-email" class="info-update" data-toggle="off" action="/PlantShop/Controller" method="post">
                            <label for="new-email">New email:</label>
                            <input id="new-email" type="email" name="new-email" placeholder="new email..." />

                            <label for="password-email">Password:</label>
                            <input id="password-email" type="password" name="password" placeholder="password..." />

                            <input type="hidden" name="action" value="update" />
                            <input type="hidden" name="update" value="email" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>
                </div>

                <div class="content-break-container">
                    <img class="content-break" src="/PlantShop/img/dividers/divider_white.png" alt="plant divider" />
                </div>

                <div class="info-section">
                    <div class="info-label-container">
                        <div class="info-label">Password:</div>
                    </div>

                    <div class="info-container">
                        <div class="info-update-link" onclick="toggleUpdateUserInfo('update-password')">Update your password</div>

                        <form id="update-password" class="info-update" data-toggle="off" action="/PlantShop/Controller" method="post">
                            <label for="old-password">Old password:</label>
                            <input id="old-password" type="password" name="old-password" placeholder="old password..." />

                            <label for="new-password">New password:</label>
                            <input id="new-password" type="password" name="new-password" placeholder="new password..." />

                            <input type="hidden" name="action" value="update" />
                            <input type="hidden" name="update" value="password" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>
                </div>

                <div class="content-break-container">
                    <img class="content-break" src="/PlantShop/img/dividers/divider_white.png" alt="plant divider" />
                </div>

                <div class="info-section">
                    <div class="info-label-container">
                        <div class="info-label">Fullname:</div>
                    </div>

                    <div class="info-container">
                        <div class="info"><c:out value="${sessionScope.account.fullname}" /></div>
                        <div class="info-update-link" onclick="toggleUpdateUserInfo('update-fullname')">Update your fullname</div>

                        <form id="update-fullname" class="info-update" data-toggle="off" action="/PlantShop/Controller" method="post">
                            <label for="new-fullname">New fullname:</label>
                            <input id="new-fullname" type="text" name="new-fullname" placeholder="new fullname..." />

                            <label for="password-fullname">Password:</label>
                            <input id="password-fullname" type="password" name="password" placeholder="password..." />

                            <input type="hidden" name="action" value="update" />
                            <input type="hidden" name="update" value="fullname" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>
                </div>

                <div class="content-break-container">
                    <img class="content-break" src="/PlantShop/img/dividers/divider_white.png" alt="plant divider" />
                </div>

                <div class="info-section">
                    <div class="info-label-container">
                        <div class="info-label">Phone:</div>
                    </div>

                    <div class="info-container">
                        <div class="info"><c:out value="${sessionScope.account.phone}" /></div>
                        <div class="info-update-link" onclick="toggleUpdateUserInfo('update-phone')">Update your phone</div>

                        <form id="update-phone" class="info-update" data-toggle="off" action="/PlantShop/Controller" method="post">
                            <label for="new-phone">New phone:</label>
                            <input id="new-phone" type="tel" name="new-phone" placeholder="new phone..." />

                            <label for="password-phone">Password:</label>
                            <input id="password-phone" type="password" name="password" placeholder="password..." />

                            <input type="hidden" name="action" value="update" />
                            <input type="hidden" name="update" value="phone" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>

    <script src="/PlantShop/js/main.min.js"></script>

</html>