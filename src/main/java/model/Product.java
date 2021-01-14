package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productCode;
    private int categoryId;
    private String productName;
    private String desc;
    private double price;
    private int status;
    private Category category;

    public Product() {
    }

    public Product(String productCode, String productName, double price, int categoryId ) {
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.productName = productName;
        this.price = price;
    }

    public Product(String productCode, String productName, String desc, int categoryId, double price) {
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.productName = productName;
        this.desc = desc;
        this.price = price;
    }

    public Product(String productCode, int categoryId, String productName, String desc) {
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.productName = productName;
        this.desc = desc;
    }

    public Product(String productCode, int categoryId, String productName, String desc, int status) {
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.productName = productName;
        this.desc = desc;
        this.status = status;
    }

    public Product(String productCode, String productName, String desc, int status, Category category, double price) {
        this.productCode = productCode;
        this.productName = productName;
        this.desc = desc;
        this.status = status;
        this.category = category;
        this.price = price;
    }

    public Product(String productName, String productDesc, double productPrice, Category category) {
        this.productName = productName;
        this.desc = productDesc;
        this.price = productPrice;
        this.category = category;
    }

    public Product(String productCode, String productName, String productDesc, Category category, double price) {
        this.productCode = productCode;
        this.productName = productName;
        this.desc = productDesc;
        this.category = category;
        this.price = price;
    }

    public Product(String productCode, int status) {
        this.productCode = productCode;
        this.status = status;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getCategoryID() {
        return categoryId;
    }

    public void setCategoryID(int categoryID) {
        this.categoryId = categoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}

