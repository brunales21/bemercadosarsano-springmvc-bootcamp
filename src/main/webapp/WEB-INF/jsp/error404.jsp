<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><spring:message code="error404.title"/></title>
        <link rel="stylesheet" type="text/css" href="/css/error404.css">
    </head>
    <body>
        <div class="container">
            <h1><spring:message code="error404.code"/></h1>
            <p><spring:message code="error404.message"/></p>
            <p><a href="/"><spring:message code="error404.back.to.main.page"/></a></p>
        </div>
    </body>
</html>