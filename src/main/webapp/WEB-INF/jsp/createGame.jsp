<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title><spring:message code="create.game.title"/></title>
        <link rel="stylesheet" type="text/css" href="/css/forms.css">
        <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    </head>
    <body>
        <h1><spring:message code="create.game.title"/></h1>
        <div class="form-container">
            <form:form action="/game/new" method="post" modelAttribute="game">
                <div class="form-group">
                    <form:label path="title" for="title"><spring:message code="title"/></form:label>
                    <form:input path="title" id="title"/>
                    <form:errors class="validation-message" path="title"/>
                </div>
                <div class="form-group">
                    <form:label path="description" for="description"><spring:message code="description"/></form:label>
                    <form:textarea path="description" id="description" rows="4"/>
                </div>
                <div class="form-group">
                    <form:label path="steamId" for="steamId"><spring:message code="steam.id"/></form:label>
                    <form:input path="steamId" id="steamId"/>
                    <c:choose>
                        <c:when test="${not empty valid}">
                            <p class="validation-message"><spring:message code="valid"/></p>
                        </c:when>
                    </c:choose>
                </div>
                <button class="create-button" type="submit"><spring:message code="create.button"/></button>
            </form:form>
        </div>
    </body>
</html>
