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
<%@page import="org.ipps.sisslr.controllers.ExampleController"%>
<% ExampleController exampleController = new ExampleController();%>
<h2>${currentNode.properties['jcr:title'].string}</h2>
<table class="table">
    <c:forEach var="entity" items="<%= exampleController.getUsers()%>">
        <tr class="table-row-cell">
            <td class="table-cell"><input type="hidden" value="${entity.id}"  class="id"/></td>
            <td class="info"><input type="text" value="${entity.firstname}"  class="FirstName"/></td>
            <td class="danger"><input type="text" value="${entity.lastname}"  class="LastName"/></td>
            <td class="success"><input type="text"  value="${entity.email}" class="Email"/></td>
            <td><input type="text"  value="${entity.phonenumber}" class="Phone"/></td>
            <td><input type="button" value="POST" class="update"></td>
            <td><input type="button" value="DELETE" class="delete"></td>
            <td><input type="button" value="GET" class="get"></td>
            <td class="link"> <a href="home/license-detail.html?id=${entity.id}">Подробнее</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><input type="hidden" class="id"/></td>
        <td><input type="text"   class="FirstName"/></td>
        <td><input type="text"   class="LastName"/></td>
        <td><input type="text"   class="Email"/></td>
        <td><input type="text"   class="Phone"/></td>
        <td><input type="button" value="ADD" class="add"></td>
    </tr>
</table>
<jsp:include page="test.jsp" />
<div id="test"> </div>
<% response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
%>
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
                url: '/cms/example/update',
                data: {Id: id, FirstName: FirstName, LastName: LastName, Email:Email, PhoneNumber:Phone},
                dataType: 'text',
                success: function (data) {
                    $('#test').append(data)
                },
                error: function (data) {
                    alert("ай ай ай!!"+ data);
                },
                async: true
            });


        });
    });
    $(document).ready(function () {
        $(document).on('click', '.delete', function () {
            var id = $(this).closest('tr').find('input.id').val();
            var FirstName = $(this).closest('tr').find('input.FirstName').val();
            var LastName = $(this).closest('tr').find('input.LastName').val();
            var Email = $(this).closest('tr').find('input.Email').val();
            var Phone = $(this).closest('tr').find('input.Phone').val();
            $.ajax({
                type: 'POST',
                url: '/cms/example/delete',
                data: {Id: id, FirstName: FirstName, LastName: LastName, Email:Email, PhoneNumber:Phone},
                dataType: 'text',
                success: function (data) {
                    location.reload();
                },
                error: function (data) {
                    alert("ай ай ай!!"+ data);
                },
                async: true
            });


        });
    });
    $(document).ready(function () {
        $(document).on('click', '.add', function () {
            var id = 0;
            var FirstName = $(this).closest('tr').find('input.FirstName').val();
            var LastName = $(this).closest('tr').find('input.LastName').val();
            var Email = $(this).closest('tr').find('input.Email').val();
            var Phone = $(this).closest('tr').find('input.Phone').val();
            $.ajax({
                type: 'POST',
                url: '/cms/example/addUser',
                data: {Id: id, FirstName: FirstName, LastName: LastName, Email:Email, PhoneNumber:Phone},
                dataType: 'text',
                success: function (data) {
                },
                error: function (data) {
                    alert("ай-ай-ай!!"+ data);
                },
                async: true
            });


        });
    });
    $(document).ready(function () {
        $(document).on('click', '.get', function () {
            $.ajax({
                type: 'GET',
                url: '/cms/example/JSON',
                dataType: 'json',
                success: function (data) {
                        $('#test').append($('<div>', {
                            text: data[0].FirstName
                        }));

                    },
                error: function (data) {
                    alert("ай ай ай!!")
                },
                async: true
            });


        });
    });
</script>