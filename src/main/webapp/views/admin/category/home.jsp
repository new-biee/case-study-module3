<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Noob
  Date: 1/4/2021
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Category</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</head>
<body>
<h1 class="font-weight-bold text-center">Category Management</h1>
<form method="get">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-3">
            <input class="form-control" type="text" size="50" name="search">
        </div>
        <div class="col-md-1">
            <input class="form-control" type="submit" value="Search" class="btn btn-outline-dark">
        </div>
        <div class="col-md-4"></div>
    </div>
</form>

<div class="text-center">
    <c:if test="${sessionScope.message_remove != null}">
        <p>Remove successfully</p>
        <% session.removeAttribute("message_remove");%>
    </c:if>
    <c:if test="${sessionScope.message_add != null}">
        <p>Add successfully</p>
        <% session.removeAttribute("message_add");%>
    </c:if>
    <c:if test="${sessionScope.message_edit != null}">
        <p>Edit successfully</p>
        <% session.removeAttribute("message_edit");%>
    </c:if>
</div>

<div class="row">
    <div class="container">
        <h2 class="font-weight-bold text-center">List category</h2>
        <hr>
        <div class="container text-left row">
            <a href="/category?action=create">Add New Category</a>
            <div class="col-md-1"></div>
            <a href="/products">Products List</a>
        </div>
        <br>
        <form id="remove" method="post" onsubmit="return confirm('Do you wanna delete this category?');">
            <table class="table table-bordered">
                <thead>
                <tr class="col-md-12">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th width="50%S">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="category" items="${categoryList}">
                    <tr>
                        <td>
                            <c:out value="${category.categoryId}"/>
                        </td>
                        <td>
                            <c:out value="${category.categoryName}"/>
                        </td>
                        <td>
                            <c:out value="${category.desc}"/>
                        </td>
                        <td>
                            <a class="btn btn-primary" href="/category?action=edit&id=${category.categoryId}"
                               style="color: white; ">
                                Edit
                            </a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="submit" class="btn btn-danger"
                                    onclick="deleteClick(${category.categoryId}) ">
                                Remove
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>

</body>
<script>
    function deleteClick(id) {
        console.log(id);
        document.getElementById('remove').action = '/category?action=remove&id=' + id;
        document.getElementById('remove').method = 'post';
    }
</script>
</html>

