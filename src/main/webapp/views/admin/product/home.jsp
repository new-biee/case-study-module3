<%--
  Created by IntelliJ IDEA.
  User: Noob
  Date: 1/10/2021
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Home</title>
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
    <script>
        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').trigger('focus')
        })
    </script>
</head>
<body>
<h1 class="font-weight-bold text-center">Product Management</h1>
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
        <h2 class="font-weight-bold text-center">List product</h2>
        <hr>
        <div class="container text-left row">
            <a href="/products?action=create">Add New Product</a>
            <div class="col-md-1"></div>
            <a href="/category">Category List</a>
        </div>
        <br>
        <form id="remove" method="post" onsubmit="return confirm('Do you wanna remove this product?');">
            <table class="table table-bordered">
                <thead>
                <tr class="col-md-12">
                    <th>Product Code</th>
                    <th>Product Name</th>
                    <th class="form-group">
                        <%--                        <from action="category_id" method="get">--%>
                        <%--                            <select class="form-control" id="exampleFormControlSelect1">--%>
                        <%--                                <option value="">Show All</option>--%>
                        <%--                                <c:forEach var="category" items="${categoryList}">--%>
                        <%--                                    <option name="category_id" value="${category.categoryId}"--%>
                        <%--                                            onclick="optionCategory('${category.categoryId}')"--%>
                        <%--                                            href="/product?action=filter&categoryId=${category.categoryId}">--%>
                        <%--                                        <c:out value="${category.categoryName}"/>--%>
                        <%--                                    </option>--%>
                        <%--                                </c:forEach>--%>
                        <%--                            </select>--%>
                        <%--                        </from>--%>
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Category Name
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <c:forEach var="category" items="${categoryList}">
                                    <a class="dropdown-item"
                                       href="/products?action=filter&categoryId=${category.categoryId}">
                                        <c:out value="${category.categoryName}"/>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <td>
<%--                            <a href="">--%>
                                <c:out value="${product.productCode}"/>
<%--                            </a>--%>
                        </td>
                        <td>
                            <c:out value="${product.productName}"/>
                        </td>
                        <td>
                            <c:out value="${product.category.categoryName}"/>
                        </td>
                        <td>
                            <c:out value="${product.desc}"/>
                        </td>
                        <td>
                            <c:out value="${product.price}"/>
                        </td>
                        <td>
                            <a class="btn btn-primary" href="/products?action=edit&product_code=${product.productCode}">
                                Edit
                            </a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="submit" class="btn btn-danger"
                                    onclick="removeClick('${product.productCode}')">
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
    function removeClick(productCode) {
        console.log(productCode);
        document.getElementById('remove').action = '/products?action=remove&product_code=' + productCode;
        document.getElementById('remove').method = 'post';
    }
</script>
<script>
    function optionCategory(categoryId) {
        console.log(categoryId)
        document.getElementById('category_id').action = '/product?action=filter&categoryId' + categoryId;
        document.getElementById('category_id').method = 'post';
    }
</script>
</body>t
</html>
