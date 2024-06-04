<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title><spring:message code="game.list.title"/></title>
        <t:common />
        <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    </head>
    <body>
        <div class="top-container">
            <h1><spring:message code="game.list.title"/></h1>
            <c:if test="${not empty successfulDelete}">
                <p class="delete-message show"><spring:message code="successful.delete"/></p>
            </c:if>
            <c:if test="${not empty successfulCreate}">
                <p class="success-message show"><spring:message code="successful.create"/></p>
            </c:if>
            <div class="container-1">
                <form action="/game" method="get">
                    <input type="text" id="search" name="title" placeholder="<spring:message code="title"/>">
                    <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <form action="/game/new" method="get">
                    <button class="create-button" type="submit"><spring:message code="create.button"/></button>
                </form>
            </div>
        </div>
        <div class="mid-container">
            <table class="game-table">
                <c:if test="${empty games}">
                    <p><spring:message code="no.games.found"/></p>
                </c:if>
                <c:if test="${not empty games}">
                <thead>
                <tr>
                    <th><spring:message code="id"/></th>
                    <th><spring:message code="title"/></th>
                    <th><spring:message code="options"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${games}" var="game">
                    <tr>
                        <td><a href="/game/${game.id}">${game.id}</a></td>
                        <td><a href="/game/${game.id}">${game.title}</a></td>
                        <td>
                            <form action="/game/delete/${game.id}" method="post" onsubmit="return confirm('<spring:message code="confirm.delete.message"/>');">
                                <input type="hidden" name="id" value="${game.id}">
                                <button type="submit" class="delete-button"><spring:message code="delete.button"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </c:if>
    </body>
</html>
