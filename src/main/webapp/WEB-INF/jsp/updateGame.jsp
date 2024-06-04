<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title><spring:message code="update.game.title"/></title>
        <link rel="stylesheet" type="text/css" href="/css/forms.css">
        <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    </head>
    <body>
        <h1><spring:message code="update.game.title"/></h1>
        <div class="form-container">
            <form:form action="/game/edit/${game.id}" method="post" modelAttribute="game">
                <div class="form-group">
                    <form:label for="title" path="title"><spring:message code="title"/></form:label>
                    <form:input id="title" path="title"/>
                    <form:errors class="validation-message" path="title"/>
                </div>
                <div class="form-group">
                    <form:label for="description" path="description"><spring:message code="description"/></form:label>
                    <form:textarea id="description" rows="4" path="description"/>
                </div>
                <div class="form-group">
                    <form:label for="steamId" path="steamId"><spring:message code="steam.id"/></form:label>
                    <form:input id="steamId" path="steamId"/>
                    <c:choose>
                        <c:when test="${not empty valid}">
                            <p class="validation-message"><spring:message code="valid"/></p>
                        </c:when>
                    </c:choose>
                </div>
                <button class="create-button" type="submit"><spring:message code="update.button"/></button>
            </form:form>
        </div>
    </body>
</html>
