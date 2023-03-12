<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/PlantShop/css/main.css" />
        <link rel="stylesheet" href="/PlantShop/css/user/profile.css" />
        <script src="/PlantShop/js/main.js" defer></script>

        <title>Viridis</title>
    </head>

    <body onload="init()">
        <jsp:include page="/html/template/header.jsp" />

        <div class="bg-container">
            <img class="bg-img" src="/PlantShop/img/resource/bg.png" alt="tree and lake" />
        </div>

        <main>
            <section class="profile">
                <div class="ava-panel">
                    <div class="img-container ava square-img-width">
                        <img src="/PlantShop/ImageRetriever?resource=avatar" alt="profile image" />
                    </div>

                    <form class="change-ava" action="/PlantShop/PageRedirect?page=profile" method="post">
                        <div class="input">
                            <label for="change-ava" tabindex="0">Change your profile avatar: </label>
                            <input id="change-ava" type="file" name="ava" accept="image/png, image/jpeg, image/gif" />
                        </div>

                        <input type="hidden" name="action" value="user-update" />
                        <input type="hidden" name="update" value="ava" />

                        <input type="submit" value="Change avatar" />
                    </form>

                    <form class="logout" action="/PlantShop/PageRedirect?page=home" method="post">
                        <input type="hidden" name="action" value="logout" />
                        <label class="scr-reader" for="logout">Log out</label>
                        <input id="logout" class="logout" type="submit" value="Log out" />
                    </form>
                </div>

                <div class="info-panel">
                    <h2>Hello, ${fn:escapeXml(sessionScope.account.fullname)}</h2>

                    <div class="info">
                        <h2>Email:</h2>

                        <p>Your email: ${fn:escapeXml(sessionScope.account.email)}</p>

                        <div class="update-link" tabindex="0" onclick="toggleUpdate('update-email', 'info-update')">
                            Update your email
                        </div>

                        <form id="update-email" class="info-update" data-toggle="off" action="/PlantShop/PageRedirect?page=profile" method="post" style="display: none;">
                            <div class="input">
                                <label for="new-email">New email:</label>
                                <input id="new-email" type="email" name="new-email" placeholder="new email..." />
                            </div>

                            <div class="input">
                                <label for="password-email">Password:</label>
                                <input id="password-email" type="password" name="password" placeholder="password..." />
                            </div>

                            <input type="hidden" name="action" value="user-update" />
                            <input type="hidden" name="update" value="email" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>

                    <div class="info">
                        <h2>Password:</h2>
    
                        <div class="update-link" tabindex="0" onclick="toggleUpdate('update-password', 'info-update')">
                            Update your password
                        </div>
    
                        <form id="update-password" class="info-update" data-toggle="off" action="/PlantShop/PageRedirect?page=profile" method="post" style="display: none;">
                            <div class="input">
                                <label for="old-password">Old password:</label>
                                <input id="old-password" type="password" name="old-password" placeholder="old password..." />
                            </div>

                            <div class="input">
                                <label for="new-password">New password:</label>
                                <input id="new-password" type="password" name="new-password" placeholder="new password..." />
                            </div>
    
                            <input type="hidden" name="action" value="user-update" />
                            <input type="hidden" name="update" value="password" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>
    
                    <div class="info">
                        <h2>Fullname:</h2>
    
                        <p>Your fullname: ${fn:escapeXml(sessionScope.account.fullname)}</p>
    
                        <div class="update-link" tabindex="0" onclick="toggleUpdate('update-fullname', 'info-update')">
                            Update your fullname
                        </div>
    
                        <form id="update-fullname" class="info-update" data-toggle="off" action="/PlantShop/PageRedirect?page=profile" method="post" style="display: none;">
                            <div class="input">
                                <label for="new-fullname">New fullname:</label>
                                <input id="new-fullname" type="text" name="new-fullname" placeholder="new fullname..." />
                            </div>

                            <div class="input">
                                <label for="password-fullname">Password:</label>
                                <input id="password-fullname" type="password" name="password" placeholder="password..." />
                            </div>
    
                            <input type="hidden" name="action" value="user-update" />
                            <input type="hidden" name="update" value="fullname" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>
    
                    <div class="info">
                        <h2>Phone:</h2>
    
                        <p>Your phone: ${fn:escapeXml(sessionScope.account.phone)}</p>
    
                        <div class="update-link" tabindex="0" onclick="toggleUpdate('update-phone', 'info-update')">
                            Update your phone
                        </div>
    
                        <form id="update-phone" class="info-update" data-toggle="off" action="/PlantShop/PageRedirect?page=profile" method="post" style="display: none;">
                            <div class="input">
                                <label for="new-phone">New phone:</label>
                                <input id="new-phone" type="tel" name="new-phone" placeholder="new phone..." />
                            </div>

                            <div class="input">
                                <label for="password-phone">Password:</label>
                                <input id="password-phone" type="password" name="password" placeholder="password..." />
                            </div>
    
                            <input type="hidden" name="action" value="user-update" />
                            <input type="hidden" name="update" value="phone" />
                            <input type="submit" value="Update" />
                        </form>
                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="/html/template/footer.jsp" />
    </body>
</html>