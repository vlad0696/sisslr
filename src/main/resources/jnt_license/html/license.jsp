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

<%exampleController.setData();%>
<ul>
    <c:forEach var="entity" items="<%= exampleController.getHello()%>">
        <li>${entity.id} : "${entity.city}"</li>
    </c:forEach>
</ul>


<ul>
    <c:forEach var="entity" items="<%= exampleController.getUsers()%>">
        <li>
            <input type="hidden" value="${entity.id}"  class="id"/>
            <input type="text" value="${entity.firstname}"  class="FirstName"/>
            <input type="text" value="${entity.lastname}"  class="LastName"/>
            <input type="text"  value="${entity.email}" class="Email"/>
            <input type="text"  value="${entity.phonenumber}" class="Phone"/>
            <input type="button" value="Сохранить" class="update">
        </li>
    </c:forEach>

</ul>

<script>
    $(document).ready(function () {
        $(document).on('click', '.update', function () {
            var id = $(this).closest('tr').find('input.id').val();

            var FirstName = $(this).closest('tr').find('input.FirstName').val();
            var LastName = $(this).closest('tr').find('input.LastName').val();
            var Email = $(this).closest('tr').find('input.Email').val();
            var Phone = $(this).closest('tr').find('input.Phone').val();

            $.ajax({
                type: 'POST',
                url: '/disease/update',
                data: {id: id, FirstName: FirstName, action:LastName },
                success: function (data) {
                    location.reload();
                },
                async: true
            });
            location.reload();
        });
    });


</script>