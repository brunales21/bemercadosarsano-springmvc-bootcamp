<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title><spring:message code="gameDetailsTitle"/></title>
        <t:common />
        <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    </head>
    <body>
        <div class="top-container">
            <h1><spring:message code="gameDetailsTitle"/></h1>
            <c:if test="${not empty successfulUpdate}">
                <p class="success-message show"><spring:message code="successful.update"/></p>
            </c:if>
                <a href="/" class="home-link">
                    <i class="fa-solid fa-house"></i>
                </a>
        </div>
        <div class="mid-container">
            <table class="game-table">
                <tr>
                    <th><spring:message code="id"/></th>
                    <th><spring:message code="title"/></th>
                    <th><spring:message code="description"/></th>
                    <th><spring:message code="steam.id"/></th>
                    <th><spring:message code="options"/></th>
                </tr>
                <tr>
                    <td>${game.id}</td>
                    <td>${game.title}</td>
                    <td>${game.description}</td>
                    <td>${game.steamId}</td>
                    <td>
                        <form action="/game/edit/${id}" method="get">
                            <button class="create-button" type="submit"><spring:message code="update.button"/></button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
