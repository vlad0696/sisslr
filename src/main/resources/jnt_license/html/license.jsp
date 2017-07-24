<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="s" uri="http://www.jahia.org/tags/search" %>
<%@page import="org.ipps.sisslr.contrlollers.ExampleController"%>
<% ExampleController exampleController = new ExampleController();%>

<h2>${currentNode.properties['jcr:title'].string}</h2>


<ul>
    <c:forEach var="entity" items="<%= exampleController.getHello()%>">
        <li>${entity.id} : "${entity.city}"</li>
    </c:forEach>
</ul>


<ul>
    <c:forEach var="entity" items="<%= exampleController.getUsers()%>">
        <li>
            <input type="hidden" value="${entity.id}" />
            <input type="text" value="${entity.firstname}" /> ,  "${entity.lastname}",  "${entity.email}" ,  "${entity.phonenumber}"
        </li>
    </c:forEach>

</ul>