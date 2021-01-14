<%--
  Created by IntelliJ IDEA.
  User: Noob
  Date: 1/11/2021
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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
</head>
<body>
<h1 class="font-weight-bold text-center">Product Management</h1>
<h3 class="font-weight-bold text-center"><a href="products?action=products">List All Products</a></h3>
<h2 class="font-weight-bold text-center">Update Product</h2>

<form method="post">
    <div class="form-group row">
        <div class="col-md-2"></div>
        <label class="col-md-2 colFormLabel">Product Name</label>
        <div class="col-md-4">
            <input class="form-control" type="text" name="product_name" value="${product.productName}"/>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="form-group row">
        <div class="col-md-2"></div>
        <label class="col-md-2 colFormLabel">Product Desc</label>
        <div class="col-md-4">
            <input class="form-control" type="text" name="product_desc" value="${product.desc}"/>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="form-group row">
        <div class="col-md-2"></div>
        <label class="col-md-2 colFormLabel">Product Price</label>
        <div class="col-md-4">
            <input class="form-control" type="number" name="product_price" value="${product.price}"/>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="form-group row">
        <div class="col-md-2"></div>
        <label class="col-md-2 colFormLabel">Product Category</label>
        <div class="col-md-4">
            <select class="form-control" id="exampleFormControlSelect1" name="category_id">
                <c:forEach var="category" items="${categoryList}">
                    <option name="category_name" value="${category.categoryId}">
                        <c:out value="${category.categoryName}"/>
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-5"></div>
        <div class="col-md-1">
            <button class="btn btn-primary" type="submit" value="Save">Save</button>
        </div>
        <div class="col-md-1">
            <button class="btn btn-danger" type="reset">Reset</button>
        </div>
        <div class="col-md-5"></div>
    </div>
</form>
</body>
</html>
